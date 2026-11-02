xs = [for i in range(8)]
ys = []

def f(x):
    y = 3 * xs + 2
    ys.append(y)

w = 0
b = 0
alpha = 0.001
def dl_dw(w, b):
    s = 0
    for i in range(len(xs)):
        s += -2 * xs[i] * (ys[i] - (w * xs[i] + b))
    return s

def dl_db(w, b):
    s = 0
    for i in range(len(xs)):
        s += -2 * (ys[i] - (w * xs[i] + b))
    return s

for i in range(50001):
    w = w - alpha * dl_dw(w, b)
    b = b - alpha * dl_db(w, b)
print(w, b)