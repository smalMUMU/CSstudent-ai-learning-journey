import json
import datetime
import subprocess
import sys

TOOLS = [
    {"type": "function", "function": {
        "name": "calc",
        "description": "计算数学表达式，如'12 * 7'",
        "parameters": {"type": "object", "properties": {"exper": {"type": "string"}}, "required": ["exper"]},
    }},
    {"type": "function", "function": {
        "name": "now",
        "description": "返回当前时间，如'2026-10-27 11:49'",
        "parameters": {"type": "object", "properties": {}, "required": []},
    }},
    {"type": "function", "function": {
        "name": "read_file",
        "description": "读取指定文件内容，如 read_file('D:/方案.txt')",
        "parameters": {"type": "object", "properties": {"path": {"type": "string"}}, "required": ["path"]},
    }},
    {"type": "function", "function": {
        "name": "write_file",
        "description": "把内容写入指定文件（会覆盖原文件）,如 write_file('a.txt','hello')",
        "parameters": {
            "type": "object",
            "properties": {"path": {"type": "string"}, "content": {"type": "string"}},
            "required": ["path", "content"]
        },
    }},
    {"type": "function", "function": {
        "name": "run_code",
        "description": "运行一段python代码并返回输出，如 run_code('print(1 + 2)')",
        "parameters": {"type": "object", "properties": {"code": {"type": "string"}}, "required": ["code"]},
    }},
]

def calc(expr):
    return str(eval(expr))

def now():
    return datetime.datetime.now().strftime("%Y-%m-%d %H:%M")

def read_file(path):
    try:
        with open(path, "r", encoding = "utf-8") as f:
            return f.read()
    except Exception as e:
        return "读取失败：" + str(e)

def write_file(path, content):
    try:
        with open(path, "w", encoding = "utf-8") as f:
            f.write(content)
        return "写入成功:" + path
    except Exception as e:
        return "写入失败：" + str(e)

def run_code(code):
    try:
        result = subprocess.run(
            [sys.executable, "-c", code],
            capture_output = True, text = True, timeout = 30,
        )
        output = (result.stdout or "") + (result.stderr or "")
        return output.strip() or "(无输出)"
    except Exception as e:
        return "运行失败:" + str(e)

def run_tool(name, args_json):
    args = json.loads(args_json) if args_json else {}
    if name == "calc":
        return calc(args["exper"])
    if name == "now":
        return now()
    if name == "read_file":
        return read_file(args["path"])
    if name == "write_file":
        return write_file(args["path"], args["content"])
    if name == "run_code":
        return run_code(args["code"])
    return "未知工具：" + name

if __name__ == "__main__":
    print(read_file("f:/GitMUMU/04_参考资料/README.md"))