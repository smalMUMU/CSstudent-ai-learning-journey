import sys
from datetime import datetime

FMT = "%Y-%m-%d %H:%M:%S"

# ① 一次读完，跳过空行（蓝桥杯里空行很常见）
lines = [l.strip() for l in sys.stdin if l.strip()]

# ② 全部转成 datetime，再排序 —— 打乱顺序的题，先排序是万能第一步
times = sorted(datetime.strptime(l, FMT) for l in lines)

# ③ 两两配对（步长 2），累加每一段的秒数
total = 0
for i in range(0, len(times), 2):
    total += int((times[i + 1] - times[i]).total_seconds())

print(total)
