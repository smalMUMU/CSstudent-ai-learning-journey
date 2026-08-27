import random
import time

from elevator_model import ElevatorModel, Passenger


if __name__ == "__main__":
    model = ElevatorModel(elevator_mass=500, motor_power=15000, angle_deg=30)

    names = ["张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十", "冯一", "陈二"]
    for i in range(10):
        walking_speed = 1.0 if i % 3 == 0 else 0.0
        model.add_passenger(Passenger(
            name=names[i % len(names)],
            mass=random.randint(55, 85),
            height=random.uniform(1.6, 1.85),
            walking_speed=walking_speed,
        ))

    print("自动扶梯物理仿真（含负载响应 + 摩擦 + 风阻）")
    print("=" * 60)
    for _ in range(100):
        model.update(0.2)
        s = model.get_status()
        print(f"t={s['time']:5.1f}s | v={s['speed_mps']:5.2f} m/s | "
              f"m={s['total_mass_kg']:6.1f}kg | 人数={s['passenger_count']} "
              f"| a={s['acceleration_mps2']:6.3f} m/s^2")
        time.sleep(0.05)
