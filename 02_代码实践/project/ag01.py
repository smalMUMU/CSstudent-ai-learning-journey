from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一个 agent。可用工具:" + str([t["function"]["name"] for t in TOOLS])

def run(user_input):
    messages = [
        {"role": "system","content": SYSTEM},
        {"role":"user","content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)
        messages.append(msg)
        tool_calls = msg.get("tool_calls")


