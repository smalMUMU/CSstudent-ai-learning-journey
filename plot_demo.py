import math
import random
import time


class Passenger:
    """Passenger with walking relative speed and position."""

    def __init__(self, name, mass, height, walking_speed=0.0):
        self.name = name
        self.mass = mass
        self.height = height
        self.walking_speed = walking_speed
        self.position = 0.0


class OnePhysicalElevatorModel:
    """
    Unified physical model for a passenger elevator / moving walkway.

    The motion is driven by the net force:
        F_net = F_motor - (F_gravity + F_friction + F_drag)
    and the kinematic update is:
        a = F_net / m_total
        v_{t+1} = v_t + a * dt
    """

    def __init__(
        self,
        elevator_mass=500,
        motor_power=15000,
        angle_deg=30,
        friction_coeff=0.05,
        air_density=1.225,
        max_speed=1.5,
        safety_limit=2.0,
    ):
        self.elevator_mass = elevator_mass
        self.motor_power = motor_power
        self.angle = math.radians(angle_deg)
        self.mu = friction_coeff
        self.rho = air_density
        self.max_speed = max_speed
        self.safety_limit = safety_limit

        self.time = 0.0
        self.current_speed = 0.0
        self.passengers = []
        self.g = 9.8
        self.max_accel = 1.2  # 最大加速度限制 (m/s^2)

    def add_passenger(self, passenger):
        self.passengers.append(passenger)

    def _total_mass(self):
        return self.elevator_mass + sum(p.mass for p in self.passengers)

    def _drag_area(self):
        area = 0.0
        for p in self.passengers:
            base_area = p.height * 0.3
            if p.walking_speed > 0:
                base_area *= 1.1
            area += base_area
        return area

    def _load_force(self, speed):
        mass = self._total_mass()
        gravity_force = mass * self.g * math.sin(self.angle)
        normal_force = mass * self.g * math.cos(self.angle)
        friction_force = self.mu * normal_force
        drag_force = 0.5 * self.rho * 0.9 * self._drag_area() * (speed ** 2)
        return gravity_force + friction_force + drag_force

    def _motor_force(self, speed):
        if speed < 1e-3:
            speed = 1e-3
        return self.motor_power / speed

    def update(self, delta_t=0.1):
        total_mass = self._total_mass()
        v = self.current_speed
        load_force = self._load_force(v)
        motor_force = self._motor_force(v)

        net_force = motor_force - load_force
        acceleration = net_force / total_mass
                # 对加速度进行限幅（防止瞬间弹射导致曲线成直角）
        if acceleration > self.max_accel:
            acceleration = self.max_accel
        elif acceleration < -self.max_accel:
            acceleration = -self.max_accel

        new_speed = v + acceleration * delta_t
        new_speed = max(0.0, min(self.safety_limit, new_speed))
        self.current_speed = max(0.0, min(self.max_speed, new_speed))

        for passenger in self.passengers:
            passenger.position += (self.current_speed + passenger.walking_speed) * delta_t

        self.time += delta_t

    def get_status(self):
        total_mass = self._total_mass()
        load_force = self._load_force(self.current_speed)
        motor_force = self._motor_force(self.current_speed)
        acceleration = (motor_force - load_force) / total_mass

        return {
            "time": round(self.time, 2),
            "speed_mps": round(self.current_speed, 3),
            "total_mass_kg": round(total_mass, 1),
            "passenger_count": len(self.passengers),
            "load_force_n": round(load_force, 1),
            "motor_force_n": round(motor_force, 1),
            "acceleration_mps2": round(acceleration, 3),
        }


if __name__ == "__main__":
    model = OnePhysicalElevatorModel(elevator_mass=500, motor_power=15000, angle_deg=30)

    names = ["A", "B", "C", "D", "E", "F", "G", "H"]
    for i, name in enumerate(names):
        walking_speed = 1.0 if i % 3 == 0 else 0.0
        passenger = Passenger(name=name, mass=random.randint(55, 85), height=random.uniform(1.6, 1.85), walking_speed=walking_speed)
        model.add_passenger(passenger)

    print("Unified physical elevator model")
    print("-" * 60)

    for _ in range(30):
        model.update(0.2)
        status = model.get_status()
        print(
            f"t={status['time']:5.1f}s | "
            f"v={status['speed_mps']:5.2f} m/s | "
            f"m={status['total_mass_kg']:6.1f} kg | "
            f"a={status['acceleration_mps2']:6.3f} m/s^2"
        )
        time.sleep(0.05)
        # ===== 以下是画图代码（接在文件末尾） =====
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import random

model = OnePhysicalElevatorModel(elevator_mass=500, motor_power=15000, angle_deg=30)

names = ["张", "李", "王", "赵", "孙", "周", "吴", "郑"]
for i, name in enumerate(names):
    walking_speed = 1.0 if i % 3 == 0 else 0.0
    passenger = Passenger(name=name, mass=random.randint(55, 85), height=random.uniform(1.6, 1.85), walking_speed=walking_speed)
    model.add_passenger(passenger)

fig, ax = plt.subplots(figsize=(10, 5))
ax.set_xlim(0, 30)
ax.set_ylim(0, 2.0)
ax.set_xlabel("时间 (秒)")
ax.set_ylabel("速度 (m/s)")
ax.set_title("电梯速度-时间曲线")
line, = ax.plot([], [], lw=2, color='blue')
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