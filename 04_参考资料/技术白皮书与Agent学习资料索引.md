# 技术白皮书 + 零基础学 Agent 资料索引

> 生成时间：2026-08-25
> ⚠️ 重要说明：本环境**无法直接下载文件**（沙箱无外网 + 本机连不上 GitHub），
> 所以这条不是"已经下载好的文件"，而是**一份带链接的索引**——点开即可看/下载。
> 本机要访问 **GitHub 的资源需开代理**；**CSDN / 官方文档 / 阿里云 / CS50** 等多数不用代理。

---

## 一、零基础学 Agent（大模型智能体）—— 中文优先、免费

| 资源 | 一句话 | 到哪里看 | 是否需代理 |
|---|---|---|---|
| **awesome-ai-agent-learning** | 原创"保姆级"中文教学，**不依赖框架从零构建 Agent**，最适合你这种零基础 | GitHub `2182977liu-bit/awesome-ai-agent-learning` | 🔴 需代理 |
| **Prompt-Engineering-Guide-zh-CN** | 提示词工程（prompt）指南、论文、资源大全，中文 | GitHub `yunwei37/Prompt-Engineering-Guide-zh-CN` | 🔴 需代理 |
| **吴恩达《ChatGPT Prompt Engineering for Developers》中文版** | 吴恩达大模型系列课程，中文笔记，讲清怎么和 LLM 打交道 | GitHub `santiagoTOP/prompt-engineering-for-developers` 或 `MAS-KE/...` | 🔴 需代理 |
| **阿里云「AI Agent 全栈进阶」学习路线专题** | 系统化路线：Agent 是什么 → 一步步怎么学 | `developer.aliyun.com/article/1754230`（网页） | 🟢 不用代理 |
| **《AI Agent 开发：零基础构建复合智能体》** | 清华大学出版社整本书，零基础入门向（需买/找电子版） | 百度百科/购书渠道 | 🟢 不用代理 |

**如果你只挑 1 份**：先看 `awesome-ai-agent-learning`（从零到一讲 Agent 内部怎么搭），配合吴恩达那个提示词课打底。这俩正好对应你"想搞懂 agent 到底怎么工作"的心愿。

---

## 二、新手友好的"技术白皮书 / 权威入门文档"

| 资源 | 一句话 | 到哪里看 | 是否需代理 |
|---|---|---|---|
| **Google 智能体伴侣 技术白皮书（中文版，可下载）** | 一份**真正的技术白皮书**，讲智能体（Agent）架构与落地方向，有中文版 | CSDN 博客（可下载附件） | 🟢 不用代理 |
| **Chrome Extensions 官方 Hello World 教程** | 与你正在做的 **ad-purifier 项目直接对口**，教你最小扩展怎么搭 | `developer.chrome.com/docs/extensions/get-started/tutorial/hello-world` | 🟡 部分 |
| **W3C Web Extensions 规范** | 浏览器扩展的"标准说明书"，想深入看它 | `w3c.github.io/webextensions/specification/` | 🟡 部分 |
| **CS50x（哈佛计算机科学入门）** | 你计划里的课！官方讲义 = 高质量"白皮书"，覆盖编程/算法/计算机组成 | `cs50.harvard.edu/x/` | 🟢 不用代理 |
| **Manifest V3 迁移文档** | 看懂扩展的"身份证" manifest.json（你项目里就有） | Chrome 开发者文档 | 🟡 部分 |

**这组怎么选**：你的主项目是**浏览器扩展**，所以**优先看「Chrome Extensions Hello World」**（读懂它，你就能反过来读懂你的 ad-purifier）；**CS50x 讲义**可以当你的系统课主线。

---

## 三、建议阅读顺序（按你当前情况排）

1. **先吃透「Chrome Extensions Hello World」** —— 和 ad-purifier 同主题，学完能看懂你现有项目结构。
2. **再看 `awesome-ai-agent-learning` 入门** —— 搞懂"agent 是怎么工作的"，这直接回应你"agent 做完我啥也不懂"的痛点。
3. **配合吴恩达提示词课** —— 学会怎么"指挥" AI 输出你想要的东西。
4. **CS50x 当长期主线** —— 每天一讲，用讲义补计算机系统底板。
5. 需要"白皮书"体量、要看 Agent 架构时，再看 **Google 智能体伴侣技术白皮书**。

---

## 四、给你的提醒
- 上面很多是 GitHub 的仓库/文档，**本机要开代理才能访问**；CSDN、阿里云、CS50 官网基本不用。
- 如果你只想**先把 Agent 到底怎么工作**这层搞清楚（你之前的卡点），**首选 `awesome-ai-agent-learning`**，我也可以**直接陪你把它的核心概念读一遍讲给你**——不用你去翻仓库。

---

### 下一步（你选）
- 需要我把某个资源**展开讲给你**听吗？（比如 `awesome-ai-agent-learning` 里"Agent 是什么、由哪几块构成")
- 还是先**把上面的链接一条条试到能打开**，挑出最想深入的那个，我陪你啃？
