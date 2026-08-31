"""pi_ai.py — 真正调用 DeepSeek 模型（对应 pi 的 @pi-ai 包）
用 Python 自带库 urllib，无需 pip 安装任何东西。

怎么用：
  先在系统环境变量设一个 DeepSeek API key（在 platform.deepseek.com 申请）：
     Windows PowerShell:  $env:DEEPSEEK_API_KEY = "sk-你的key"
     或在代码里直接写：   API_KEY = "sk-你的key"   （不推荐提交到 git）
"""
import os
import json
import urllib.request

API_KEY = os.environ.get("DEEPSEEK_API_KEY", "")
BASE_URL = "https://api.deepseek.com/chat/completions"
MODEL = "deepseek-chat"          # DeepSeek 聊天模型别名；如不行可换成 deepseek-reasoner


def call_llm(messages, tools=None):
    """发一次请求给 DeepSeek，返回它这一轮的"消息"（可能含 content 或 tool_calls）。"""
    body = {"model": MODEL, "messages": messages}
    if tools:                       # 有工具就给模型看
        body["tools"] = tools
        body["tool_choice"] = "auto"

    req = urllib.request.Request(
        BASE_URL,
        data=json.dumps(body).encode("utf-8"),
        headers={
            "Authorization": "Bearer " + API_KEY,
            "Content-Type": "application/json",
        },
    )
    with urllib.request.urlopen(req, timeout=60) as resp:
        data = json.loads(resp.read().decode("utf-8"))
    return data["choices"][0]["message"]       # 返回模型这轮的 assistant 消息
