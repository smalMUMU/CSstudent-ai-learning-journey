import math


class Passenger:
    """乘客：体重、身高、相对梯子的步行速度"""
    def __init__(self, name, mass, height, walking_speed=0.0):
        self.name = name
        self.mass = mass                 # 体重 (kg)
        self.height = height             # 身高 (m)，影响迎风面积
        self.walking_speed = walking_speed  # 相对梯速 (m/s)，0 表示站着不动
        self.position = 0.0              # 相对位移 (m)


class ElevatorModel:
    """
    自动扶梯物理引擎。
    净力 = 电机牵引力 - (重力分量 + 摩擦力 + 风阻)
    加速度 = 净力 / 总质量
    用欧拉法（Euler Forward）逐步更新速度。
    """

    def __init__(self, elevator_mass=500, motor_power=15000, angle_deg=30,
                 friction_coeff=0.05, air_density=1.225,
                 max_speed=1.5, safety_limit=2.0, max_accel=1.2):
        self.elevator_mass = elevator_mass
        self.motor_power = motor_power
        self.angle = math.radians(angle_deg)
        self.mu = friction_coeff
        self.rho = air_density
        self.max_speed = max_speed
        self.safety_limit = safety_limit
        self.max_accel = max_accel
        self.g = 9.8
        self.time = 0.0
        self.current_speed = 0.0
        self.passengers = []

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
        # 恒功率特性：功率 = 力 x 速度。低速时力会极大，给个下限防除零
        if speed < 1e-3:
            speed = 1e-3
        return self.motor_power / speed

    def update(self, delta_t=0.1):
        total_mass = self._total_mass()
        v = self.current_speed
        load_force = self._load_force(v)
        motor_force = self._motor_force(v)
        acceleration = (motor_force - load_force) / total_mass

        # 限幅，防止启动瞬间加速度过大导致曲线成直角
        if acceleration > self.max_accel:
            acceleration = self.max_accel
        elif acceleration < -self.max_accel:
            acceleration = -self.max_accel

        new_speed = v + acceleration * delta_t
        new_speed = max(0.0, min(self.safety_limit, new_speed))
        self.current_speed = max(0.0, min(self.max_speed, new_speed))

        for p in self.passengers:
            p.position += (self.current_speed + p.walking_speed) * delta_t

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
