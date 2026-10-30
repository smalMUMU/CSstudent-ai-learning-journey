import sys
from mini_agent import run

def main():
    args = sys.argv[1:]
    if args:
        print(run(" ".join(args)))
        return
    print("（输入 /bye 退出）")
    while True:
        try:
            q = input("你：")
        except (EOFError, KeyboardInterrupt):
            print()
            break
        if q.strip() == "/bye":
            break
        print(run(q))

if __name__ == "__main__":
    main()