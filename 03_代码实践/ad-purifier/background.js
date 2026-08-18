// 广告净化器 · 后台过滤引擎（declarativeNetRequest 动态规则）
// 原理：把配置中的 URL 过滤规则注册为 DNR 动态规则，拦截匹配的广告请求。
// 注意：B站广告与内容同源（hdslb.com），URL 级过滤只能拦第三方广告网络，主力仍是 DOM 隐藏。
(() => {
  "use strict";

  const STORAGE_KEY = "config";
  const RULE_ID_BASE = 1000;

  // 内置默认请求拦截规则（MVP 起点示例，需实测验证）
  const DEFAULT_URL_RULES = [
    { urlFilter: "adservice.google.", description: "Google 广告服务" },
    { urlFilter: "doubleclick.net", description: "Google DoubleClick" },
    { urlFilter: "||2mdn.net^", description: "Google 广告素材" },
  ];

  function buildRule(urlFilter, id) {
    return {
      id,
      priority: 1,
      action: { type: "block" },
      condition: {
        urlFilter,
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

  // 将配置同步为动态规则（先清空再全量重建，保证与配置一致）
  async function syncRules() {
    const data = await chrome.storage.local.get(STORAGE_KEY);
    const cfg = data[STORAGE_KEY] || {};
    const enabled = cfg.enabled !== false;

    const custom = (cfg.requestRules || [])
      .map((r) => (typeof r === "string" ? { urlFilter: r.trim(), description: "自定义" } : r))
      .filter((r) => r && typeof r.urlFilter === "string" && r.urlFilter.trim());

    const all = enabled ? [...DEFAULT_URL_RULES, ...custom] : [];

    const removeRuleIds = (await chrome.declarativeNetRequest.getDynamicRules()).map((r) => r.id);
    const addRules = all.map((r, i) => buildRule(r.urlFilter.trim(), RULE_ID_BASE + i));

    await chrome.declarativeNetRequest.updateDynamicRules({ removeRuleIds, addRules });
    console.log(`[广告净化器] 已同步 ${addRules.length} 条请求过滤规则`);
  }

  // 配置变更实时同步
  chrome.storage.onChanged.addListener((changes, area) => {
    if (area === "local" && changes[STORAGE_KEY]) {
      syncRules().catch(console.error);
    }
  });

  // 安装/启动/唤醒时同步
  chrome.runtime.onInstalled.addListener(() => syncRules().catch(console.error));
  chrome.runtime.onStartup.addListener(() => syncRules().catch(console.error));
  syncRules().catch(console.error);
})();
