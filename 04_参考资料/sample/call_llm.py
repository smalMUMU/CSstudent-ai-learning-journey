"""call_llm.py — 用 Python 自带 urllib 发一个 HTTP POST 给 DeepSeek（示例，带注释教学版）
这份代码是学"怎么发 HTTP POST + JSON + Bearer 认证"用的。
运行前：先在环境变量设 DEEPSEEK_API_KEY；或把你自己的 key 填进 API_KEY。
"""
import os
import json
import urllib.request            # ① 借来"发网络请求"的工具

API_KEY = os.environ.get("DEEPSEEK_API_KEY", "")   # ② 从环境变量读 key(密钥)
BASE_URL = "https://api.deepseek.com/chat/completions"
MODEL = "deepseek-chat"


def call_llm(messages, tools=None):
    """把"对话(messages)+工具(tools)"发给 DeepSeek，返回模型这一轮的 message。"""
    # ③ 组装"请求体"：告诉模型 用哪个模型、说些什么、有什么工具
    body = {"model": MODEL, "messages": messages}
    if tools:
        body["tools"] = tools
        body["tool_choice"] = "auto"

    # ④ 把 body 这个 字典 转成 JSON 字符串(网络传输要用文本)
    payload = json.dumps(body).encode("utf-8")

    # ⑤ 发起请求要把"你是谁"告诉对方 → 用 Bearer + 你的 key(通行证)
    req = urllib.request.Request(
        BASE_URL,
        data=payload,                               # POST 的内容(请求体)
        headers={
            "Authorization": "Bearer " + API_KEY,   # API Key 用到的地方(通行证)
            "Content-Type": "application/json",      # 我发的是 JSON
        },
    )

    # ⑥ 把请求发出去，等 DeepSeek 回复
    with urllib.request.urlopen(req, timeout=60) as resp:
        data = json.loads(resp.read().decode("utf-8"))   # 把回复的 JSON 转回字典

    # ⑦ 从回复里，取出模型这一轮的 message(就是 msg)
    return data["choices"][0]["message"]


if __name__ == "__main__":
    # 简单测试：发一句话，打印模型回复（需要 API key）
    print(call_llm([
        {"role": "system", "content": "你是个助手"},
        {"role": "user", "content": "1+1=? 只回答2"},
    ]))
