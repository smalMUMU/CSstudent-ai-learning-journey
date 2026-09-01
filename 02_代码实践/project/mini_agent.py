#第一遍默写
from pi_ai import call_llm
from tools import TOOLS, run_tool

SYSTEM = "你是一名代码助手" + str(t[["function"]["name"] for t in TOOLS])