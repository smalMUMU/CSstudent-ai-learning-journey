# D01 工作时长 · 带 n 版（今天的练习）
#
# 和真题的区别：第一行多给一个 n（记录条数）。这样你只需要最朴素的三件套。
#
# ── 目标 ──
#   读入 n 条打卡时间 → 排序 → 两两配对（步长 2）→ 累加秒数 → 打印总秒数
#
# ── 今天只允许用这三个读入零件 ──
#   input()         要一行，拿到字符串
#   .split()        按空格切开
#   int()           字符串 → 数字
#
# ── 时间要变成能相减的东西 ──
#   from datetime import datetime
#   t = datetime.strptime("2022-01-01 07:58:02", "%Y-%m-%d %H:%M:%S")
#   （两个 datetime 相减 → timedelta → .total_seconds() 得秒数）
#
# ── 测试（在 D01_工作时长 目录下）──
#   Get-Content .\样例输入_带n.txt -Encoding UTF8 | python -X utf8 .\我的解答_带n版.py
#
# ── 期望输出 ──
#   44433

# ---------- 从这里开始写 ----------
from datetime import datetime

n = int(input())
times = []

for i in range(n):
    line = input()
    t = datetime.strptime(line, "%Y-%m-%d %H:%M:%S")
    times.append(t)

print(len(times))