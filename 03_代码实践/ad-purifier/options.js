// 广告净化器 · 选项页逻辑
(() => {
  "use strict";

  const STORAGE_KEY = "config";

  const $ = (id) => document.getElementById(id);
  const elEnabled = $("enabled");
  const elSelectors = $("selectors");
  const elRequestRules = $("requestRules");
  const elJson = $("jsonView");
  const elStatus = $("status");

  let current = null;

  // 按行解析文本域
  function lines(text) {
    return text
      .split("\n")
      .map((s) => s.trim())
      .filter(Boolean);
  }

  function buildConfig() {
    return {
      enabled: elEnabled.checked,
      selectorRules: lines(elSelectors.value),
      requestRules: lines(elRequestRules.value),
    };
  }

  function renderJson() {
    elJson.textContent = JSON.stringify(current, null, 2);
  }

  async function save() {
    current = buildConfig();
    await chrome.storage.local.set({ [STORAGE_KEY]: current });
    renderJson();
    elStatus.textContent = "✅ 已保存（本地生效）";
    setTimeout(() => (elStatus.textContent = ""), 2000);
  }

  function loadConfig(cfg) {
    current = cfg || { enabled: true, selectorRules: [], requestRules: [] };
    elEnabled.checked = current.enabled !== false;
    elSelectors.value = (current.selectorRules || []).join("\n");
    elRequestRules.value = (current.requestRules || []).join("\n");
    renderJson();
  }

  async function init() {
    const data = await chrome.storage.local.get(STORAGE_KEY);
    loadConfig(data[STORAGE_KEY]);
    $("btnSave").addEventListener("click", save);
    $("btnExport").addEventListener("click", async () => {
      await navigator.clipboard.writeText(JSON.stringify(current, null, 2));
      elStatus.textContent = "✅ 配置已复制到剪贴板";
      setTimeout(() => (elStatus.textContent = ""), 2000);
    });
    $("btnImport").addEventListener("click", async () => {
      try {
        const text = await navigator.clipboard.readText();
        const parsed = JSON.parse(text);
        if (typeof parsed !== "object" || parsed === null) throw new Error("格式错误");
        loadConfig(parsed);
        await save();
        elStatus.textContent = "✅ 已导入并保存";
        setTimeout(() => (elStatus.textContent = ""), 2000);
      } catch (e) {
        elStatus.textContent = "❌ 导入失败：剪贴板内容不是合法 JSON 配置";
      }
    });
    $("btnReset").addEventListener("click", async () => {
      loadConfig(null);
      await save();
      elStatus.textContent = "✅ 已恢复默认";
      setTimeout(() => (elStatus.textContent = ""), 2000);
    });
  }

  init();
})();
