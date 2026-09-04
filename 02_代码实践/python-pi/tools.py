import json
import datetime

TOOLS = [
    {"type": "function", "function": {"name": "calc",
        "description": "计算数学表达式，如 '12*7'",
        "parameters": {"type": "object", "properties": {"expr": {"type": "string"}}, "required": ["expr"]}}},
    {"type": "function", "function": {"name": "now",
        "description": "返回当前时间，如 '2026-09-04 14:30'",
        "parameters": {"type": "object", "properties": {}, "required": []}}},
]

def calc(expr):
    return str(eval(expr))

def now():
    return datetime.datetime.now().strftime("%Y-%m-%d %H:%M")

def run_tool(name, args_json):
    args = json.loads(args_json) if args_json else {}
    if name == "calc":
        return calc(args["expr"])
    if name == "now":
        return now()
    return "未知工具: " + name