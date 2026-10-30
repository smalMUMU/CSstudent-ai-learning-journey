"""pi_ai.py — 真正调 DeepSeek（工程化第②步：重试 + 友好报错）
用 Python 自带库 urllib，无需 pip。

用法：
  设环境变量 DEEPSEEK_API_KEY（在 platform.deepseek.com 申请），或直接填 API_KEY。
"""
import os
import json
import time
import urllib.request
import urllib.error

API_KEY = os.environ.get("DEEPSEEK_API_KEY", "")
BASE_URL = "https://api.deepseek.com/chat/completions"
MODEL = "deepseek-chat"
MAX_RETRIES = 3          # 网络/429 时最多重试 3 次


def call_llm(messages, tools=None):
    """发请求给 DeepSeek，返回模型这一轮的"消息"（含 content 或 tool_calls）。
    网络抖动/429 会自动重试；401 直接报 key 错；最终失败抛友好 RuntimeError。
    """
    body = {"model": MODEL, "messages": messages}
    if tools:
        body["tools"] = tools
        body["tool_choice"] = "auto"
    payload = json.dumps(body).encode("utf-8")

    req = urllib.request.Request(
        BASE_URL,
        data=payload,
        headers={
            "Authorization": "Bearer " + API_KEY,
            "Content-Type": "application/json",
        },
    )

    last_err = None
    for attempt in range(MAX_RETRIES):
        try:
            with urllib.request.urlopen(req, timeout=60) as resp:
                data = json.loads(resp.read().decode("utf-8"))
            return data["choices"][0]["message"]       # 成功就返回
        except urllib.error.HTTPError as e:
            if e.code == 401:                           # key 不对，重试也没用
                raise RuntimeError("API key 无效或未设置（401）。请检查 DEEPSEEK_API_KEY。")
            if e.code == 429:                           # 太频繁/额度，等一会儿重试
                last_err = e
                time.sleep(2 * (attempt + 1))
                continue
            raise RuntimeError("请求失败（HTTP %s）" % e.code)
        except (urllib.error.URLError, TimeoutError, OSError) as e:   # 断网/超时
            last_err = e
            time.sleep(2 * (attempt + 1))
            continue

    raise RuntimeError("重试 %d 次仍失败：%s" % (MAX_RETRIES, last_err))
