# ⚡ Java 速查表（3 分钟复习卡）

> **用法**：**先自己答，答不出再看这里**（"重看"会骗自己）。
> **规矩**：**只收我真栽过、或真讲不清的东西**，不抄教材。新条目往对应栏目里加。
> **配套**：内容和进度分开——**内容在这**，痕迹在 `Java每日总账.md`，别重复劳动。

---

## 一、类型 / 值 vs 引用
（待填）

## 二、类与对象
（待填）

## 三、集合（选型先问：我要什么？）
| 我要什么 | 用什么 | 一句话 |
|---|---|---|
| 有序、可重复、按下标取 | `ArrayList` |  |
| 键 → 值（查得快） | `HashMap` |  |
| 只要"在不在" | `HashSet` |  |
| 先进先出 | `ArrayDeque` / `Queue` |  |

## 四、异常
（待填）

## 五、IO / 文件读写
（待填）

## 六、HTTP 与 JSON
（待填）

## 七、SQL / JDBC
（待填）

## 八、构建与运行
| 目的 | 命令 |
|---|---|
| 编译 | `javac Hello.java` |
| 运行 | `java Hello`（**不带** `.class`） |
| 带中文 | `javac -encoding UTF-8 Hello.java` |

---

## 九、Java ↔ Python：键盘录入（2026-11-07 记）

| | Python | Java |
|---|---|---|
| 导入 | `import math`（**模块**） | `import java.util.Scanner;`（**类**） |
| 能不能直接用 | 能，函数直接调 | **不行**，`Scanner` 要先 `new` 出对象 |
| 读一行 | `input()` | `sc.nextLine()` |
| 读一个数 | `int(input())`：**先读字符串，再转换** | `sc.nextInt()`：**直接给 `int`** |
| 谁决定类型 | **我自己**（转换函数） | **方法名**（`nextInt` / `nextDouble` / `next`） |
| 提示语怎么写 | `input("请输入：")` 一个参数搞定 | Scanner **没有提示参数**，得自己先 `print` |
| 读失败 | `ValueError` | `InputMismatchException` |
| 分隔规则 | **按行** | `nextInt()` **按空白**（空格/换行都算）；`nextLine()` 才按行 |

> ⭐ **一句话带走**：**Python 的 `input()` 只给你字符串，转不转是你的事；Java 的 `nextInt()` 说好了给 `int`，给不出来就直接炸。**
> ⚠️ **两个坑的根在这里**：① `nextInt()` 按空白、`nextLine()` 按行 ⇒ 混用会出现"残留换行被读走"；② 提示语要用 `print`（不换行）而不是 `println`（换行后光标跑了）。

---

## 🔍 5 题自测（每周抽一轮，口头答，不写代码）

> 答不出的，就是这一周要补的那一块。

1. `int a = 5; int b = a;` 和 `String s = "x"; String t = s;`，改 `b` / 改 `s` 分别会影响谁？为什么？
2. `==` 和 `equals` 各在比什么？什么时候必须用 `equals`？
3. `List` 和 `Map`，什么时候用哪个？（用"我要什么"回答，不要用定义回答）
4. 受检异常（`IOException`）和非受检异常（`NullPointerException`），编译器对我的要求有什么不同？
5. 源码、字节码、JVM，三者的顺序和关系是什么？为什么 Java 要多这一层？
