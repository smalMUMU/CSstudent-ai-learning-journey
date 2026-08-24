// 广告净化器 · 弹窗逻辑（快速开关）
(() => {
  "use strict";

  const STORAGE_KEY = "config";
  const elEnabled = document.getElementById("enabled");
  const elStatus = document.getElementById("status");

  async function init() {
    const data = await chrome.storage.local.get(STORAGE_KEY);
    const cfg = data[STORAGE_KEY] || { enabled: true };
    elEnabled.checked = cfg.enabled !== false;
    renderStatus(cfg.enabled !== false);

    elEnabled.addEventListener("change", async () => {
      const next = { ...cfg, enabled: elEnabled.checked };
      await chrome.storage.local.set({ [STORAGE_KEY]: next });
      renderStatus(elEnabled.checked);
    });

    document.getElementById("openOptions").addEventListener("click", (e) => {
      e.preventDefault();
      chrome.runtime.openOptionsPage();
    });
  }

  function renderStatus(on) {
    elStatus.textContent = on ? "已开启 · 实时过滤中" : "已关闭";
  }

  init();
})();
