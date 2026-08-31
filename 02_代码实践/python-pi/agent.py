"""agent.py — agent 内核（对应 pi 的 @pi-agent-core：工具调用 + 状态 + 循环）
把 mini_agent.py 的"假的 llm()"升级成"真调 DeepSeek"，并处理真模型返回的 tool_calls。
运行：python agent.py
"""
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一个 agent。可用工具: " + str([t["function"]["name"] for t in TOOLS])


def run(user_input):
    messages = [                                  # 状态 = 对话历史
        {"role": "system", "content": SYSTEM},
        {"role": "user", "content": user_input},
    ]
    while True:
        msg = call_llm(messages, TOOLS)           # 真·调 DeepSeek，让它想一步
        messages.append(msg)                      # 记下模型这步（含 tool_calls，很重要）
        tool_calls = msg.get("tool_calls")
        if tool_calls:                            # 模型决定调用工具
            for tc in tool_calls:
                name = tc["function"]["name"]
                args_json = tc["function"]["arguments"]
                out = run_tool(name, args_json)   # 真·执行工具
                messages.append({                 # 把工具结果回填状态
                    "role": "tool",
                    "tool_call_id": tc["id"],
                    "content": out,
                })
            continue                              # 回到下一轮，让模型看到结果
        return msg.get("content")                 # 模型直接回答 → 结束


if __name__ == "__main__":
    print(run(input("你: ")))
