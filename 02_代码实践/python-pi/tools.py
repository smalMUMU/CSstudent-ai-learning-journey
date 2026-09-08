"""tools.py — M3：加第三个工具 read_file(读文件)"""
import json
import datetime

TOOLS = [
    {"type": "function", "function": {
        "name": "calc",
        "description": "计算数学表达式，如 '12*7'",
        "parameters": {"type": "object", "properties": {"expr": {"type": "string"}}, "required": ["expr"]},
    }},
    {"type": "function", "function": {
        "name": "now",
        "description": "返回当前时间，如 '2026-09-04 14:30'",
        "parameters": {"type": "object", "properties": {}, "required": []},
    }},
    {"type": "function", "function": {
        "name": "read_file",
        "description": "读取指定文件内容，如 read_file('D:/方案.txt')",
        "parameters": {"type": "object", "properties": {"path": {"type": "string"}}, "required": ["path"]},
    }},
]

def calc(expr):
    return str(eval(expr))

def now():
    return datetime.datetime.now().strftime("%Y-%m-%d %H:%M")

def read_file(path):
    try:
        with open(path, "r", encoding="utf-8") as f:
            return f.read()
    except Exception as e:
        return "读取失败: " + str(e)

def run_tool(name, args_json):
    args = json.loads(args_json) if args_json else {}
    if name == "calc":
        return calc(args["expr"])
    if name == "now":
        return now()
    if name == "read_file":
        return read_file(args["path"])
    return "未知工具: " + name

if __name__ == "__main__":
    print(read_file("f:/GitMUMU/04_参考资料/README.md"))