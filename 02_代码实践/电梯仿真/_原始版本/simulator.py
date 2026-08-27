import time

class MotionSimulator:
    """
    人梯运动模拟器（模板类）
    可用于模拟同向或反向运动，只需修改速度的正负号。
    """
    def __init__(self, person_speed, elevator_speed, initial_position=0, direction="down"):
        """
        初始化状态
        :param person_speed: 人相对于电梯的步行速度（正数）
        :param elevator_speed: 电梯的运行速度（正数）
        :param initial_position: 起始位置（米）
        :param direction: "down" 表示向下为正方向，"up" 表示向上为正方向
        """
        self.person_speed = abs(person_speed)
        self.elevator_speed = abs(elevator_speed)
        self.position = initial_position
        
        # 核心：根据方向设定速度的正负号
        if direction == "down":
            # 同向：相加
            self.net_speed = self.person_speed + self.elevator_speed
        elif direction == "up":
            # 反向：相减（人向上，电梯向下）
            self.net_speed = self.person_speed - self.elevator_speed
        else:
            raise ValueError("方向只能为 'down' 或 'up'")
        
        self.direction = direction
        self.time_elapsed = 0.0

    def update(self, delta_t):
        """
        更新状态（这就是所谓的“模板方法”）
        :param delta_t: 时间步长（秒），比如 0.5 秒更新一次
        """
        # 位移增量
        displacement = self.net_speed * delta_t
        self.position += displacement
        self.time_elapsed += delta_t
        
        # 边界保护（防止位置为负，模拟到底部停止）
        if self.position < 0:
            self.position = 0
            self.net_speed = 0

    def get_status(self):
        """获取当前状态"""
        return {
            "时间": round(self.time_elapsed, 2),
            "位置（向下为正）": round(self.position, 2),
            "当前合速度": round(self.net_speed, 2)
        }

# --- 使用这个“模板”进行模拟 ---
def run_simulation():
    # 实例化：人走1.2m/s，电梯走0.8m/s，同向下行
    simulator = MotionSimulator(person_speed=1.2, elevator_speed=0.8, direction="down")
    
    print("🚶‍♂️ 人梯同向下行模拟开始（模板函数验证）")
    print("每秒更新时间步长 0.5 秒\n")
    
    # 模拟 5 秒（共 10 帧）
    for _ in range(10):
        simulator.update(0.5)
        status = simulator.get_status()
        print(f"⏱️ 第 {status['时间']}秒 | 位移: {status['位置（向下为正）']} 米 | 合速度: {status['当前合速度']} m/s")
        time.sleep(0.1)

if __name__ == "__main__":
    run_simulation()