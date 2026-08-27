# 电梯仿真（自动扶梯物理模拟）

用 Python 模拟自动扶梯"速度如何随乘客负载自适应"的项目。

## 做什么
- 物理引擎 `ElevatorModel`：净力 = 电机牵引力 - (重力分量 + 摩擦力 + 风阻)
- 加速度 = 净力 / 总质量，再用欧拉法逐步更新速度
- 乘客 `Passenger`：体重、身高、相对梯子的步行速度

## 文件
- `elevator_model.py`  核心物理引擎（`Passenger` + `ElevatorModel`）
- `run_simulation.py`  控制台运行：逐帧打印速度/质量/加速度
- `plot_speed.py`      matplotlib 动画画"速度-时间曲线"
- `_原始版本/`         整理前原始的 6 个文件，保留备查

## 运行
```
python run_simulation.py     # 控制台仿真
python plot_speed.py         # 画动态速度-时间曲线
```

## 学习建议
1. 先读 `elevator_model.py`：理解类、方法、`update()` 物理更新
2. 再读 `run_simulation.py`：看怎么"用"这个引擎
3. 最后看 `plot_speed.py`：理解怎么把仿真过程画成动画
