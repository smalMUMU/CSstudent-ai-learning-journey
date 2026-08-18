// 广告净化器 · 内容脚本（DOM 隐藏引擎）
// 原理：按 CSS 选择器列表隐藏广告元素 + MutationObserver 处理动态注入的广告。
// 这是 B站网页版 MVP 的主力拦截手段（B站广告与内容同源，URL 级过滤效果有限）。
(() => {
  "use strict";

  const STORAGE_KEY = "config";

  // 内置默认选择器（B站网页版广告容器，MVP 起点，站点改版后需更新）
  const DEFAULT_SELECTORS = [
    "#bannerAd", // 播放页顶部横幅广告
    ".ad-report", // 广告卡片（首页/分区/播放页右侧）
    "[id^='creative_']", // 广告创意容器
    ".video-card-ad-small", // 播放页右侧小广告
    ".banner-card", // 顶部横幅卡片
    ".ad-floor-exp", // 楼层广告位
  ];

  const hiddenElements = new WeakSet();
  let selectors = [...DEFAULT_SELECTORS];
  let enabled = true;

  // 从配置读取选择器与开关
  function applyConfig(cfg) {
    cfg = cfg || {};
    enabled = cfg.enabled !== false;
    const custom = (cfg.selectorRules || [])
      .map((s) => (typeof s === "string" ? s.trim() : ""))
      .filter(Boolean);
    selectors = enabled ? [...DEFAULT_SELECTORS, ...custom] : [];
  }

  // 隐藏单个元素（幂等）
  function hide(el) {
    if (!el || hiddenElements.has(el)) return;
    hiddenElements.add(el);
    el.style.setProperty("display", "none", "important");
  }

  // 判断元素是否命中任一选择器
  function matchesAny(el) {
    for (const sel of selectors) {
      try {
        if (el.matches && el.matches(sel)) return true;
      } catch (e) {
        /* 非法选择器，跳过 */
      }
    }
    return false;
  }

  // 扫描 root（含自身与后代）
  function scan(root) {
    if (!enabled || selectors.length === 0 || !root) return;
    if (root.nodeType === Node.ELEMENT_NODE && matchesAny(root)) hide(root);
    for (const sel of selectors) {
      try {
        root.querySelectorAll(sel).forEach(hide);
      } catch (e) {
        /* 非法选择器，跳过 */
      }
    }
  }

  // 监听动态注入的广告节点
  const observer = new MutationObserver((mutations) => {
    if (!enabled) return;
    for (const m of mutations) {
      for (const node of m.addedNodes) {
        if (node.nodeType === Node.ELEMENT_NODE) {
          scan(node);
        }
      }
    }
  });

  function startObserver() {
    observer.observe(document.documentElement || document, {
      childList: true,
      subtree: true,
    });
  }

  // 配置变更实时生效
  chrome.storage.onChanged.addListener((changes, area) => {
    if (area === "local" && changes[STORAGE_KEY]) {
      applyConfig(changes[STORAGE_KEY].newValue);
      scan(document);
    }
  });

  // 初始化
  chrome.storage.local.get(STORAGE_KEY, (data) => {
    applyConfig(data[STORAGE_KEY]);
    scan(document);
    if (document.documentElement) startObserver();
  });
  document.addEventListener("DOMContentLoaded", () => scan(document));
})();
