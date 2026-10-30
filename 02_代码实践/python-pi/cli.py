"""cli.py — 命令行入口
用法：
  python cli.py               交互模式（连续对话，/bye 退出）
  python cli.py "你现在几点"   一次性模式（跑一句就退出）
"""
import sys
from agent import run            # 复用 agent.py 里的 run 循环

def main():
    args = sys.argv[1:]          # 命令行参数（没有 = 空列表）
    if args:                     # 一次性模式：有参数 → 跑一句就退出
        print(run(" ".join(args)))
        return
    # 交互模式：没参数 → 连续对话
    print("（输入 /bye 退出）")
    while True:
        try:
            q = input("你: ")
        except (EOFError, KeyboardInterrupt):   # Ctrl+C / 输入中断 → 优雅退出
            print()
            break
        if q.strip() == "/bye":
            break
        print(run(q))


if __name__ == "__main__":
    main()
