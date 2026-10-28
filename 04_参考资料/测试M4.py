"""测试 M4 的 5 个工具 + agent 循环逻辑（不联网、不用 key）"""
import sys

sys.path.insert(0, r"F:\GitMUMU\02_代码实践\python-pi")
import tools

print("=== 1) 工具函数测试 ===")
print("calc(12*7)      :", tools.calc("12*7"))
print("now()           :", tools.now())
print("run_code        :", tools.run_code("print(1+2)"))
print("read_file       :", tools.read_file(r"F:\GitMUMU\04_参考资料\README.md").strip()[:30], "...")

# write_file 测到临时文件
print("write_file      :", tools.write_file(r"F:\GitMUMU\04_参考资料\_test_write.txt", "hello M4"))
print("读回刚才写的    :", tools.read_file(r"F:\GitMUMU\04_参考资料\_test_write.txt"))

print()
print("=== 2) run_tool 分发测试 ===")
print('run_tool("calc", {expr:2+3}) :', tools.run_tool("calc", '{"expr":"2+3"}'))
print('run_tool("now")              :', tools.run_tool("now", "{}"))

print()
print("=== 3) agent 循环逻辑（用假模型 mock，不联网）===")
messages = [{"role": "system", "content": "you are agent"}]
messages.append({"role": "user", "content": "12*7等于多少"})


def fake_call_llm(msgs, tools_arg=None):
    """假模型：第一轮决定调 calc；看到工具结果后直接回答"""
    has_tool_result = any(m.get("role") == "tool" for m in msgs)
    if not has_tool_result:
        return {"role": "assistant", "content": None,
                "tool_calls": [{"id": "call_1", "type": "function",
                                "function": {"name": "calc", "arguments": '{"expr":"12*7"}'}}]}
    return {"role": "assistant", "content": "结果是 84"}


result = None
for step in range(5):
    msg = fake_call_llm(messages)
    messages.append(msg)
    tool_calls = msg.get("tool_calls")
    if tool_calls:
        for tc in tool_calls:
            name = tc["function"]["name"]
            args_json = tc["function"]["arguments"]
            out = tools.run_tool(name, args_json)
            print("  [工具执行] %s(%s) -> %s" % (name, args_json, out))
            messages.append({"role": "tool", "tool_call_id": tc["id"], "content": out})
        continue
    result = msg.get("content")
    break

print("最终回答:", result)
print()
print("=== 循环/工具/回填：", "✅ 通过" if result == "结果是 84" else "❌ 有问题")
