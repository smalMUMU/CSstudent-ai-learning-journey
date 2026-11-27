# 📇 练习代码索引（INDEX）

> **用途**：110 个 `.java` 文件在同一个文件夹里，靠名字找不出来。这张表是唯一的入口。
> **建表**：2026-11-19 ｜ **顺手删掉了 2 个空壳**（`jiujingyuanzehethisguanjianzi04` / `shuangseqiucaipiaoxitonglianxi08`）
> **规矩**：新增文件后，**自己在这里加一行**（一行就够：文件名 / 干什么 / 哪一集）。

---

## 🗂️ 目录结构（**英文目录名** · ✅ 2026-11-19 已执行）

```
练习代码/黑马JAVA视频跟练/
  INDEX.md                  ← 本文件
  01_basics/                51 个：变量 · 类型 · 录入 · 运算符 · 判断 · 循环
  02_array/                 13 个：一维 · 二维
  03_method/                30 个：方法 + 综合练习 01–07 + 冷写
  04_oop/                   16 个：类 · 封装 · 构造 · this · 对象数组 · 增删改查
  05_string/                （2026-11-20 新建）字符串：API 文档 · 构造 · 常量池 · 比较 · 常用方法
  06_collection/            （2026-11-25 新建）集合：ArrayList ·（后续 HashSet / HashMap）
  07_project_student/        （2026-11-25 新建）**第一个完整项目**：学生管理系统（基础版 + 升级版）
```

> 📌 **以后每开一个新章节，就新建一个 `0N_xxx` 目录**（英文名），并在本文件加一个分区。

> ✅ **移动后已做过全量编译验证**：四个目录整批 `javac` **全部通过**（110 个文件，0 失败）。
> ⚠️ 教训：验证时**不能逐个文件单独编译** —— 测试类（`CarTest`→`Car`）会找不到同目录的类，那是验证方法的错，不是文件坏了。
> ⚠️ `.java` 文件名必须等于 `public class` 名，**不能改名**；目录名不影响编译（都不写 `package`）。
> 💡 IDEA 会跟随文件夹结构，**不需要重开项目**，最多 `Build → Rebuild Project`。

---

## 01 基础语法（变量 / 类型 / 录入 / 运算符 / 判断 / 循环）

| 文件 | 干什么 |
|---|---|
| `value.java` | 第一次编译运行（三行输出，中文不乱码） |
| `valuedemo.java` | 制表符 `'\t'` 和 `"\t"` 的区别 |
| `Varydemo01.java` | 变量基本功：重复打印、重新赋值、一行声明多个 |
| `gong.java` | 公交车人数（`+=` / `-=` 累加器） |
| `shuju01.java` | **8 种基本类型全覆盖**（含 `L` / `F` 后缀） |
| `shuchu09.java` | 变量练习①个人信息（String/int/char/double/boolean） |
| `shudian09.java` | 变量练习②电影信息 |
| `shushou09.java` | 变量练习③手机信息 |
| `luru11.java` | **Scanner 入门**：读一个整数 + 提示语 |
| `lianxi11.java` | Scanner 读两个数求和 |
| `yunsuan103.java` | 四则运算 + 小数参与的差异 |
| `chuyu.java` | 整数除法 / 取余（注释里写了"四舍五入还是截断"的疑问） |
| `suanlian103.java` | 拆三位数：个 / 十 / 百 |
| `suanfu06.java` | 拆三位数（带标签输出） |
| `zhuanhuan0405.java` | **强转要给整个表达式加括号**（自己撞出来的结论） |
| `yunsuan07.java` | 自增 `i++` / `++i` 的取值时机 |
| `yunsuan08.java` | `+=` 的隐式强转（`short s += 1` 能过） |
| `ylian08.java` | 关系运算符 + 注释里提出"Scanner 要强化" |
| `luoji09.java` | 四种逻辑运算符真值表（`&` `\|` `^` `!`） |
| `duanlu10.java` | `&` 不短路的实验（**缺 `&&` 对照组**） |
| `luolianxi10.java` | `\|\|` 短路练习（判断是否有 6） |
| `sanyuan10.java` | 三元求较大值 |
| `laohulianxi10.java` | 三元：老虎体重是否相同 |
| `sanyuanlianxi10.java` | 三元：三个和尚谁最高 |
| `panduan02.java` | if / else（酒量） |
| `panduan03.java` | if / else（钱够不够） |
| `panlianxi03.java` | if / else（支付金额） |
| `pandianying03.java` | 嵌套 if：电影票奇偶（**缺"编号非法"出口**） |
| `xiaoming04.java` | 成绩分档（**含非法输入出口 ✅**） |
| `shangpinglianxi04.java` | 会员折扣（外层范围判断多余） |
| `swtichmian05.java` | switch 菜单（`break` 版） |
| `switchjianfei05.java` | switch：周几运动 |
| `switch06.java` | switch **箭头语法**（JDK14+） |
| `lianxixuanze06.java` | 菜单（箭头 switch） |
| `lianxi1switch06.java` | 工作日/休息日（**范围用 if，自己判断对了**） |
| `forlianxi107.java` | for 打印 5 次 |
| `shuchu07.java` | for 倒序打印 |
| `chonglianlianxi07.java` | for + 字符串拼接 |
| `qiuhe07.java` | for 累加 1..5（累加思想） |
| `xunhuan08.java` | 1..100 偶数和 |
| `lianxixunhuan08.java` | 3 和 5 整除计数（**统计思想**；缺 `return` 中止） |
| `while09.java` | while 打印 1..100 |
| `whilelianxi209.java` | 折纸超珠峰（不知道次数 → while） |
| `huiwenshulianxi10.java` | 空壳（在 LeetCode 写的，仅留注释） |
| `shangheyushu10.java` | 不用 `/` `%` 求商余（**商打印成了除数，被样例掩盖**） |
| `kongzhiyuju01.java` | `continue` 演示（"continue 不是继续"的理解修正） |
| `feng7Guolianxi02.java` | 逢七过（`continue` 实战） |
| `pingfanggenglianxi03.java` | 循环逼近求平方根 |
| `panduanzhishu04.java` | 质数判断（**`i*i < number` 边界 bug**） |
| `caishuzixiaoyouxi05.java` | 猜数字（**先读后判，给了 4 次机会**） |

---

## 02 数组（一维 / 二维）

| 文件 | 干什么 |
|---|---|
| `shuzu01.java` | 数组三种初始化写法 |
| `shuzulianxi02.java` | 遍历数组 |
| `shujzuxiaolianxi02.java` | 数组求和 |
| `shuzutongjigeshu02.java` | 统计能被 3 整除的个数 |
| `shuzubianhualianxi02.java` | 偶数除 2 / 奇数乘 2（一变一印分两个循环） |
| `shuzhulianxi1.java` | 最大值（**`max = arr[0]`，不能用 0**） |
| `shuzuisuijilianxi2.java` | 随机 10 个数 + 平均 + 低于平均的个数 |
| `shuzujiaohuan3.java` | 数组反转（单变量版） |
| `shuzulianxijiaohuan3.java` | 数组反转（**双指针版，黑马写法**） |
| `shuzulianxidaluanshuju4.java` | 随机打乱（洗牌 / Fisher–Yates 变体） |
| `erweishuzulianxishuxi09.java` | **不规则二维数组**遍历（`arr[i].length`） |
| `erweishuzudongtaichushihua.java` | 二维动态初始化 `new int[3][5]` + 默认值 |
| `erweishuzuxiaolianxi09.java` | 二维按行求和（季度营业额，`arr[i]` 当一维数组传参） |

---

## 03 方法（含综合练习 01–07 与冷写）

| 文件 | 干什么 |
|---|---|
| `jiandandefangfadiaoyong02.java` | 无参方法调用 |
| `fangfadiaoyong02.java` | 方法定义与调用（playgame） |
| `fangfajiandanlianxi02.java` | 无参方法：内部求和并打印 |
| `fangfadaicanshu03.java` | 带参方法（求和） |
| `canshudechangfxzhouchangqiuhe03.java` | 带参：矩形周长 |
| `qiuyuandemianji03.java` | 带参：圆面积（**`nextInt()` 读 double 的坑**） |
| `jidusuanfanhuizhi04.java` | 有返回值的**三种调用方式** |
| `bijiaochangfxdemianji04.java` | 比较两矩形面积 ⭐ **"方法三问"的出处** |
| `fangfachongzai06.java` | **方法重载**（4 个 `compare`） |
| `fangfalianxi07.java` | 练习：打印成 `[1,2,3]` 格式 |
| `fangfalianxi207.java` | 练习：随机数求最大值 |
| `fangfalianxichunzailianxi07.java` | 练习：查找是否存在（`int` 返回 1/-1，**该用 boolean**） |
| `fangfakaobeishuzulianxi08.java` | 拷贝数组（长度/边界修过一轮） |
| `feijipiaolianxizonghe01.java` | 综合 01：**买机票**（四档折扣 + 月份/座位校验） |
| `panduanzhishu02.java` | 综合 02：100–200 找质数（**`j*j < i` 导致 121/169 漏检**） |
| `yanzhengmalianxi03.java` | 综合 03：**验证码**（ASCII 运算 `(char)(97+i)`） |
| `shuzuyuansufuzhi04.java` | 综合 04：数组复制 + 校验（**"只比内容"测不出共享**） |
| `pingweidafen05.java` | 综合 05：评委打分（自己版，**除数写成 4**） |
| `pingweilianxiheimabanben05.java` | 综合 05：评委打分（黑马版，**`if (...);` 多分号**） |
| `shuzijiamilianxi06.java` | 综合 06：数字加密（跟敲版） |
| `shuzijiamilianxiduli06.java` | 综合 06：数字加密（独立版） |
| `zhengshutianjiajinshuzu06.java` | 整数拆位成数组（先数位数再倒着填） |
| `shuzijieminixiang06.java` | 综合 06：**解密**（靠"≤4 说明溢出过"逆向还原，自己推的） |
| `choujiangdediyizhongshixianfangshi07.java` | 综合 07：抽奖 A —— **拒绝采样** |
| `choujianglianxi07.java` | 综合 07：抽奖 B —— **洗牌后顺序发奖** |
| `shuangseqiuilianxi08.java` | 综合 08：**双色球**（跟敲版，红球比对 `arr[i]` 的 bug） |
| `shuangseqiulenxie.java` | 综合 08：**双色球冷写版** ✅ 三条判据全过（蓝球比对曾写成常数 6） |
| `shuzufanzhuanlenxie.java` | 冷写：数组反转 ✅ |
| `qiushuzuzuidazhihezuixiaozhi.java` | 冷写：求最大值/最小值 ✅ |
| `zhishulenxie.java` | 冷写：质数判断 ✅（**`i*i <= num` 第一次一次写对**；缺 `1` 的特判） |

---

## 04 面向对象（类 / 封装 / 构造 / this / 对象数组 / 增删改查）

| 文件 | 干什么 |
|---|---|
| `mianxiangduixiang02.java` | 类与对象入门（手机：字段 + 方法） |
| `testduixiang02.java` | 测试类：`new` → 赋值 → 调方法 |
| `Gfriend.java` | **封装改造**（`private` + get/set + `setAge` 校验 + `this`） |
| `duixiangshiyonglianxi.java` | 使用封装后的对象 + 测非法值 |
| `User.java` | 构造方法练习（无参 + 带参；**get/set 没写，等于封死**） |
| `Student.java` | **六步作业**：3 字段 + 2 构造 + get/set + 校验 |
| `wenzigedoulianxi.java` | 格斗游戏·角色类（`attack(Role)` + `gender`/`face` + `showRoleInfo`） |
| `Gametest.java` | 格斗游戏·测试类（互相攻击 + KO） |
| `Good.java` | 商品类（对象数组 02） |
| `TestGood.java` | 对象数组：创建/赋值/遍历（**自测"打印对象得到地址"**） |
| `Car.java` | 汽车类 |
| `CarTest.java` | 对象数组 + 键盘录入（读入 3 台车） |
| `Phon.java` | 手机类 |
| `PhonTest.java` | 对象数组：求和 + 平均（**主动写 `sum * 1.0`**） |
| `Sstudent.java` | 学生类（增删改查用，含 `setAge` 校验） |
| `TestSstudent.java` | ⭐ **增 / 删 / 改 / 查 四件事**：`getCount` / `creatNewArr`（扩容）/ `indexPosition`（返回 -1）/ `printArr`（判空） |

---

## ⚠️ 有已知 bug 但**故意保留**的文件（学习留痕）

| 文件 | 留着的原因 |
|---|---|
| `panduanzhishu04.java` | `i*i < number` ⇒ 4、9 判成质数。**同一坑后来又犯两次**，留作证据 |
| `panduanzhishu02.java` | 同上 `j*j < i` ⇒ 121、169 漏检 |
| `shangheyushu10.java` | 商打印成除数 `b`，被 `100/10` 的样例掩盖 |
| `caishuzixiaoyouxi05.java` | 先读输入再判次数 ⇒ 给 4 次机会 |
| `shuzuyuansufuzhi04.java` | 校验"只比内容"⇒ 测不出两个变量指向同一数组 |
| `pingweidafen05.java` | 除数写死 4（数组长度 10） |
| `pingweilianxiheimabanben05.java` | `if (max < x);` 多分号（已修） |
| `duanlu10.java` | 缺 `&&` 对照组（结论正确但没验证） |
| `fangfalianxichunzailianxi07.java` | `int` 返回 1/-1 当布尔（C 习惯） |
| `User.java` | 只有构造没有 get/set（自己注明"不想写了"） |

---

## 🔁 冷写记录（详见《冷写日程》）

| 冷写 | 对象 | 结果 |
|---|---|---|
| 1 | 双色球（`shuangseqiulenxie.java`） | ✅ 2026-11-17 通过（乱序 + 只中蓝球两条判据实测） |
| 2 | 数组反转 / 求最值 / 质数（三个 `*lenxie.java`） | ✅ 2026-11-14 全过 |

---

## 📌 维护约定

1. **新文件 → 在这里加一行**（文件名 / 干什么 / 哪一集）
2. **改了旧文件 → 说一句"我改了 XXX"**（我这边不会自动重读旧文件）
3. **有 bug 但故意不改的** → 归到上面「留痕清单」
