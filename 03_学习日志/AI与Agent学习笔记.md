# 🤖 AI 与 Agent 学习笔记

> 与 leetcode/C 速成课不同，这是一份"我亲手搭 agent"相关的大白话知识笔记
> 一句话核心：**agent = 大脑(LLM) + 循环(loop) + 工具(tools)**

---

## 1️⃣ 什么是 LLM（大语言模型）

- **一句话：一个被海量文字训练出来的"超级接龙机器"**——你给它前文，它预测下文。
- 它不是"思考/理解"也不是"实时搜索"，而是**根据你给的文字，预测最可能的下一句**。
- 缺点：只是"预测"，所以会**一本正经地胡说八道（幻觉）**。
- `qwen3:4b` 就是约 40 亿参数的 LLM；`gpt-4o-mini` 是另一个。

## 2️⃣ 什么是 Agent

任何 agent 都逃不过这 3 块核心：

| 部件 | 干什么 | 对应代码 |
|---|---|---|
| ① **LLM（大脑）** | 负责"想"出回答 | `ollama.chat(...)` / `client.chat.completions.create(...)` |
| ② **循环（loop）** | 反复"问→想→答→再问" | `while True:` |
| ③ **工具（tool）** | 会调用外部能力（读文件、查资料…） | 后面加的 `read_file()` 等 |

> `pi` 这类成熟框架只是把这三块做得很大；最小 agent 就是"LLM + 循环"。

## 3️⃣ messages（对话记录本）结构

```python
messages = [
    {"role": "system", "content": "你是一个有帮助的AI助手。"},  # 系统设定：角色/指令
    {"role": "user",   "content": "什么是AI Agent？"},          # 用户的话
]
```

- **`messages` = 对话记录本**（一个列表）；每一条 `{...}` = 一句话（一个字典）。
- 每个字典两个固定键：**`"role"`**（谁在说）+ **`"content"`**（说了啥）。
- **`role` 只有 3 个固定值**：`"system"`（设定）、`"user"`（用户）、`"assistant"`（AI 回答）。
- **为什么要把历史全给 AI？** 因为 AI 没有记忆，必须把"到这为止的对话"整本塞给它，它才懂上下文。

**易错**：`"content": user_question`（变量，不加引号）≠ `"content": "user_question"`（字面量字符串）。

## 4️⃣ 怎么调用本地 Ollama

```python
import ollama
messages = [{"role":"system","content":"你是一个有帮助的AI助手。"}]
while True:
    q = input("你: ")
    if q == "/bye": break
    messages.append({"role":"user","content":q})
    resp = ollama.chat(model="qwen3:4b", messages=messages)  # 调用本地模型
    answer = resp["message"]["content"]                       # 取回答（Ollama 格式）
    print("助手:", answer)
    messages.append({"role":"assistant","content":answer})    # 把回答也记进去（有记忆）
```

- 装好 Ollama → `ollama pull qwen3:4b` → pip `ollama` 库。
- `resp["message"]["content"]`（Ollama 返回，**没有 choices**）。

## 5️⃣ OpenAI 格式调用（更通用，DeepSeek/通义也这样）

```python
from openai import OpenAI
client = OpenAI()   # 需要一个 API key（环境变量 OPENAI_API_KEY）

response = client.chat.completions.create(
    model=os.getenv("MODEL", "gpt-4o-mini"),  # ① 用哪个模型（默认gpt-4o-mini）
    messages=messages,                         # ② 对话记录本（同上）
    temperature=0.7,                           # ③ 创造性 0~2，越低越严谨
)
answer = response.choices[0].message.content   # 取回答（OpenAI 格式，多了 choices[0]）
```

- **`client.chat.completions.create(...)`**：`client`(接待员)→`.chat`(聊天)→`.completions`(生成)→`.create`(现在生成)。
- **`os.getenv("MODEL", "gpt-4o-mini")`**：去环境变量读 `MODEL`，没有就用默认 `"gpt-4o-mini"`（需要 `import os`）。
- **`temperature`**：越低越确定、越高越有创意；0.7 是折中。

## 6️⃣ ⚠️ 两种返回格式（最容易搞混）

| 库 | 取答案 |
|---|---|
| **Ollama** | `resp["message"]["content"]` |
| **OpenAI / DeepSeek / 通义** | `response.choices[0].message.content` |

## 7️⃣ 几个反复踩的坑（务必记住）

1. **`role` 值**：必须是 `"system"/"user"/"assistant"`，不能填中文句子。
2. **变量 vs 字符串**：`content=user_question`（变量）不是 `"user_question"`（字面量）。
3. **`messages = "messages"`**：参数名和变量名同名时，值必须是**变量**（不带引号），否则报错（`_copy_messages` 那个 ValueError）。
4. **嵌套 = `f(g(x))` 由内到外**：`roman_map[s[i]]` 先算 `s[i]` 再查字典。
5. **`roman_map`（字典变量名）≠ `map()`（内置函数·批量加工）**。

---
