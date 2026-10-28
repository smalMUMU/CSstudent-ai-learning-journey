"""tools.py — M4 完整版：5 个工具
   calc(算数) / now(报时) / read_file(读文件) / write_file(写文件) / run_code(运行代码)
   结构：① TOOLS 说明书(给模型看) ② def 函数(真正干活) ③ run_tool(按名字分发)
"""
import json
import datetime
import subprocess
import sys

# ========== ① TOOLS 说明书：告诉模型"有哪些工具、怎么传参" ==========
TOOLS = [
    {"type": "function", "function": {
        "name": "calc",
        "description": "计算数学表达式，如 '12*7'",
        "parameters": {"type": "object", "properties": {"expr": {"type": "string"}}, "required": ["expr"]},
    }},
    {"type": "function", "function": {
        "name": "now",
        "description": "返回当前时间，如 '2026-10-27 14:30'",
        "parameters": {"type": "object", "properties": {}, "required": []},
    }},
    {"type": "function", "function": {
        "name": "read_file",
        "description": "读取指定文件内容，如 read_file('D:/doc.txt')",
        "parameters": {"type": "object", "properties": {"path": {"type": "string"}}, "required": ["path"]},
    }},
    {"type": "function", "function": {
        "name": "write_file",
        "description": "把内容写入指定文件（会覆盖原文件），如 write_file('D:/doc.txt','hello')",
        "parameters": {
            "type": "object",
            "properties": {"path": {"type": "string"}, "content": {"type": "string"}},
            "required": ["path", "content"],
        },
    }},
    {"type": "function", "function": {
        "name": "run_code",
        "description": "运行一段 Python 代码并返回输出，如 run_code('print(1+2)')",
        "parameters": {"type": "object", "properties": {"code": {"type": "string"}}, "required": ["code"]},
    }},
]


# ========== ② 造工具：每个函数真正干活 ==========
def calc(expr):
    """计算数学表达式。demo 用 eval；真实项目别直接 eval 不可信输入。"""
    return str(eval(expr))


def now():
    """返回当前时间字符串。"""
    return datetime.datetime.now().strftime("%Y-%m-%d %H:%M")


def read_file(path):
    """读取文件内容。"""
    try:
        with open(path, "r", encoding="utf-8") as f:
            return f.read()
    except Exception as e:
        return "读取失败: " + str(e)


def write_file(path, content):
    """把内容写入文件（覆盖）。"""
    try:
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        return "写入成功: " + path
    except Exception as e:
        return "写入失败: " + str(e)


def run_code(code):
    """运行一段 Python 代码，返回它的输出（stdout + stderr）。"""
    try:
        result = subprocess.run(
            [sys.executable, "-c", code],
            capture_output=True, text=True, timeout=30,
        )
        output = (result.stdout or "") + (result.stderr or "")
        return output.strip() or "(无输出)"
    except Exception as e:
        return "运行失败: " + str(e)


# ========== ③ 调用：按名字分发到对应函数 ==========
def run_tool(name, args_json):
    args = json.loads(args_json) if args_json else {}
    if name == "calc":
        return calc(args["expr"])
    if name == "now":
        return now()
    if name == "read_file":
        return read_file(args["path"])
    if name == "write_file":
        return write_file(args["path"], args["content"])
    if name == "run_code":
        return run_code(args["code"])
    return "未知工具: " + name


if __name__ == "__main__":
    # 单独测一下工具（不用 API key）
    print("calc:", calc("12*7"))
    print("now:", now())
    print("run_code:", run_code("print(1+2)"))
