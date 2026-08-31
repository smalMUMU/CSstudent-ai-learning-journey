# pi 架构拆解与 python-pi 开发计划

> 用《项目开发提示词》流程拆解 pi，指导"用 Python 从零实现一个自己的 agent"。
> 参考源码：`04_参考资料\project01`（pi 单仓库，TypeScript）。

---

## 一、pi 的真实架构（monorepo 包结构）

| pi 的包 | 干嘛 | 你的 `python-pi` 对应 |
|---|---|---|
| **`ai`**（@pi-ai） | 调各种 LLM（多 provider、模型目录、鉴权） | `pi_ai.py`（`call_llm`） |
| **`agent`**（@pi-agent-core） | **核心运行时**：agent-loop 决策 + 工具调用 + 状态 | `agent.py`（`run` 循环） |
| **`coding-agent`** | 交互式 CLI，把上面俩串起来做"编码助手" | （可后加 CLI） |
| `protocol` / `client` / `server` | 消息序列化 + 客户端/服务端架构 | 🟢 以后再说 |
| `tui` / `telemetry` / `evals` | 终端 UI / 遥测 / 评测 | 🟢 可选 |

> 关键：`ai`(脑) + `agent`(循环) + 工具 = agent 的"三块核心"。pi 只是把"很多 provider、很多工具、漂亮 CLI"做大了，**底层心智和几行循环一样**。

---

## 二、MVP（最小能跑版）

> **让 agent 真正跑起来：接上一个真实模型 + 循环 + 1 个工具，问它 "12*7" 能算出 "84"。**
> 砍掉：多 provider、多工具、CLI/TUI、错误重试、流式输出、编码功能。

---

## 三、里程碑（每个都给"能验证的结论"）

| 里程碑 | 要做出什么 | 能验证的结论 |
|---|---|---|
| **M1**（MVP） | 用**一个真实模型**（DeepSeek 或本地 Ollama）+ 现有循环 + `calc` 工具 | 输入 `12*7` 返回 **84**；输入 `hello` 直接回答 ✅ |
| **M2** | **加"多个工具 + 多轮记忆"**：再加 `now`(报时) 工具；`messages` 累积上下文 | 能**连续**做多步：先算 `12*7`→再问"刚才算的加3"→接得上 ✅ |
| **M3** | **让 agent 碰真实世界**：加 **读写文件** 工具（类 pi 的 coding-agent 核心） | 对 agent 说"读 `README.md` 并总结" → 真去读并回答 ✅ |

---

## 四、知识清单（🔴必备 / 🟡建议 / 🟢可选）

**🔴 必备（不会就卡住）**
1. Python：函数/字典/列表/循环/JSON/import —— 已会基础
2. 用代码发一个 **HTTP POST**（urllib/requests + JSON + Bearer 认证）—— 学习ing
3. **Agent 循环概念**（问→想→调工具→回填→再问）—— 已懂
4. **messages 的 role/content/tool** —— 已懂
5. **工具调用协议**（`tool_calls` / `tool_call_id`）—— 已懂

**🟡 建议（更顺）**
- function calling 协议细节（OpenAI 兼容）
- 网络失败/超时的异常处理
- 类型注解、命令行交互（读输入/循环）

**🟢 可选（以后再说）**
- pi 的 client/server、TUI、telemetry、多 provider 抽象
- TypeScript/Node（若要看 pi 源码）

---

## 五、待确认（开工前）
1. 🔴 那 5 项里，**已会的**、**还不牢的**分别是哪些？
2. **接哪个模型**：
   - 用 **DeepSeek API**（需 `DEEPSEEK_API_KEY`，用 `pi_ai.py` 的 `urllib` 方式），或
   - 用**本地 Ollama**（`qwen3:4b`，免费、离线，`ai` 层用 `ollama.chat`）？

确认后从 **M1** 开写。
