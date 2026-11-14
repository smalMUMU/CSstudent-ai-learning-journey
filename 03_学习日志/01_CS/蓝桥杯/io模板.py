# 🧰 IO 模板（蓝桥杯 · 直接复制，别背）

> **用法**：做题时从这里**复制一整块**，改个变量名就用。**这是工具，不是学习材料**——目的是让"语法"不再占你的时间。
> 想背的话，只背 **①** 和 **⑤** 两种。

```python
import sys
from datetime import datetime
```

---

## ① 一次读完（最通用 · 空行/多空格自动吃掉）

**什么时候用**：数据量未知、或有一堆空白乱排的。

```python
data = sys.stdin.read().split()      # data 里全是"字符串"
```

---

## ② 有 n，后面 n 行

```python
n = int(input())
for i in range(n):
    line = input()
    # 用 line
```

---

## ③ 有 n，后面 n 个数字（一行里）

```python
n = int(input())
nums = list(map(int, input().split()))
```

---

## ④ 读到结尾（**没有 n** 的那种）

```python
lines = []
while True:
    try:
        s = input()
    except EOFError:          # 没数据了 → 跳出
        break
    s = s.strip()
    if s:                     # 跳过空行
        lines.append(s)
```

或更短（一次读完后自己切）：

```python
lines = [x for x in sys.stdin.read().split("\n") if x.strip()]
```

---

## ⑤ 每行两个值（姓名 数字）

```python
for i in range(n):
    name, v = input().split()   # 切出来都是字符串
    v = int(v)                  # 要算数就转
```

---

## 输出

```python
print(" ".join(map(str, nums)))   # 空格分隔，一行
print(f"{x:.2f}")                # 保留两位小数
print(int(total))                # 整数题：一定套 int，别打出 .0
```

---

## 时间（零件 #7）

```python
fmt = "%Y-%m-%d %H:%M:%S"
t = datetime.strptime("2022-01-01 07:58:02", fmt)   # 文字 → 时间对象
diff = (t2 - t1).total_seconds()                    # 相减 → 秒（后 − 前！）
```

---

## 本地跑（两条路，按你的窗口选）

```powershell
# PowerShell（提示符带 PS）
Get-Content .\输入.txt -Encoding UTF8 | python -X utf8 .\我的解答.py
```

```
:: CMD（提示符不带 PS）
type 输入.txt | python -X utf8 我的解答.py
```
