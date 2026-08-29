# Git 命令速查 + 返回信息翻译手册

> 用途：日常 95% 的 git 操作，其实只需要 10 条命令、看懂 10 句话。
> 核心认知：**git 的输出 = 「发生了什么」+「当前状态」**。红字不一定是错（git 把正常信息也写在 stderr），黑字也不一定就对。**看内容，别看颜色。**

---

## 一、日常 10 条命令（覆盖 95% 场景）

| 命令 | 作用 | 什么时候用 |
|---|---|---|
| `git status` | 看当前状态 | **每次操作前先敲这个**，心里有底 |
| `git add .` | 把改动加入暂存区 | 改完文件后 |
| `git add 文件名` | 只加指定文件 | 只想提交某几个文件时 |
| `git commit -m "说明"` | 产生一个本地提交 | add 之后 |
| `git push` | 把本地提交传到 GitHub | commit 之后 |
| `git pull` | 把 GitHub 的更新拉下来 | 换电脑/多人协作时 |
| `git log --oneline` | 看提交历史（一行一条） | 想回顾干了什么 |
| `git diff` | 看具体改了什么 | 提交前检查 |
| `git clone 地址` | 把远程仓库复制到本地 | 新电脑/新目录 |
| `git remote -v` | 看仓库链接指向哪 | 怀疑链接断了时 |

**标准循环（每天重复）：**
```
改代码 → git status（确认改了什么）
       → git add .
       → git commit -m "干了什么"
       → git push
```

---

## 二、返回信息翻译表（看到就对照）

### ✅ 成功类（看到就放心）

| 原文 | 翻译 |
|---|---|
| `Everything up-to-date` | 本地和远程一样，没有新东西要传（**成功**） |
| `nothing to commit, working tree clean` | 没有未提交的改动（**干净**） |
| `Your branch is up to date with 'origin/main'` | 本地和 GitHub 同步（**成功**） |
| `xxx..yyy main -> main` | 推送成功：提交已从本地到远程 |
| `1 file changed, 5 insertions(+)` | 1 个文件改了，加了 5 行 |

### ℹ️ 提示类（不是错误）

| 原文 | 翻译 |
|---|---|
| `git: 'xxx' is not a git command` | 命令打错了，看它提示的"最相似命令" |
| `warning: LF will be replaced by CRLF` | Windows 换行符提示，**无害**，忽略 |
| `Your branch is ahead of 'origin/main' by 1 commit` | 你有 1 个提交没推，这时 push 才有事做 |
| `Enumerating/Counting/Writing objects` | 推送进度条，**可无视** |

### ❌ 真错误类（才需要处理）

| 原文 | 翻译 | 对策 |
|---|---|---|
| `fatal: not a git repository` | 当前目录不是仓库 | 检查是否 cd 到了仓库里 |
| `fatal: remote origin already exists` | 重复添加远程 | 用 `git remote set-url` 修改 |
| `Please tell me who you are` | 没设置用户名邮箱 | `git config --global user.name "名字"` + `git config --global user.email "邮箱"` |
| `failed to push some refs` | 推送被拒（远程有你的旧东西） | `git pull` 合并后再 push |
| `CONFLICT` | 冲突：两边改了同一处 | 打开冲突文件，保留想要的，删掉 `<<<<<<<`/`=======`/`>>>>>>>` 标记，再 add+commit |

---

## 三、推送输出逐段翻译（实例）

```
Enumerating objects: 5, done.            → 清点要传的文件（5个）
Counting objects: 100% (5/5), done.      → 数完了
Writing objects: 100% (3/3), 396 bytes   → 打包好了（3个对象，396字节）
Total 3 (delta 0)                        → 一共传3个对象
remote: Resolving deltas: 100% (1/1)     → 远程在接收
To https://github.com/用户名/仓库.git     → 目的地确认
   d553a23..bcdc7cf  main -> main        → ✅ 成功！提交已上传
```

**只需看最后一行**：`main -> main` 就是成功。`xxx..yyy` 里的 `xxx` 是推送前的提交，`yyy` 是推送后的提交（也就是你刚 commit 的那条）。

---

## 四、提交号（hash）是什么

`bcdc7cf` 这种 7 位乱码 = **这次提交的身份证号**（SHA-1 哈希）。
- 每次 commit 自动生成，全球唯一
- `git log --oneline` 用它来指代提交
- 你不需要记它，git 帮你管

---

## 五、遇到看不懂的报错怎么办（三步法）

1. **读一遍报错原文**——git 报错通常已经告诉你答案（`pudh` 它直接提示 `push`）
2. **百度搜报错原文**（不带你自己的路径/文件名）——比如搜 `git failed to push some refs`，中文教程一大把
3. **把报错贴给 AI**——让它给你翻译 + 解决方案

---

## 六、心理建设

- git 是工具，不是考试。**背不住命令完全正常**，查表就行（这份手册就是你的表）
- 报错是 git 在"说话"，不是 git 在"生气"。它比绝大多数软件都友好
- 每个程序员都经历过 `git pudh` 阶段——你现在经历的，所有人当年都经历过
