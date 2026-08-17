import math
import random
import time

class Passenger:
    """乘客个体模型"""
    def __init__(self, name, mass, height, is_walking=False):
        self.name = name
        self.mass = mass          # 体重 (kg)
        self.height = height      # 身高 (m)，影响风阻面积
        self.is_walking = is_walking  # True: 在梯上走动; False: 站着不动
        self.position = 0.0       # 相对位移 (m)
        
    def get_walking_speed(self):
        """人在梯上的相对步行速度（假设走动时相对梯速 1.0 m/s）"""
        return 1.0 if self.is_walking else 0.0

class SmartElevatorSim:
    """
    智能人梯物理仿真引擎（含负载响应、摩擦、风阻）
    """
    def __init__(self, elevator_mass=500, max_power=15000, angle_deg=30, 
                 friction_coeff=0.05, air_density=1.225):
        """
        :param elevator_mass: 电梯空载自重 (kg)
        :param max_power: 电机额定功率 (W)，实际电梯约 15kW
        :param angle_deg: 扶梯倾斜角度 (度)
        :param friction_coeff: 滑动摩擦系数 (钢对橡胶约 0.05)
        :param air_density: 空气密度 (kg/m^3)
        """
        self.elevator_mass = elevator_mass
        self.max_power = max_power
        self.angle = math.radians(angle_deg)
        self.mu = friction_coeff
        self.rho = air_density
        
        # 系统状态变量
        self.time = 0.0
        self.current_speed = 1.2  # 初始空载速度 (m/s)
        self.passengers = []
        
        # 物理常数
        self.g = 9.8
        
    def add_passenger(self, passenger):
        """添加乘客"""
        self.passengers.append(passenger)
        
    def _calculate_total_mass(self):
        """计算总质量（电梯自重 + 所有乘客体重）"""
        human_mass = sum(p.mass for p in self.passengers)
        return self.elevator_mass + human_mass
    
    def _calculate_drag_area(self):
        """
        计算总迎风面积 (m^2)
        假设站立时面积 ≈ 0.5 * height; 走动时姿态略变，面积微增
        """
        total_area = 0.0
        for p in self.passengers:
            # 基础面积：身高 x 0.3 (胸厚估算)
            area = p.height * 0.3
            if p.is_walking:
                area *= 1.1  # 走动时风阻略大
            total_area += area
        return total_area
    
    def _calculate_load_force(self, speed):
        """
        计算总阻力负载 (N)
        = 重力分量 + 摩擦力 + 风阻
        """
        mass = self._calculate_total_mass()
        # 1. 重力沿斜坡分量 (向下滑的力)
        F_gravity = mass * self.g * math.sin(self.angle)
        # 2. 摩擦力 (正压力 * 摩擦系数)
        F_normal = mass * self.g * math.cos(self.angle)
        F_friction = self.mu * F_normal
        # 3. 风阻 (与速度平方成正比)
        area = self._calculate_drag_area()
        F_drag = 0.5 * self.rho * 0.9 * area * (speed ** 2)  # Cd取0.9（人体近似圆柱）
        
        return F_gravity + F_friction + F_drag
    
    def _calculate_motor_force(self, speed):
        """电机牵引力 (N) = 功率 / 速度 (恒功率特性)"""
        if speed < 0.01:  # 防止除以零
            return self.max_power / 0.01
        return self.max_power / speed
    
    def update(self, delta_t=0.1):
        """
        更新物理引擎 (核心迭代函数)
        采用欧拉前向法（Euler Forward）求解速度平衡
        """
        # 1. 获取当前速度
        v = self.current_speed
        
        # 2. 计算当前负载阻力 (与当前速度有关)
        F_load = self._calculate_load_force(v)
        
        # 3. 计算电机能提供的牵引力 (与当前速度有关)
        F_motor = self._calculate_motor_force(v)
        
        # 4. 计算加速度 (牛顿第二定律: F = m * a)
        total_mass = self._calculate_total_mass()
        # 净力 = 牵引力 - 阻力 (若牵引力 > 阻力，加速)
        F_net = F_motor - F_load
        acceleration = F_net / total_mass
        
        # 5. 更新速度 (限定上下限，防止飞车或停止)
        new_speed = v + acceleration * delta_t
        # 物理约束：速度不低于0.1 m/s (避免死锁)，不高于2.5 m/s (安全限速)
        self.current_speed = max(0.1, min(2.5, new_speed))
        
        # 6. 更新乘客位置 (结合梯速 + 人相对梯子的步行速度)
        for p in self.passengers:
            # 位移增量 = (梯速 + 人的相对速度) * 时间
            p.position += (self.current_speed + p.get_walking_speed()) * delta_t
            
        self.time += delta_t
        
    def get_status(self):
        """获取当前状态报告"""
        total_mass = self._calculate_total_mass()
        load = self._calculate_load_force(self.current_speed)
        motor = self._calculate_motor_force(self.current_speed)
        return {
            "时间": round(self.time, 2),
            "当前梯速 (m/s)": round(self.current_speed, 3),
            "总质量 (kg)": round(total_mass, 1),
            "乘客数": len(self.passengers),
            "总阻力 (N)": round(load, 1),
            "电机出力 (N)": round(motor, 1),
            "净加速度 (m/s^2)": round((motor - load)/total_mass, 3)
        }

# ========== 运行仿真 ==========
if __name__ == "__main__":
    # 1. 初始化电梯 (角度30度，功率15kW)
    elevator = SmartElevatorSim(max_power=15000, angle_deg=30)
    
    # 2. 随机生成10个乘客 (体重50-90kg，身高1.6-1.85m)
    names = ["张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十", "冯一", "陈二"]
    for i in range(10):
        is_walking = random.choice([True, False])  # 随机走动或站立
        p = Passenger(
            name=names[i % len(names)],
            mass=random.randint(50, 90),
            height=random.uniform(1.6, 1.85),
            is_walking=is_walking
        )
        elevator.add_passenger(p)
    
    print("🚀 智能扶梯物理仿真启动 (含负载响应 + 风阻 + 人流量)")
    print(f"👥 乘客总数: {len(elevator.passengers)} 人")
    print("=" * 60)
    
    # 3. 模拟运行 20 秒 (每0.2秒更新一次)
    for _ in range(100):
        elevator.update(0.2)
        status = elevator.get_status()
        
        # 动态显示
        print(f"⏱️ {status['时间']}s | 速度: {status['当前梯速 (m/s)']} | "
              f"质量: {status['总质量 (kg)']}kg | 人数: {status['乘客数']} | "
              f"净加速度: {status['净加速度 (m/s^2)']}")
        
        time.sleep(0.05)  # 模拟实时刷新