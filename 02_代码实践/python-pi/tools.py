"""tools.py — agent 能用的"手"（工具）：给模型看的 schema + 实际实现"""
import json

# 给模型看的"工具说明书"：名字、干什么、参数格式（OpenAI 兼容的 function calling 格式）
TOOLS = [
    {
        "type": "function",
        "function": {
            "name": "calc",
            "description": "计算数学表达式，如 '12*7'",
            "parameters": {
                "type": "object",
                "properties": {"expr": {"type": "string"}},
                "required": ["expr"],
            },
        },
    }
]


def calc(expr):
    """真正执行工具。演示用 eval；真实项目别直接 eval 不可信输入。"""
    return str(eval(expr))


def run_tool(name, args_json):
    """按工具名调用对应实现，返回字符串结果。"""
    args = json.loads(args_json) if args_json else {}
    if name == "calc":
        return calc(args["expr"])
    return "未知工具: " + name
