# minipi_ai.py 逐行详解（含名词解释）

> 这个文件 = **"怎么发请求给 DeepSeek、拿回模型回复"**，带**重试 + 友好报错**。
> 是 agent 的"**对话层**"：agent.py 的 `call_llm(messages, TOOLS)` 调的就是这里的 `call_llm`。
> ⚠️ 文中会标出**你抄写时手滑的 4 个 bug**（不改跑不通）。

---

## 一、先看整体：这文件干 4 件事
1. **准备配置**：key、地址、模型名、重试次数（7~10 行）。
2. **定义 `call_llm` 函数**（13 行起）——它负责"发请求、拿回复"。
3. 函数里**组装请求**（14~27 行）：请求体 + JSON 编码 + 请求对象。
4. **发请求 + 重试 + 报错**（29~48 行）：★ 这是你重点要看的部分。

---

## 二、名词速查（先扫一遍术语）
| 名词 | 意思 |
|---|---|
| **`None`** | Python 的"空/无"（表示"没有值"），不是 0、不是空字符串 |
| **`range(3)`** | 生成序列 `0, 1, 2`（配合 for 用，循环 3 次） |
| **`for attempt in range(3)`** | 循环 3 次，每次 `attempt` = 0、1、2 |
| **`try / except`** | **尝试**做某事；**出错**就跳去 except 处理（不让程序崩） |
| **`raise`** | **主动抛出**一个错误（告诉上层"出事了"） |
| **`RuntimeError`** | Python 的一种"运行时错误"类型；我们用它来抛"人话错误" |
| **`HTTPError`** | 服务器**回了**，但状态码是错误（如 401、429） |
| **`URLError`** | **没连上**（断网、DNS、连接失败） |
| **`TimeoutError` / `OSError`** | 超时 / 底层系统 IO 错误 |
| **`e.code`** | HTTP 错误里的"**状态码**"（401、429…） |
| **401** | **未授权**（key 不对/没设） |
| **429** | **请求太频繁 / 额度限制** |
| **`time.sleep(n)`** | **暂停 n 秒**（"等一会儿"） |
| **`continue`** | 跳过本轮剩下代码，**直接进入下一轮循环**（=重试） |
| **`e`** | except 里接住的"错误对象"（里面有错误码/信息） |

---

## 三、逐行详解

### 【第 1~5 行】导入模块
```python
import os                 # 读环境变量（key）
import json               # 字典 ↔ JSON 文本 转换
import time               # 等待（time.sleep）
import urllib.request     # 造请求、发请求（Request / urlopen）
import urllib.error       # 错误类型（HTTPError / URLError）
```

### 【第 7~10 行】配置常量
```python
API_KEY = os.environ.get("DEEPSEEK_API_KEY", "")   # 读环境变量里的 key；没设=空串
BASE_URL = "https://api.deepseek.com/chat/completions"  # 发到哪（接口地址）
MODEL = "deepseek-chat"                            # 用哪个模型
MAX_RETRIES = 3                                    # 最多尝试 3 次
```

### 【第 13 行】定义函数（不是"调用"）
```python
def call_llm(messages, tools=None):
```
- `def` = **定义**一个函数（把"调模型"的代码打包）。
- `messages` = 参数：对话历史（列表）。
- `tools=None` = 参数：工具清单，**默认 None（不传也行）**。
- 这一行**不执行**，只是"造"这个函数；别处 `call_llm(...)` 才是"用"它。

### 【第 14~17 行】组装"请求体 body"
```python
body = {"model": MODEL, "messages": messages}   # 请求体：用哪个模型 + 对话
if tools:                                        # 如果传了工具
    body["tools"] = tools                        #   把工具清单放进去
    body["tool_choice"] = "auto"                 #   让模型"自动决定"用不用工具
```
- `body` = **请求体 = 要发出去的内容**（"信的正文"）。
- `tool_choice: "auto"` = 让模型**自己决定**要不要调工具（不是我们点名）。

### 【第 18 行】转成 JSON + 编码成字节
```python
payload = json.dumps(body).encode("utf-8")
```
拆两步：
1. `json.dumps(body)` = **字典 → JSON 字符串**（`'{"model": ...}'`）。
2. `.encode("utf-8")` = **字符串 → 字节(bytes)**，按 UTF-8。
- 为什么？因为**网络只能传字节**，不能传字符串。

### 【第 20~27 行】造"请求对象"
```python
req = urllib.request.Request(   # 创建一个请求对象（"填好一张快递单"）
    BASE_URL,                   # ① 发到哪
    data = payload,             # ② 要发的数据（请求体，不是回复！）
    headers = {                 # ③ 请求头（身份+格式）
        "Authorization": "Bearer " + API_KEY,   # 通行证：Bearer + 你的key
        "Content-Type": "application/json",      # 我发的是 JSON
    },
)
```
> ⚠️ **BUG①（你抄的）**：第 24 行 `"Bearer" + API_KEY` **`Bearer` 后少空格**！应为 `"Bearer " + API_KEY`（多个空格），否则变 `Bearersk-xxx`，认证失败。
> 注意：`Request(...)` 只是**装信封**（还**没寄**），寄是下面的 `urlopen`。

### 【第 29~30 行】★ 重试的准备（重点）
```python
29:     last_err = None                  # 准备一个"记住最后一次错误"的变量，先设空(None)
30:     for attempt in range(MAX_RETRIES):  # 循环 MAX_RETRIES 次（attempt = 0,1,2）
```
- **第 29 行 `last_err = None`**：
  - `last_err` = 变量名，含义"**最后的错误**"。
  - `None` = Python 的"**空/无**"（表示"现在还没有错误"）。
  - 作用：后面每次重试失败，就把那次错误存进 `last_err`；等 3 次全失败后，用 `last_err` 告诉用户"最后一次是啥错"。
- **第 30 行 `for attempt in range(MAX_RETRIES):`**：
  - `for ... in ...` = 循环。
  - `range(3)` = 生成 `0, 1, 2`（3 个数字 = 3 次）。
  - `attempt` = 每轮的"第几次尝试"（0=第1次，1=第2次，2=第3次）。
  - `:` = 循环开始；**缩进进去的代码会执行 3 次**。

### 【第 31~34 行】发请求（try 部分）
```python
31:     try:                                        # 尝试做下面的请求
32:         with urllib.request.urlopen(req, timeout=60) as resq:  # 发请求，最多等60秒
33:             data = json.loads(resp.read().decode("utf-8"))      # 把回复 JSON→字典
34:         return data["choices"][0]["messages"]   # 返回模型这条消息
```
- **第 32 行**：`urlopen(req, timeout=60)` = **把请求真正发出去 + 收回复**；`timeout=60` = **最多等 60 秒**（超时报错）。`with ... as resq` = 拿到结果叫它 `resq`。
- **第 33 行**：`resp.read()` = 读取回复的**原始字节**；`.decode("utf-8")` = 字节→字符串；`json.loads(...)` = 字符串→字典。
- **第 34 行**：`data["choices"][0]["message"]` = 从回复里**逐层取**"模型那条消息"。

> ⚠️ **BUG②③④（你抄的）**：
> - 第 32 行 `as resq:` → 拼错，应为 **`as resp:`**；而且第 33 行用了 `resp`（没定义）→ **NameError**。
> - 第 34 行 `["messages"]` → 应为 **`["message"]`**（API 是**单数** message，复数会 **KeyError**）。

### 【第 35~42 行】捕获"HTTP 错误"（401/429）
```python
35:     except urllib.error.HTTPError as e:   # 如果服务器回了"错误状态码"
36:         if e.code == 401:                 # 401 = key 不对
37:             raise RuntimeError("API key 无效或未设置（401）。请检查 DEEPSEEK_API_KEY。")
38:         if e.code == 429:                 # 429 = 太频繁/额度
39:             last_err = e                  #   记住这个错
40:             time.sleep(2 * (attempt + 1)) #   等 2/4/6 秒
41:             continue                      #   直接下一轮（重试）
42:         raise RuntimeError("请求失败（HTTP %s）" % e.code)  # 其他码：直接报错
```
- `except urllib.error.HTTPError as e` = 如果"服务器回了错误状态码"，接住错误对象 `e`。
- `e.code` = 状态码（401/429/500…）。
- **401**：`raise RuntimeError("...")` = **直接抛人话错误**（不重试，因为 key 错重试没用）。
- **429**：`last_err = e` 记住；`time.sleep(2*(attempt+1))` 等 2/4/6 秒；`continue` **跳过本轮剩下的、直接下一轮**（=重试）。
- 其他状态码：`raise RuntimeError("请求失败（HTTP %s）" % e.code)` 直接报错（`%s` 会填成状态码）。

### 【第 43~46 行】捕获"网络错误"（断网/超时）
```python
43:     except (urllib.error.URLError, TimeoutError, OSError) as e:  # 没连上/超时/IO错误
44:         last_err = e                  # 记住错误
45:         time.sleep(2 * (attempt + 1)) # 等 2/4/6 秒
46:         continue                      # 重试
```
- `URLError` = 没连上；`TimeoutError` = 超时；`OSError` = 底层 IO 错误。
- 这几个都**可能恢复**，所以：记住错误 → 等一会儿 → `continue` 重试。

### 【第 48 行】重试全失败的最终报错
```python
48:     raise RuntimeError("重试 %d 次仍失败：%s" % (MAX_RETRIES, last_err))
```
- 如果循环 3 次都失败、都没 return/raise，就走到这行。
- `"重试 %d 次仍失败：%s" % (MAX_RETRIES, last_err)` = 把"`%d`换次数、`%s`换最后错误"拼成一句人话。
- `raise` = **抛给上层**（agent.py 会显示这行错误）。

---

## 四、整体流程图（一次 call_llm 的旅程）
```
组装 body（模型+对话+工具）
   ↓ json.dumps → encode（字典→JSON字符串→字节）
造请求对象 req（地址+数据+头部）
   ↓ 循环最多 3 次：
   ├─ 尝试 urlopen 发请求（等60秒）
   │    成功 → 解析 JSON → 返回 message ✓（结束）
   │    401 → 直接抛"key 错"（不重试）✗
   │    429 → 等2/4/6秒 → 下一轮（重试）
   │    网络错误/超时 → 等2/4/6秒 → 下一轮（重试）
   │
   └─ 3 次都失败 → raise "重试3次仍失败：最后一次错误"
```

## 五、一句话
> `minipi_ai.py` = **"发请求给 DeepSeek + 拿回复"**；重点 `29~48` 行是**重试机制**：先设 `last_err=None`，再 `for` 循环最多 3 次——401 直接报错，429/网络错误就"等一会儿（sleep）+ continue 重试"，全失败才抛最终错误。

## 六、你要改的 4 个 bug（不改跑不通）
| 行 | 你写的 | 改成 |
|---|---|---|
| 24 | `"Bearer" + API_KEY` | `"Bearer " + API_KEY`（加空格） |
| 32 | `as resq:` | `as resp:` |
| 33 | `resp.read()`（但上面是 resq） | 跟 32 行一致（都叫 `resp`） |
| 34 | `["messages"]` | `["message"]`（单数） |
