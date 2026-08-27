import random

import matplotlib.pyplot as plt
import matplotlib.animation as animation

from elevator_model import ElevatorModel, Passenger


if __name__ == "__main__":
    model = ElevatorModel(elevator_mass=500, motor_power=15000, angle_deg=30)
    names = ["张", "李", "王", "赵", "孙", "周", "吴", "郑"]
    for i, name in enumerate(names):
        walking_speed = 1.0 if i % 3 == 0 else 0.0
        model.add_passenger(Passenger(
            name=name,
            mass=random.randint(55, 85),
            height=random.uniform(1.6, 1.85),
            walking_speed=walking_speed,
        ))

    fig, ax = plt.subplots(figsize=(10, 5))
    ax.set_xlim(0, 30)
    ax.set_ylim(0, 2.0)
    ax.set_xlabel("时间 (秒)")
    ax.set_ylabel("速度 (m/s)")
    ax.set_title("电梯速度-时间曲线（含负载响应）")
    line, = ax.plot([], [], lw=2, color="blue")
    time_data, speed_data = [], []

    def update(frame):
        model.update(0.1)
        time_data.append(model.time)
        speed_data.append(model.current_speed)
        line.set_data(time_data, speed_data)
        if model.time > 25:
            ax.set_xlim(model.time - 25, model.time + 5)
        return line,

    ani = animation.FuncAnimation(fig, update, frames=300, interval=100, blit=True)
    plt.show()
