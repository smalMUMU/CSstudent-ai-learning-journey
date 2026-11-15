# T02 食堂消费汇总（完整版）—— 我的解答
#
# ⏱️ 限时 25 分钟，到点就停。除 io模板.py 外不看任何资料。
#
# 目标：读入 → 按金额降序、金额相同按姓名升序 → 每行「姓名 金额」→ 最后一行平均分(2位小数)
# 期望输出见 样例输出.txt（5 行数据 + 1 行平均分）
#
# 四个考点：① 跳过空行 ② 二级排序 ③ 输出别带[]和逗号 ④ f"{x:.2f}"
#
# 跑：
#   PowerShell: Get-Content .\样例输入.txt -Encoding UTF8 | python -X utf8 .\我的解答.py
#   CMD:        type 样例输入.txt | python -X utf8 我的解答.py
#
# ---------- 从这里开始写 ----------

n = int(input())
total = 0
pairs = []

while len(pairs) < n:
    line = input()
    if not line.strip():
        continue
    name, v = line.split()
    v = int(v)
    total += v
    pairs.append((name, v))
pairs.sort(key = lambda p: p[0])
pairs.sort(key = lambda p: p[1], reverse = True)

for name, v in pairs:
    print(f"{name} {v}")
print(f"{total / n:.2f}")

#我暂时就写到这里，了处理升序和降序的我不会写，然后至少输出的大致顺序是对的
#我想想先暂时这样还是去问？我写了12分钟

