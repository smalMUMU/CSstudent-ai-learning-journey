"""mini_agent.py — 最小的 agent 核心示例（对标 pi 的 agent-core：工具调用 + 循环 + 状态）
运行：python mini_agent.py
说明：这里 llm() 用"简单规则"模拟大模型的决策，所以不用 API key 也能跑。
      把 llm() 换成真实的 DeepSeek/OpenAI 调用，就成了真正的 AI agent。
"""

# 1) 工具：agent 能调用的"手" = 一个函数 + 一段描述
TOOLS = {"calc": "计算数学表达式，如 '12*7'"}

def calc(expr: str) -> str:
    return str(eval(expr))          # 演示用；真实项目别直接 eval 不可信输入

# 2) 模型(llm)：收到 state，决定"调哪个工具"还是"直接回答"
def llm(state):
    msgs = state["messages"]
    last = msgs[-1]
    if last["role"] == "tool":                       # 刚拿到工具结果 → 总结一句
        return {"answer": "结果是：" + last["content"]}
    user = msgs[1]["content"]
    if any(c in user for c in "+-*/"):               # 需要算 → 决定调用工具
        return {"tool": "calc", "args": user}
    return {"answer": "（模拟回答）你说：" + user}      # 否则直接回答

# 3) agent 循环（pi-agent-core 的核心）
def agent(user_input):
    state = {"messages": [                           # 状态 = 对话历史
        {"role": "system", "content": "你是 agent，可用工具：" + str(TOOLS)},
        {"role": "user", "content": user_input},
    ]}
    while True:
        decision = llm(state)                        # 模型想一步
        if "tool" in decision:                       # 决定调用工具
            state["messages"].append({"role": "assistant", "content": "调用工具 " + decision["tool"]})
            state["messages"].append({"role": "tool", "content": calc(decision["args"])})  # 结果回填
            # 不退出，进入下一轮，让模型看到工具结果
        else:
            return decision["answer"]                # 决定直接回答 → 结束

if __name__ == "__main__":
    print(agent(input("你: ")))
