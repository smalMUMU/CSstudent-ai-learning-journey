public class TTStudent {
    public static void main(String[] args) {
        TStudent[] arr = new TStudent[3];
        arr[0] = new TStudent(1, "zhangsan", 16);
        arr[1] = new TStudent(2, "lisi", 17);
        arr[2] = new TStudent(3, "xiaohong", 18);

        System.out.println("===== 初始 =====");
        printArr(arr);

        // ===== 增：加第 4 个（数组已满 → 自动扩容） =====
        System.out.println("===== 增：加 id=4 =====");
        arr = addStudent(arr, new TStudent(4, "yunbao", 19));
        printArr(arr);

        // 再试一个重复 id
        System.out.println("===== 增：加重复 id=2（应被拒绝） =====");
        arr = addStudent(arr, new TStudent(2, "重复的", 20));
        printArr(arr);

        // ===== 删：删中间的 id=2 =====
        System.out.println("===== 删：id=2（中间那个） =====");
        arr = deleteStudent(arr, 2);
        printArr(arr);

        // ===== 删：删不存在的 id=99 =====
        System.out.println("===== 删：id=99（不存在） =====");
        arr = deleteStudent(arr, 99);
        printArr(arr);

        // ===== 改：把 id=3 的年龄 +1 =====
        System.out.println("===== 改：id=3 年龄 +1 =====");
        int idx = indexPosition(arr, 3);
        if (idx != -1) {
            TStudent s = arr[idx];
            s.setAge(s.getAge() + 1);
        }
        printArr(arr);
    }

    // ========== 增：返回（可能已扩容的）数组 ==========
    public static TStudent[] addStudent(TStudent[] arr, TStudent stu) {
        // ① 查重
        if (indexPosition(arr, stu.getId()) != -1) {
            System.out.println("id 重复，拒绝录入：" + stu.getId());
            return arr;
        }
        // ② 有没有空位：有 → 直接放；满 → 扩容后放
        int count = getCount(arr);
        if (count < arr.length) {
            arr[count] = stu;
        } else {
            TStudent[] newArr = createNewArr(arr);
            newArr[count] = stu;
            arr = newArr;
        }
        return arr;
    }

    // ========== 删：置 null ==========
    public static TStudent[] deleteStudent(TStudent[] arr, int id) {
        int idx = indexPosition(arr, id);
        if (idx == -1) {
            System.out.println("没有该学生：" + id);
            return arr;
        }
        arr[idx] = null;
        return arr;
    }

    // ========== 查：返回下标，找不到返回 -1 ==========
    public static int indexPosition(TStudent[] arr, int id) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // ========== 统计实际学生数（数非 null） ==========
    public static int getCount(TStudent[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count++;
            }
        }
        return count;
    }

    // ========== 扩容：新数组长一位 + 复制旧内容 ==========
    public static TStudent[] createNewArr(TStudent[] arr) {
        TStudent[] newArr = new TStudent[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    // ========== 打印：跳过 null ==========
    public static void printArr(TStudent[] arr) {
        for (int i = 0; i < arr.length; i++) {
            TStudent s = arr[i];
            if (s == null) {
                continue;
            }
            System.out.println(s.getId() + "," + s.getName() + "," + s.getAge());
        }
    }
}
