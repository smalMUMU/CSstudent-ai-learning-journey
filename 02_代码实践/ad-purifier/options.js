// 广告净化器 · 选项页逻辑
(() => {
  "use strict";

  const STORAGE_KEY = "config";
  const FILTER_KEY = "filterData";

  const $ = (id) => document.getElementById(id);
  const elEnabled = $("enabled");
  const elSelectors = $("selectors");
  const elRequestRules = $("requestRules");
  const elAutoUpdate = $("autoUpdate");
  const elMaxNet = $("maxNetworkRules");
  const elJson = $("jsonView");
  const elStatus = $("status");
  const elSubInfo = $("subInfo");
  const elBtnUpdate = $("btnUpdate");

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
      autoUpdate: elAutoUpdate.checked,
      maxNetworkRules: Math.max(
        0,
        Math.min(4500, parseInt(elMaxNet.value, 10) || 1500)
      ),
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
    current = cfg || {
      enabled: true,
      selectorRules: [],
      requestRules: [],
      autoUpdate: true,
      maxNetworkRules: 1500,
    };
    elEnabled.checked = current.enabled !== false;
    elSelectors.value = (current.selectorRules || []).join("\n");
    elRequestRules.value = (current.requestRules || []).join("\n");
    elAutoUpdate.checked = current.autoUpdate !== false;
    elMaxNet.value = current.maxNetworkRules ?? 1500;
    renderJson();
  }

  // 订阅信息展示
  async function refreshSubInfo() {
    const data = await chrome.storage.local.get(FILTER_KEY);
    const fd = data[FILTER_KEY];
    if (fd && fd.stats) {
      const t = new Date(fd.stats.updatedAt);
      const src = fd.stats.sources && fd.stats.sources.length
        ? fd.stats.sources.join("、")
        : "仅内置规则";
      elSubInfo.textContent =
        `上次更新：${t.toLocaleString()} · 网络规则 ${fd.stats.network} 条` +
        ` · 隐藏规则 ${fd.stats.cosmetic} 条 · 例外 ${fd.stats.exceptions} 条 · 来源：${src}`;
    } else {
      elSubInfo.textContent = "尚未加载规则，点击「立即更新规则」拉取（需联网）";
    }
  }

  async function init() {
    const data = await chrome.storage.local.get(STORAGE_KEY);
    loadConfig(data[STORAGE_KEY]);
    refreshSubInfo();

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

    elBtnUpdate.addEventListener("click", async () => {
      elBtnUpdate.disabled = true;
      elSubInfo.textContent = "正在更新规则（需联网）…";
      try {
        const res = await chrome.runtime.sendMessage({ type: "update-lists" });
        if (res && res.ok) {
          elSubInfo.textContent =
            `✅ 更新完成 · 网络规则 ${res.stats.network} 条 · ` +
            `隐藏规则 ${res.stats.cosmetic} 条 · 例外 ${res.stats.exceptions} 条`;
        } else {
          elSubInfo.textContent = `❌ 更新失败：${res ? res.error : "后台无响应"}`;
        }
      } catch (e) {
        elSubInfo.textContent = `❌ 更新失败：${e.message}`;
      }
      elBtnUpdate.disabled = false;
      refreshSubInfo();
    });

    // 后台更新完成时刷新信息
    chrome.storage.onChanged.addListener((changes, area) => {
      if (area === "local" && changes[FILTER_KEY]) refreshSubInfo();
    });
  }

  init();
})();
