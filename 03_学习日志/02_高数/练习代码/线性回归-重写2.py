# 线性回归 · 第 2 次重写（2026-10-29）
#
# 凭记忆，从零写出完整的线性回归训练。
#
# 数据：y = 3x + 2，x = 0,1,2,...,7
# 训练完应该：w ≈ 3，b ≈ 2
#
# ---------------------------------------------------------------
# ⚠️ 不许看任何东西：
#   · T10背景.md
#   · 线性回归练习.py
#   · 训练升级版.py
#   · 线性回归-重写.py（10-22 那版）
#   · 高数速查表.md
#   · 不查 AI
#
# ✅ 卡住就空着 —— 卡在哪，等下告诉我哪。
#    卡住的位置，才是这次重写的价值。

# ---------- 开始写 ----------
xs = list(range(8))
ys = []

def f(x):
    y = 3 * x + 2
    return y

for i in xs:
    ys.append(f(i))

w = 0
b = 0
alpha = 0.001
def dl_dw(w, b):
    s = 0
    for i in range(len(xs)):
        s += -2 * xs[i] * (-w * xs[i] + ys[i] - b)
    return s

def dl_db(w, b):
    s = 0
    for i in range(len(xs)):
        s += -2 * (-w * xs[i] + ys[i] - b)
    return s

for i in range(5001):
    w = w - alpha * dl_dw(w, b)
    b = b - alpha * dl_db(w, b)
print(w, b)