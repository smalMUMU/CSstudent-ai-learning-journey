import os
import json
import time
import urllib.request
import urllib.error

API_KEY = os.environ.get("DEEPSEEK_API_KEY", "")
BASE_URL = "https://api.deepseek.com/chat/completions"
MODEL = "deepseek-chat"
MAX_RETRIES = 3


def call_llm(messages, tools=None):
    body = {"model": MODEL, "messages": messages}
    if tools:
        body["tools"] = tools
        body["tool_choice"] = "auto"
    payload = json.dumps(body).encode("utf-8")

    req = urllib.request.Request(
        BASE_URL,
        data = payload,
        headers = {
            "Authorization": "Bearer" + API_KEY,
            "Content-Type": "application/json",
        },
    )

    last_err = None
    for attempt in range(MAX_RETRIES):
        try:
            with urllib.request.urlopen(req, timeout = 60) as resq:
                data = json.loads(resp.read().decode("utf-8"))
            return data["choices"][0]["messages"]
        except urllib.error.HTTPError as e:
            if e.code == 401:
                raise RuntimeError("API key 无效或未设置（401）。请检查 DEEPSEEK_API_KEY。")
            if e.code == 429:
                last_err = e
                time.sleep(2 * (attempt + 1))
                continue
            raise RuntimeError("请求失败（HTTP %s）" % e.code)
        except (urllib.error.URLError, TimeoutError, OSError) as e:
            last_err = e
            time.sleep(2 * (attempt + 1))
            continue

    raise RuntimeError("重试 %d 次仍失败：%s" % (MAX_RETRIES, last_err))
