# 广告净化器（ad-purifier）

基于规则的实时广告过滤浏览器扩展 —— **B站网页版 MVP**，个人自用工具。

> 孵化项目 · 见《广告净化器-可行性评估.md》（同级目录）
> 核心理念：**不做"预测广告"，只做"实时过滤"**——DOM 隐藏为主，请求过滤为辅，配置以 JSON 保存在本地。

## 功能

- ✅ **DOM 隐藏引擎**（内容脚本）：按 CSS 选择器隐藏广告元素，MutationObserver 兜底动态注入的广告
- ✅ **请求过滤引擎**（background + declarativeNetRequest）：拦截匹配 URL 的广告请求
- ✅ **JSON 配置**：全局开关、自定义选择器、自定义 URL 规则，全部以 JSON 存于 `chrome.storage.local`
- ✅ **实时生效**：配置修改后无需刷新页面，后台与内容脚本立即同步
- ✅ **快速开关**：工具栏弹窗一键启停；选项页支持配置导入/导出

## 安装（开发者模式加载）

1. 打开 Chrome / Edge，访问 `chrome://extensions`（Edge 为 `edge://extensions`）
2. 打开右上角 **开发者模式**
3. 点击 **加载已解压的扩展程序**，选择本目录 `ad-purifier`
4. 访问 `bilibili.com` 任一页面验证效果

## 使用

- 点击工具栏扩展图标：快速开关过滤
- 右键扩展图标 → 选项（或弹窗内"打开设置"）：管理规则
  - **DOM 隐藏规则**：每行一个 CSS 选择器（追加于内置默认选择器之后）
  - **请求过滤规则**：每行一个 urlFilter（支持 `doubleclick.net` 子串 或 `||domain^` 语法）
  - **配置 JSON**：复制 / 从剪贴板导入 / 恢复默认

默认配置形如：

```json
{
  "enabled": true,
  "selectorRules": [],
  "requestRules": []
}
```

## 架构

```
ad-purifier/
├── manifest.json     MV3 清单（权限：storage + declarativeNetRequest）
├── background.js     请求过滤引擎：配置 → DNR 动态规则（实时同步）
├── content.js        DOM 隐藏引擎：选择器 + MutationObserver
├── options.html/js   设置页：开关 + 规则编辑 + JSON 导入导出
├── popup.html/js     工具栏弹窗：快速开关
└── README.md
```

数据流：`options/popup → chrome.storage.local(JSON) → content.js(隐藏) + background.js(DNR 拦截)`

## 已知边界（重要）

- **同源广告拦不住**：B站广告与内容共用 `hdslb.com` 等 CDN 域名，URL 级过滤对广告主内容无效——这正是 DOM 隐藏作为主力的原因
- **选择器需维护**：站点改版后广告容器选择器会失效，需在选项页补充；内置默认选择器为 MVP 起点，需实测验证
- **隐私**：全部本地处理，无任何云端上报；仅记录运行日志于扩展控制台
- **合规**：仅限个人自用；请勿分发

## 后续路线（按可行性评估）

1. 实测并扩充 B站选择器库
2. 接入 EasyList 等开源规则订阅（DOM 隐藏规则 + 请求规则）
3. 覆盖更多网页平台（按平台分目录维护选择器）
4. 手机端另行评估（Android 本地 VPN 模式 / iOS 直接建议现成工具）
