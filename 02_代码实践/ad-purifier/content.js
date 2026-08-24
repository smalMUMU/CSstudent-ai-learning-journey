// 广告净化器 · 内容脚本（DOM 隐藏引擎 v2）
// 原理：把「内置 B站选择器库 + EasyList 订阅隐藏规则（按站点匹配）+ 用户自定义选择器」
//       合并为一条组合选择器（C++ 级匹配），MutationObserver 防抖扫描动态注入的广告。
// 支持例外规则（#@#）——命中例外的元素不隐藏。
(() => {
  "use strict";

  const CONFIG_KEY = "config";
  const FILTER_KEY = "filterData";
  const MAX_COSMETIC = 4000; // 隐藏规则数量上限，防页面卡顿

  // 极简兜底（filterData 未就绪时的最后防线）
  const FALLBACK_SELECTORS = ["#bannerAd", ".ad-report", "[id^='creative_']"];

  const hidden = new Set(); // 记录本脚本隐藏的元素，禁用时恢复
  let combinedSelector = "";
  let exceptionSelector = "";
  let enabled = true;
  let scanTimer = null;

  // 规则作用域匹配：无作用域 → 全局；有作用域 → 精确或子域匹配
  function siteMatches(domains) {
    if (!domains || domains.length === 0) return true;
    const host = location.hostname;
    return domains.some((d) => d === host || host.endsWith("." + d));
  }

  // 增量校验合并选择器：单条无效不拖垮整体（非法伪类/格式被丢弃）
  function combine(selectors) {
    const ok = [];
    const frag = document.createDocumentFragment();
    for (const s of selectors) {
      try {
        frag.querySelectorAll([...ok, s].join(","));
        ok.push(s);
      } catch (e) {
        /* 无效选择器，丢弃 */
      }
    }
    return ok.join(",");
  }

  async function buildRules() {
    const [cfgData, data] = await Promise.all([
      chrome.storage.local.get(CONFIG_KEY),
      chrome.storage.local.get(FILTER_KEY),
    ]);
    const cfg = cfgData[CONFIG_KEY] || {};
    const wasEnabled = enabled;
    enabled = cfg.enabled !== false;

    if (wasEnabled && !enabled) {
      restore(); // 关闭开关时恢复已隐藏的元素
    }

    const custom = (cfg.selectorRules || [])
      .map((s) => String(s).trim())
      .filter(Boolean);
    const fd = data[FILTER_KEY] || { cosmetic: [], exceptions: [] };

    const sels = [];
    for (const r of fd.cosmetic || []) {
      if (siteMatches(r.domains)) sels.push(r.selector);
    }
    sels.push(...custom, ...FALLBACK_SELECTORS);
    const uniq = [...new Set(sels)].slice(0, MAX_COSMETIC);
    combinedSelector = enabled ? combine(uniq) : "";

    const exs = [];
    for (const r of fd.exceptions || []) {
      if (siteMatches(r.domains)) exs.push(r.selector);
    }
    exceptionSelector = combine(exs);
  }

  function restore() {
    for (const el of hidden) {
      try {
        el.style.removeProperty("display");
      } catch (e) {
        /* ignore */
      }
    }
    hidden.clear();
  }

  function hide(el) {
    if (!el || hidden.has(el)) return;
    try {
      if (exceptionSelector && el.matches(exceptionSelector)) return;
    } catch (e) {
      /* ignore */
    }
    hidden.add(el);
    el.style.setProperty("display", "none", "important");
  }

  function scan(root) {
    if (!enabled || !combinedSelector) return;
    try {
      if (root.nodeType === Node.ELEMENT_NODE && root.matches(combinedSelector)) {
        hide(root);
      }
      root.querySelectorAll(combinedSelector).forEach(hide);
    } catch (e) {
      /* ignore */
    }
  }

  // 防抖扫描：避免 MutationObserver 高频触发拖慢页面
  function scheduleScan() {
    if (scanTimer) return;
    scanTimer = setTimeout(() => {
      scanTimer = null;
      scan(document);
    }, 150);
  }

  const observer = new MutationObserver(scheduleScan);

  function startObserver() {
    if (document.documentElement) {
      observer.observe(document.documentElement, { childList: true, subtree: true });
    } else {
      document.addEventListener("DOMContentLoaded", startObserver, { once: true });
    }
  }

  async function init() {
    await buildRules();
    scan(document);
    startObserver();
  }

  chrome.storage.onChanged.addListener((changes, area) => {
    if (area === "local" && (changes[CONFIG_KEY] || changes[FILTER_KEY])) {
      buildRules().then(() => scan(document));
    }
  });

  document.addEventListener("DOMContentLoaded", () => scan(document));

  init();
})();
