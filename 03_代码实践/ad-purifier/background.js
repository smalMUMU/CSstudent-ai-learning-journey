// 广告净化器 · 后台引擎
// 职责：
//   ① 解析内置规则（lists/bilibili.txt + lists/network.txt，随扩展分发）
//   ② EasyList 规则订阅：下载、解析、缓存、自动/手动更新
//   ③ 把网络过滤规则同步为 declarativeNetRequest 动态规则（受限上限）
importScripts("filter-parser.js");

(() => {
  "use strict";

  const CONFIG_KEY = "config";
  const FILTER_KEY = "filterData";
  const RULE_ID_BASE = 1000;

  const AUTO_UPDATE_DAYS = 7;
  const MAX_LIST_BYTES = 12 * 1024 * 1024;
  const FETCH_TIMEOUT_MS = 20000;
  const DEFAULT_MAX_NETWORK_RULES = 1500; // DNR 动态上限 5000，订阅规则截断至此

  // 订阅源（每个源按序尝试镜像，任一成功即用）
  const SOURCES = [
    {
      name: "EasyList China + EasyList",
      urls: [
        "https://easylist-downloads.adblockplus.org/easylistchina+easylist.txt",
        "https://raw.githubusercontent.com/easylist/easylistchina/master/easylistchina.txt",
      ],
    },
    {
      name: "EasyPrivacy",
      urls: [
        "https://easylist-downloads.adblockplus.org/easyprivacy.txt",
        "https://raw.githubusercontent.com/easylist/easylist/master/easylist/easyprivacy.txt",
      ],
    },
  ];

  // ---------- 内置规则 ----------
  async function loadBundled() {
    const [bili, net] = await Promise.all([
      fetch(chrome.runtime.getURL("lists/bilibili.txt")).then((r) => r.text()),
      fetch(chrome.runtime.getURL("lists/network.txt")).then((r) => r.text()),
    ]);
    const biliParsed = AdPurifierParser.parseAdblockText(bili, ["bilibili.com"]);
    const netParsed = AdPurifierParser.parseAdblockText(net);
    return {
      network: netParsed.network,
      cosmetic: biliParsed.cosmetic,
      exceptions: biliParsed.exceptions,
    };
  }

  // ---------- 订阅下载 ----------
  async function fetchList(url) {
    const ctrl = new AbortController();
    const timer = setTimeout(() => ctrl.abort(), FETCH_TIMEOUT_MS);
    try {
      const res = await fetch(url, { signal: ctrl.signal });
      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      const text = await res.text();
      if (text.length > MAX_LIST_BYTES) throw new Error("列表过大，已忽略");
      return text;
    } finally {
      clearTimeout(timer);
    }
  }

  async function fetchSubscriptions() {
    const network = new Set();
    const cosmetic = [];
    const exceptions = [];
    const names = [];

    for (const src of SOURCES) {
      let text = null;
      let used = null;
      for (const url of src.urls) {
        try {
          text = await fetchList(url);
          used = url;
          break;
        } catch (e) {
          /* 尝试下一个镜像 */
        }
      }
      if (text === null) continue; // 该源全部镜像失败，跳过

      const p = AdPurifierParser.parseAdblockText(text);
      for (const f of p.network) network.add(f);
      cosmetic.push(...p.cosmetic);
      exceptions.push(...p.exceptions);
      names.push(`${src.name}（${used.replace(/^https?:\/\//, "").split("/")[0]}）`);
    }
    return { network: [...network], cosmetic, exceptions, names };
  }

  // ---------- 合并与存储 ----------
  function mergeParsed(base, sub) {
    const network = [...new Set([...base.network, ...sub.network])];
    const cosmetic = [...base.cosmetic, ...sub.cosmetic];
    const exceptions = [...base.exceptions, ...sub.exceptions];
    // 以 selector+domains 去重
    const seen = new Set();
    const uniqCosmetic = cosmetic.filter((r) => {
      const k = `${r.selector}|${r.domains.join(",")}`;
      if (seen.has(k)) return false;
      seen.add(k);
      return true;
    });
    const seenEx = new Set();
    const uniqExceptions = exceptions.filter((r) => {
      const k = `${r.selector}|${r.domains.join(",")}`;
      if (seenEx.has(k)) return false;
      seenEx.add(k);
      return true;
    });
    return { network, cosmetic: uniqCosmetic, exceptions: uniqExceptions };
  }

  async function loadStored() {
    const data = await chrome.storage.local.get(FILTER_KEY);
    return data[FILTER_KEY] || null;
  }

  async function saveFilterData(data) {
    await chrome.storage.local.set({ [FILTER_KEY]: data });
  }

  /**
   * 确保规则数据存在且不过期；manual=true 时强制拉取订阅。
   * 订阅失败时降级为仅内置规则（不阻断使用）。
   * 返回合并后的规则数据。
   */
  async function updateFilterData(manual) {
    const base = await loadBundled();
    const cfg = (await chrome.storage.local.get(CONFIG_KEY))[CONFIG_KEY] || {};
    const auto = cfg.autoUpdate !== false;
    const existing = await loadStored();
    const stale =
      !existing ||
      !existing.updatedAt ||
      Date.now() - existing.updatedAt > AUTO_UPDATE_DAYS * 86400000;

    if (!manual && existing && !(auto && stale)) {
      return existing;
    }

    let sub = { network: [], cosmetic: [], exceptions: [], names: [] };
    try {
      sub = await fetchSubscriptions();
    } catch (e) {
      console.warn("[广告净化器] 订阅拉取失败，仅使用内置规则：", e);
    }
    const merged = mergeParsed(base, sub);
    const data = {
      ...merged,
      updatedAt: Date.now(),
      stats: {
        network: merged.network.length,
        cosmetic: merged.cosmetic.length,
        exceptions: merged.exceptions.length,
        sources: sub.names,
        updatedAt: Date.now(),
      },
    };
    await saveFilterData(data);
    return data;
  }

  // ---------- DNR 同步 ----------
  function buildRule(urlFilter, id) {
    return {
      id,
      priority: 1,
      action: { type: "block" },
      condition: {
        urlFilter,
        // 仅拦截从 bilibili 页面发起的请求（作用域限定，避免全局误伤）
        initiatorDomains: ["bilibili.com"],
        resourceTypes: [
          "xmlhttprequest",
          "script",
          "image",
          "sub_frame",
          "media",
          "other",
        ],
      },
    };
  }

  async function syncRules(cfg, filterData) {
    cfg = cfg || {};
    filterData = filterData || {};
    const enabled = cfg.enabled !== false;
    const maxNet = Math.max(
      0,
      Math.min(4500, Number(cfg.maxNetworkRules) || DEFAULT_MAX_NETWORK_RULES)
    );

    const custom = (cfg.requestRules || [])
      .map((r) => (typeof r === "string" ? r.trim() : r && r.urlFilter ? String(r.urlFilter).trim() : ""))
      .filter(Boolean);

    const filters = [];
    const seen = new Set();
    const push = (f) => {
      if (!f) return;
      const k = String(f).toLowerCase();
      if (seen.has(k)) return;
      seen.add(k);
      filters.push(f);
    };

    if (enabled) {
      custom.forEach(push); // 用户自定义始终生效
      (filterData.network || [])
        .slice(0, maxNet) // 内置精选 + 订阅，截断到上限
        .forEach(push);
    }

    const addRules = filters.map((f, i) => buildRule(f, RULE_ID_BASE + i));
    const removeRuleIds = (await chrome.declarativeNetRequest.getDynamicRules()).map((r) => r.id);
    await chrome.declarativeNetRequest.updateDynamicRules({ removeRuleIds, addRules });
    console.log(`[广告净化器] 已同步 ${addRules.length} 条网络过滤规则`);
  }

  // ---------- 生命周期 ----------
  async function init(forceUpdate = false) {
    let filterData = null;
    try {
      filterData = await updateFilterData(forceUpdate);
    } catch (e) {
      console.warn("[广告净化器] 规则初始化失败，使用已有数据：", e);
      filterData = await loadStored();
    }
    const cfg = (await chrome.storage.local.get(CONFIG_KEY))[CONFIG_KEY] || {};
    await syncRules(cfg, filterData);
  }

  chrome.runtime.onInstalled.addListener(() => init(true).catch(console.error));
  chrome.runtime.onStartup.addListener(() => init(false).catch(console.error));

  chrome.storage.onChanged.addListener((changes, area) => {
    if (area !== "local") return;
    if (changes[CONFIG_KEY] || changes[FILTER_KEY]) {
      init().catch(console.error);
    }
  });

  // 选项页「立即更新规则」
  chrome.runtime.onMessage.addListener((msg, _sender, sendResponse) => {
    if (msg && msg.type === "update-lists") {
      updateFilterData(true)
        .then((data) => sendResponse({ ok: true, stats: data.stats }))
        .catch((e) => sendResponse({ ok: false, error: String(e && e.message ? e.message : e) }));
      return true; // 异步响应
    }
  });

  init().catch(console.error);
})();
