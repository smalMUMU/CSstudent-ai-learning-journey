# ⛔ 参考代码（AI 生成版 · **冷写之后再看**）

> 2026-11-18 机房小测 5 题。这 5 份代码**不是我写的**，是 AI 写的。
> **冷写之前不要看这个文件。** 冷写完、而且是自己 AC 之后，再回来对照"我写的和它差在哪"。

---

## 1 · 3×3 矩阵对角线之和

```c
#include <stdio.h>

int main() {
    int a[3][3], sum1 = 0, sum2 = 0;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            scanf("%d", &a[i][j]);

    for (int i = 0; i < 3; i++) {
        sum1 += a[i][i];        /* 主对角线 */
        sum2 += a[i][2 - i];    /* 副对角线：2 = N-1 */
    }
    printf("%d %d\n", sum1, sum2);
    return 0;
}
```

**留个问题给自己**：3×3 时 `a[1][1]` 被两条对角线各算了一次 —— 题面要求去重吗？

---

## 2 · 有序数组插入元素（倒序腾位）

```c
#include <stdio.h>

int main() {
    int a[10], x, i;
    for (i = 0; i < 9; i++) scanf("%d", &a[i]);
    scanf("%d", &x);

    for (i = 8; i >= 0 && a[i] > x; i--) {
        a[i + 1] = a[i];        /* 大的往后挪一格，腾出空位 */
    }
    a[i + 1] = x;               /* i 是"最后一个 ≤ x 的下标"，所以填 i+1 */

    for (i = 0; i < 10; i++) printf("%d\n", a[i]);
    return 0;
}
```

**关键点**：循环结束后 `i` 的值是"从后往前扫停下的位置"，**`a[i+1]` 才是要填的格子**。

---

## 3 · 3×3 矩阵原地转置

```c
#include <stdio.h>

void transpose(int a[3][3]) {
    int temp;
    for (int i = 0; i < 3; i++) {
        for (int j = i + 1; j < 3; j++) {     /* 只走上三角 */
            temp = a[i][j];
            a[i][j] = a[j][i];
            a[j][i] = temp;
        }
    }
}

int main() {
    int a[3][3];
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            scanf("%d", &a[i][j]);

    transpose(a);               /* 数组名 = 首地址，所以传 a 就能改原数组 */

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) printf("%d ", a[i][j]);
        printf("\n");
    }
    return 0;
}
```

**关键点**：`j = i + 1` —— 从 0 开始会把换好的又换回去。

---

## 4 · n×n 矩阵绝对值最大元素及位置（多组 + EOF）

```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, a[6][6], max_row, max_col;
    while (scanf("%d", &n) != EOF) {        /* 多组测试标准写法 */
        max_row = 0;                        /* 每组必须重置 */
        max_col = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                scanf("%d", &a[i][j]);
                if (abs(a[i][j]) > abs(a[max_row][max_col])) {
                    max_row = i;
                    max_col = j;
                }
            }
        }
        printf("%d %d %d\n", a[max_row][max_col], max_row + 1, max_col + 1);
    }
    return 0;
}
```

**⚠️ 这份代码的疑点（要自己判断，别照抄）**：
- `a[6][6]` 是**硬编码**的猜测 —— 如果题面 `n` 上限 > 6 就越界。**按题面开。**
- `max_row/max_col` 初值 0 是安全的（至少有元素），但如果矩阵元素可能更小也没关系，因为每读一个都比一次。

---

## 5 · 删除字符串中的非英文字母（快慢指针 + `'\0'` 截断）

```c
#include <stdio.h>

int main() {
    char s[85];
    fgets(s, sizeof(s), stdin);             /* 能吃空格；scanf("%s") 遇空格就停 */

    int j = 0;
    for (int i = 0; s[i] != '\0'; i++) {
        if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
            s[j++] = s[i];                  /* 快指针 i 扫，慢指针 j 写 */
        }
    }
    s[j] = '\0';                            /* ★ 手动截断，否则尾部残留旧字符 */

    printf("%s\n", s);
    return 0;
}
```

**关键点**：`s[j] = '\0'` 这一行是**这题的命门**。C 字符串靠 `'\0'` 判断结尾；
只往前搬字符不补 `'\0'`，尾部旧的、本该被删掉的字符会一起打出来。
