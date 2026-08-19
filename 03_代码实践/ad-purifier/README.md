# 广告净化器（ad-purifier）

基于规则的实时广告过滤浏览器扩展 —— **B站网页版**，个人自用工具。

> 孵化项目 · 见《广告净化器-可行性评估.md》（同级目录）
> 核心理念：**不做"预测广告"，只做"实时过滤"**——DOM 隐藏为主，请求过滤为辅，配置以 JSON 保存在本地。

## 功能

- ✅ **DOM 隐藏引擎**（内容脚本）：内置 B站选择器库 + EasyList 订阅隐藏规则（按站点匹配）+ 用户自定义选择器，合并为组合选择器一次匹配；MutationObserver 防抖扫描动态广告；支持例外规则（`#@#`）
- ✅ **请求过滤引擎**（background + declarativeNetRequest）：内置精选网络规则 + EasyList 订阅规则 + 用户自定义，拦截 bilibili 页面发起的广告请求
- ✅ **EasyList 规则订阅**：自动更新（每 7 天）/ 手动更新（选项页一键），多镜像源容错
- ✅ **JSON 配置**：全局开关、自定义选择器/URL 规则、订阅设置，全部以 JSON 存于 `chrome.storage.local`；支持导入导出
- ✅ **实时生效**：配置/规则变更后无需刷新页面，后台与内容脚本立即同步
- ✅ **快速开关**：工具栏弹窗一键启停；关闭时自动恢复已隐藏的元素

## 安装（开发者模式加载）

1. 打开 Chrome / Edge，访问 `chrome://extensions`（Edge 为 `edge://extensions`）
2. 打开右上角 **开发者模式**
3. 点击 **加载已解压的扩展程序**，选择本目录 `ad-purifier`
4. 访问 `bilibili.com` 任一页面验证效果

> 首次使用建议在选项页点击「立即更新规则」拉取 EasyList 订阅（需联网，国内网络若镜像不通可换源或仅用内置规则）。

## 使用

- 点击工具栏扩展图标：快速开关过滤
- 右键扩展图标 → 选项（或弹窗内"打开设置"）：
  - **DOM 隐藏规则**：每行一个 CSS 选择器（追加于内置与订阅规则之后）
  - **请求过滤规则**：每行一个 urlFilter（`doubleclick.net` 子串 或 `||domain^` 语法）
  - **规则订阅**：自动更新开关、订阅网络规则上限（DNR 上限 5000，默认截断到 1500）、立即更新
  - **配置 JSON**：复制 / 从剪贴板导入 / 恢复默认

默认配置形如：

```json
{
  "enabled": true,
  "selectorRules": [],
  "requestRules": [],
  "autoUpdate": true,
  "maxNetworkRules": 1500
}
```

## 架构

```
ad-purifier/
├── manifest.json        MV3 清单（storage + declarativeNetRequest + unlimitedStorage）
├── background.js        后台引擎：订阅下载/解析/缓存 + DNR 动态规则同步
├── filter-parser.js     EasyList 子集解析器（importScripts 加载，可独立测试）
├── content.js           DOM 隐藏引擎：组合选择器 + 例外规则 + 防抖扫描
├── options.html/js      设置页：开关 + 规则编辑 + 订阅管理 + JSON 导入导出
├── popup.html/js        工具栏弹窗：快速开关
├── lists/
│   ├── bilibili.txt     B站选择器库（内置，随扩展分发，需随站点改版维护）
│   └── network.txt      精选第三方广告网络规则（内置兜底）
└── README.md
```

数据流：`options/popup → chrome.storage.local(JSON) → content.js(隐藏) + background.js(DNR 拦截)`

订阅源（按序尝试镜像）：EasyList China + EasyList、EasyPrivacy（ABP CDN / GitHub raw）。

## 规则解析支持范围

| 语法 | 支持 | 说明 |
|---|---|---|
| `||domain^` | ✅ | 网络规则，保留锚定语法直接喂给 DNR |
| `domain##sel` / `##sel` | ✅ | 元素隐藏（含站点作用域匹配） |
| `#@#sel` | ✅ | 隐藏例外 |
| `#?#...`（程序化过滤） | ❌ | 解析时跳过（:has 等复杂规则） |
| `@@` 网络例外 | ❌ | 解析时跳过 |
| `$` 选项 / `/regex/` | ❌ | 解析时跳过/忽略 |

## 已知边界（重要）

- **同源广告拦不住**：B站广告与内容共用 `hdslb.com` 等 CDN 域名，URL 级过滤对广告主内容无效；软广（原生广告）任何规则都抓不住——DOM 隐藏是主力
- **选择器需维护**：站点改版后广告容器选择器会失效，需在 `lists/bilibili.txt` 或选项页补充；内置列表标注"待验证"的条目需实测确认
- **网络规则截断**：EasyList 完整网络规则约数万条，超出 DNR 5000 上限，订阅规则按"上限"设置截断（默认取前 1500 条）
- **隐私**：全部本地处理，无任何云端上报；订阅下载仅访问列表镜像
- **合规**：仅限个人自用；请勿分发

## 后续路线（按可行性评估）

1. 实测并扩充 B站选择器库（把"待验证"条目确认/清理）
2. 验证 EasyList 订阅在真实网络下的可用性，必要时增加国内可达镜像
3. 覆盖更多网页平台（按平台维护 lists/*.txt）
4. 手机端另行评估（Android 本地 VPN 模式 / iOS 直接建议现成工具）
