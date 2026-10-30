import ast

files = [
    r"F:\GitMUMU\02_代码实践\python-pi\cli.py",
    r"F:\GitMUMU\02_代码实践\python-pi\agent.py",
    r"F:\GitMUMU\02_代码实践\python-pi\tools.py",
    r"F:\GitMUMU\02_代码实践\python-pi\pi_ai.py",
]
for f in files:
    try:
        src = open(f, encoding="utf-8").read()
        ast.parse(src, filename=f)
        print("OK:", f.split("\\")[-1])
    except SyntaxError as e:
        print("SYNTAX ERROR:", f.split("\\")[-1], "->", e)
