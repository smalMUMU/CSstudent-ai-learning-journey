#M3学习样本
import json
import datetime

TOOLS = [
    {
        "type": "function",
        "function":{
        "name": "calc",
        "description": "计算数学表达式， 如 '12*7'",
        "parameters": {
            "type": "object", 
            "properties": {"expr": {"type": "string"}},
            "required": ["expr"]
            },
        },
    }
]