import matplotlib.pyplot as plt
import matplotlib.animation as animation
from elevator_model import OnePhysicalElevatorModel
import random

# 1. 创建物理引擎实例
model = OnePhysicalElevatorModel(elevator_mass=500, motor_power=15000, angle_deg=30)

# 2. 添加一些乘客（随机体重和身高）
names = ["张", "李", "王", "赵", "孙", "周", "吴", "郑"]
for i, name in enumerate(names):
    walking_speed = 1.0 if i % 3 == 0 else 0.0  # 每三个人里有一个人走路
    passenger = Passenger(
        name=name,
        mass=random.randint(55, 85),
        height=random.uniform(1.6, 1.85),
        walking_speed=walking_speed
    )
    model.add_passenger(passenger)

# 3. 准备画布
fig, ax = plt.subplots(figsize=(10, 5))
ax.set_xlim(0, 30)   # X轴：时间 0~30秒
ax.set_ylim(0, 2.0)  # Y轴：速度 0~2.0 m/s
ax.set_xlabel("时间 (秒)")
ax.set_ylabel("速度 (m/s)")
ax.set_title("电梯速度-时间曲线 (含负载响应)")
line, = ax.plot([], [], lw=2, color='blue')

# 存储历史数据
time_data = []
speed_data = []

# 4. 动画更新函数（每帧调用）
def update(frame):
    # 运行一步物理仿真（步长0.1秒）
    model.update(0.1)
    
    # 记录当前时间和速度
    time_data.append(model.time)
    speed_data.append(model.current_speed)
    
    # 更新曲线数据
    line.set_data(time_data, speed_data)
    
    # 动态调整X轴范围（让画面跟着时间滚动）
    if model.time > 25:
        ax.set_xlim(model.time - 25, model.time + 5)
    
    return line,

# 5. 运行动画（持续30秒，每秒10帧）
ani = animation.FuncAnimation(fig, update, frames=300, interval=100, blit=True)
plt.show()