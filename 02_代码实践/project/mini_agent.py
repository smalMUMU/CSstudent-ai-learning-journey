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