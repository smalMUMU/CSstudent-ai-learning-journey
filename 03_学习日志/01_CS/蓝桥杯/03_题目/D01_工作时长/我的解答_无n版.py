# D01 工作时长 · 无 n 版（原版真题形态 · 限时 35 分钟）
#
# ── 和带 n 版的唯一区别 ──
#   没有 n。所以不能 for i in range(n)，要"读到结尾"。
#   参考 ../../io模板.py 的 ④（一次读完 / 或 while + try/except）
#
# ── 算法部分完全不变（你已经会的三步）──
#   排序 → 步长 2 两两配对 → 累加秒数 → 打印
#
# ── 两个自检点 ──
#   ① 先打印 len(所有行) —— 必须是【偶数】（上下班成对；奇数=读漏了或空行没滤掉）
#   ② 配对是 times[i+1] - times[i]（后减前），减反了会打出负数
#
# ── 跑 ──
#   样例只有 4 条，先本地验证必须还是 44433：
#   PowerShell: Get-Content .\样例输入.txt -Encoding UTF8 | python -X utf8 .\我的解答_无n版.py
#   CMD:        type 样例输入.txt | python -X utf8 我的解答_无n版.py
#
#   注意：样例输入.txt 没有 n（第一行就是时间），正好用来测无 n 版。
#
# ── 期望输出 ──
#   44433

# ---------- 从这里开始写 ----------

#这里我尝试一下用while的解法吧因为其他解法我都不记得了忘记完了
from datetime import datetime

times = []
while True:
    try:
        n = input()
    except:
        break
    times.append(datetime.strptime(n, "%Y-%m-%d %H:%M:%S"))

times.sort()
total = 0
for i in range(0, len(times), 2):
    total += int((times[i + 1] - times[i]).total_seconds())

print(total)

#我的记忆只能支撑我写这么多了，我现在感觉我只知道流程但是写不出来语法