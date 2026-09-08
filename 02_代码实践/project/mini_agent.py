#第一遍默写
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名代码助手" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role": "user","content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                     "content": out,
                     })
            continue
        return msg.get("content")

if __name__ == "__main__":
    print(run(input("你：")))

#第二遍
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名代码助手" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role": "user","content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                }
                )
            continue
        return msg.get("content")

if __name__ == "__main__":
    print(run(input("你:")))


#第三遍
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名编程学习助手" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role": "user","content": user_input},
    ]

    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    print(run(input("你:")))

#第四遍

from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名编程学习助手" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role": "user","content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    print(run(input("你：")))

#第五遍
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名代码学习助手，辅助用户完成代码" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role": "user","content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    print(run(input("你:")))

#第六遍，此版本为M2版本，特点有长期记忆，和多轮对话记忆
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名编程学习助手，辅助用户完成编程学习" + str([t["function"]["name"] for t in TOOLS])

messages = [{"role": "system","content": SYSTEM}]

def run(user_input):
    messages.append({"role": "user","content": user_input})
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    while True:
        user_input = input("你：")
        if user_input == "/bye":
            break
        print(run(user_input))

#第七遍M2版本
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名代码学习助手，主要辅助用户进行编程学习" + str([t["function"]["name"] for t in TOOLS])

messages = [{"role": "system","content": SYSTEM}]

def run(user_input):
    messages.append({"role": "user","content": user_input})
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    while True:
        user_input = input("你：")
        if user_input == "/bye":
            break
        print(run(user_input))

#第八遍M2版本
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名编程学习助手，辅助用户进行代码学习" + str([t["function"]["name"] for t in TOOLS])

messagges = [{"role": "system","content": SYSTEM}]

def run(user_input):
    messages.append({"role": "user","content": user_input})
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")
        if tool_calls:
            for tc in TOOLS:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)
                messages.append({
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out
                })
            continue
        return msg.get("content")

if __name__ == "__main__":
    while True:
        user_input = input("你:")
        if user_input == "/bye":
            break
        print(run(user_input))