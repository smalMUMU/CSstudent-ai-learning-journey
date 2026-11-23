public class TTStudent {
    public static void main(String[] args) {
        TStudent[] arr = new TStudent[3];
        TStudent stu1 = new TStudent(01, "zhangsan", 16);
        TStudent stu2 = new TStudent(02, "lisi", 17);
        TStudent stu3 = new TStudent(03, "xiaohong", 18);
        
        arr[0] = stu1;
        arr[1] = stu2;
        arr[2] = stu3;


        TStudent stu4 = new TStudent(04, "yunbao", 19);

        boolean flag = check(arr, 04);

        int index = index(arr);

        boolean len = lengthArr(arr);

        //这个是增
        if (flag){
            if (index < arr.length){
                arr[index] = stu4;
                printArr(arr);
            }else{
                TStudent[] new_arr = creatArr(arr);
                new_arr[index] = stu4;
                arr = new_arr;
                printArr(new_arr);
            }
        }else{
            System.out.println("你录入的这个学生id重复，请重新录入！！！");
        }

        //下面的是删

        boolean delete = check(arr, 02);
        if (delete){
            //还要写一个删除的方法
            if (arr[1] == null){
                System.out.println("没有这个学生！！！");
            }else{
                TStudent[] dnew_arr = deleteArr(arr, 02);
                printArr(dnew_arr);

            }
        }else{
            System.out.println("你想删除的这个学生不存在，请重新输入！！！");
        }

    }

    public static TStudent[] deleteArr(TStudent[] arr, int id){
        TStudent stu = arr[id];
        stu = null;
        arr[id] = stu;
        return arr;
    }

    //删除的话还要看要删除的下标在不在但是这个不是和增的重复了？，直接用增的吧

    //遍历打印数组的方法
    public static void printArr(TStudent[] arr){
        for (int i = 0; i < arr.length; i++) {
            TStudent stu = arr[i];
            if (stu != null){
                System.out.println(stu.getId() + "," + stu.getName() + "," + stu.getAge());
            }else{
                continue;
            }
        }
    }

    //确定要增了还要判断长度够不够，还要写一个判断长度的方法
    public static boolean lengthArr(TStudent[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            TStudent temp = arr[i];
            if (temp != null){
                count++;
            }
        }
        if (count == arr.length){
            return false;
        }else{
            return true;
        }
    }
    //这个方法和index这个方重合了，因为直接拿index和那个长度比较就能判断是否创建新的数组，

    //这里我还要统计一下插入位置，方便增

    public static int index(TStudent[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            TStudent temp = arr[i];
            if (temp != null){
                count++;
            }
        }

        return count;
    }
    
    public static boolean check(TStudent[] arr, int id){
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            TStudent temp = arr[i];
            if (temp == null){
                continue;
            }
            if (temp.getId() == id){
                flag = false;
                break;
            }
        }

        return flag;
    }
    
    public static TStudent[] creatArr(TStudent[] arr){
        TStudent[] new_arr = new TStudent[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            new_arr[i] = arr[i];
        }

        return new_arr;
    }
}
