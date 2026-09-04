# pi / Agent 学习总结（日志）

> 这是**学习 AI Agent、亲手用 Python 搭 agent** 的总结日志。以后相关笔记都往这里追加。
> 一句话核心：**agent = 大脑(LLM) + 循环(loop) + 工具(tools)**。

---

## 一、什么是 LLM（大语言模型）
- **一句话：一个被海量文字训练出来的"超级接龙机器"**——你给它前文，它预测下文。
- 它不是"思考/理解"也不是"实时搜索"，而是**根据你给的文字，预测最可能的下一句**。
- 缺点：只是"预测"，所以会**一本正经地胡说八道（幻觉）**。
- `qwen3:4b` 是约 40 亿参数的 LLM；`gpt-4o-mini` 是另一个。

## 二、什么是 Agent（3 块核心）
任何 agent 都逃不过这 3 块：

| 部件 | 干什么 | 对应代码 |
|---|---|---|
| ① **LLM（大脑）** | 负责"想"出回答 | `call_llm(...)` / `client.chat.completions.create(...)` |
| ② **循环（loop）** | 反复"问→想→答→再问" | `while True:` |
| ③ **工具（tool）** | 会调用外部能力（算数、读文件、查资料…） | `TOOLS` + `run_tool(...)` |

> `pi` 这类成熟框架只是把这三块做得很大；**最小 agent 就是"LLM + 循环"**。

## 三、messages（对话记录本）结构
```python
messages = [
    {"role": "system", "content": "你是一个有帮助的AI助手。"},  # 系统设定：角色/指令
    {"role": "user",   "content": "什么是AI Agent？"},          # 用户的话
]
```
- **`messages` = 对话记录本**（一个列表）；每一条 `{...}` = 一句话（一个字典）。
- 每个字典两个固定键：**`"role"`**（谁在说）+ **`"content"`**（说了啥）。
- **`role` 只有固定值**：`"system"`（设定）、`"user"`（用户）、`"assistant"`（AI 回答）、`"tool"`（工具结果）。
- **为什么要把历史全给 AI？** 因为 AI 没有记忆，必须把"到这为止的对话"整本塞给它，它才懂上下文。

**易错**：`"content": user_question`（变量，不加引号）≠ `"content": "user_question"`（字面量字符串）。

## 四、怎么调用模型
### 本地 Ollama
```python
import ollama
messages = [{"role":"system","content":"你是一个有帮助的AI助手。"}]
while True:
    q = input("你: ")
    if q == "/bye": break
    messages.append({"role":"user","content":q})
    resp = ollama.chat(model="qwen3:4b", messages=messages)
    answer = resp["message"]["content"]          # Ollama 格式
    print("助手:", answer)
    messages.append({"role":"assistant","content":answer})   # 记录，才有记忆
```
装好 Ollama → `ollama pull qwen3:4b` → pip `ollama`。

### OpenAI / DeepSeek 格式（更通用）
```python
from openai import OpenAI
client = OpenAI()   # 需要 API key（环境变量 OPENAI_API_KEY）
response = client.chat.completions.create(
    model=os.getenv("MODEL", "gpt-4o-mini"),     # 用哪个模型
    messages=messages,                           # 对话记录本
    temperature=0.7,                             # 创造性 0~2，越低越严谨
)
answer = response.choices[0].message.content     # OpenAI 格式
```
- `client.chat.completions.create(...)`：`client`(接待员)→`.chat`(聊天)→`.completions`(生成)→`.create`(现在生成)。
- `os.getenv("MODEL", "...")`：读环境变量 `MODEL`，没有就用默认值。

### ⚠️ 两种返回格式（最容易搞混）
| 库 | 取答案 |
|---|---|
| **Ollama** | `resp["message"]["content"]` |
| **OpenAI / DeepSeek / 通义** | `response.choices[0].message.content` |

## 五、反复踩的坑（务必记住）
1. **`role` 值**：必须 `"system"/"user"/"assistant"/"tool"`，不能填中文句子。
2. **变量 vs 字符串**：`content=user_question`（变量）不是 `"user_question"`（字面量）。
3. **参数名和变量名同名**：值必须是**变量**（不带引号），否则报错。
4. **嵌套 = `f(g(x))` 由内到外**：`roman_map[s[i]]` 先算 `s[i]` 再查字典。
5. **`roman_map`（字典变量名）≠ `map()`（内置函数·批量加工）**。

## 六、pi 的骨架：工具说明书 TOOLS + SYSTEM
### 模块化 / import
- 每个 `.py` 文件 = 一个**模块**；`from 模块 import 名字` = 从别的文件**拿现成的函数/变量来用**（不用模块前缀）。拆分各司其职，像 pi 拆成各 package。

### 工具说明 TOOLS
- `TOOLS` = 给**模型看的"工具说明书"**（一份 JSON 数据），告诉它"有哪些工具、干嘛、怎么传参"。字段：`name`/`description`/`parameters`。
- **键名固定（`type`/`function`/`name`/`description`/`parameters`），值随工具变**。
- **说明 ≠ 实现**：`TOOLS` 是说明书（给模型看）；真正干活的是 `def calc()`。

### SYSTEM（系统提示词）
- `SYSTEM = "你是一个 agent。可用工具:" + str([t["function"]["name"] for t in TOOLS])`
- = 对话开头的"**身份 + 工具清单**"。后面那串是**列表推导式**：对每个工具取 `name`，生成 `["calc"]`，再 `str()` 转字符串。
- 尾逗号（trailing comma）：**语法上无作用、被忽略**，不占位；好处是**以后在列表末尾加元素更方便**。

## 七、agent 内核（工具调用 + 循环 + 状态）
```python
def run(user_input):
    messages = [                                   # 状态 = 对话历史
        {"role": "system", "content": SYSTEM},
        {"role": "user", "content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)            # 把"数据+工具"发给AI，拿回AI的回话
        messages.append(msg)                        # 记下这步(含 tool_calls)，必须
        tool_calls = msg.get("tool_calls")          # 安全取"要不要调工具"(没有→None)
        if tool_calls:                              # 有清单 → 处理工具
            for tc in tool_calls:
                name = tc["function"]["name"]       # 取工具名
                args_json = tc["function"]["arguments"]  # 取参数(JSON字符串)
                out = run_tool(name, args_json)     # 真正执行工具 → 结果
                messages.append({                   # 结果回填
                    "role": "tool",
                    "tool_call_id": tc["id"],       # 必须对上号，否则API报错
                    "content": out,
                })
            continue                                # 回到下一轮，让AI看到结果
        return msg.get("content")                   # 直接回答 → 结束

if __name__ == "__main__":                          # 只有直接运行时才启动
    print(run(input("你:")))
```

## 八、关键概念逐一
- `msg = call_llm(...)`：**`msg` 是这一行新建的变量**，接住函数返回（`变量 = 函数(...)`）。
- `call_llm`：**发请求给模型 + 返回它的回话**（可能带 `content` 或 `tool_calls`）。是"数据+工具→AI→回话"的往返。
- `msg.get("tool_calls")` vs `msg["tool_calls"]`：`.get()` **没有键就返回 None、不报错**；`[""]` 会 KeyError。`.get()` 是字典通用的"安全取字段"。
- AI 每轮**都会回话给 msg**，只是两种形态：直接回答（有 `content`）或要调工具（有 `tool_calls`、`content` 空）。据此走不同路。
- `for tc in tool_calls:`：`tc` 是**自定义循环变量**（可任意命名）；**不是挑最合适的工具**，而是**AI 已经选好了（清单），把每一个都执行**。
- `args_json = ...`：只是**取参数**（JSON字符串），**不是调用工具**；调用在下一行 `run_tool`。
- `tool_call_id`：**必须**带。因为 API 要求"每条工具结果都要对得上它是哪次调用"（订单号/收据号类比）；**哪怕是单个工具调用**，`role:"tool"` 也必须带 id，否则报错。
- `if __name__ == "__main__":`：**入口守卫**。直接 `python agent.py` 时 `__name__=="__main__"` 才执行；被 `import` 时跳过（避免交互乱触发）。`input("你:")` 显示提示并等你输入。

## 九、一句话总收
> agent 的骨架 = **工具说明书（TOOLS）+ 调模型的桥（call_llm）+ while 循环（问→回话→要工具就做→回填→再问；有答案就停）**。API 是"程序间的桥"，API Key 是"过桥的通行证"。

---

## 十、进度记录（按日期追加）

### 2026-08-25
- **完成**：独立写出真实版 `agent.py`（`call_llm` + `tool_calls` 处理 + 回填），全对——从"知道思路"到"真会写"。
- **理通**：`msg` vs `messages`、`call_llm`、`tool_calls`、`tool_call_id`、`.get()`、缩进逻辑、`continue` vs `return`。
- **周边**：API / API Key / HTTP / HTTPS / dsh 上下文机制 / gitlink（GitHub"打不开的箭头"）。

### 明日计划
1. 写 `mini_agent.py`（纯本地、不用 key，巩固"循环+工具+状态"），跑通 `12*7` / `hello`。
2. 跑通今天写的 `agent.py`（设 `DEEPSEEK_API_KEY` → `python agent.py` → 输 `12*7` / `hello`），验证 **M1**。
3. 进 **M2**：再加 `now`（报时）工具 + 多轮记忆（能连续多步，如"先算 12*7 再问加 3"）。

### 2026-09-04
- 重写基础框架（第 4 遍），修掉 4 处错：`call_llm(messages, TOOLS)` 顺序、漏 `messages.append(msg)`、`"tool_calls"` 字段名、`for tc in tool_calls` 遍历。
- 开始进 **M2**。

---

## 十一、我常犯的错（错题集，写之前先扫一遍）

### 一类：最容易混的"两对"
| 混 | 是什么 | 怎么用 |
|---|---|---|
| `msg` | AI 这轮刚回的那句话（一个字典） | `msg.get("tool_calls")`、`msg.get("content")` |
| `messages` | 一整本对话历史（列表） | `messages.append(msg)` |

| 混 | 是什么 | 怎么用 |
|---|---|---|
| `TOOLS` | 你的**工具说明书**（有哪些工具） | 给 model 看；`[... for t in TOOLS]` 抽名字 |
| `tool_calls` | **AI 决定要调的那批调用**（带参数/id） | `for tc in tool_calls:` |

### 二类：字段名 / 拼写
- `"tool_calls"`（**不是** `"tools"`）
- `"tool_call_id"`（**不是** `"tool_calls_id"`）
- `"role": "tool"`（`tool` 要加引号）
- `tc["id"]`（`id` 要加引号）

### 三类：顺序 / 漏写
- `call_llm(messages, TOOLS)`（先 `messages`，后 `TOOLS`）
- **漏写** `messages.append(msg)`（必须！尤其带 `tool_calls` 时）

### 四类：结构 / 缩进
- 开头用 `messages = [...]`（用 `=` 建列表），**不是** `messages.append(...)`；`append` 一次只加一个元素。
- `continue` 要缩进到 `if tool_calls:` 里面（和 `for` 平级），否则死循环。
- `return msg.get("content")` 在 `if` 外、`while` 里（AI 直接回答时才走）。
- 结尾 `if __name__ == "__main__":`（`__main__` 加引号）。

### 五类：函数名
- `run_tool(...)`（**不是** `tool(...)` / `tun_tool(...)`）——必须是定义时那个确切名字。

---

## 十二、周总结（按周追加）

### 2026-08-25 ~ 2026-09-04 这一周
> 核心一句话：**从零、亲手、独立地写出了"一个 agent 的骨架"，并理解了它每一块在干嘛。**

**主线：亲手用 Python 写出 agent**
- 掌握三块核心：`大脑(call_llm) + 循环(while) + 工具(tools)`。
- `TOOLS` 工具说明书（name/description/parameters）+ `SYSTEM` 系统提示词。
- `messages` 对话历史（`role`+`content`：system/user/assistant/tool）。
- `while` 循环：问 AI → `append` → 看 `tool_calls` → 要工具就执行+回填+`continue` → 有答案就 `return`。
- 关键细节：`msg` vs `messages`、`msg.get("tool_calls")`、`tool_call_id` 对上号、`.get()` 安全取、缩进、`continue` vs `return`。
- **M2**：加多工具（`now`）+ **多轮记忆**（messages 提到外面 + main 的 while 循环）。
- 独立写 `agent.py` **4 遍**写到全对。

**副线：Python + 网络基础**
- `import`/模块化、字典/列表/循环/JSON。
- **HTTP POST**（urllib + JSON + Bearer 认证）。
- **API / API Key**（API=程序间的桥，Key=通行证；什么时候才要 Key）。
- **HTTP vs HTTPS**（明文 vs 加密；身份证/密码等敏感信息的安全常识）。

**周边：工具与安全**
- **dsh**：使用时间峰谷、省钱时间表、上下文机制（100 万窗口 / 80% 压缩 / 缓存）。
- **GitHub**：gitlink/子模块"箭头打不开"是怎么回事、怎么解决。
- **学习资料**：技术白皮书 + Agent 学习资料索引、语法模板卡、我该怎么用 dsh 助手、项目开发提示词。

**学习方法**
- 正视卡点"知道思路但写不出/记不住" → 用**反复默写 + 错题集 + 理解"为什么"**破解。
- 坚持**复述法**（能自己讲清楚才算会）。
- 明白"**能看懂 ≠ 会写**"，最终独立写出完整 agent。
