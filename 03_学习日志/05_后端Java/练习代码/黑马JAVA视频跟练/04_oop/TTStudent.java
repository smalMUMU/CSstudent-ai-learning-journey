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

        if (flag){
            arr[index] = stu4;
        }else if{

        }else{
            System.out.println("");
        }
        
        
        
    }

    //这里我还要统计一下插入位置，方便增

    public static int index(TStudent[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            TStudent temp = arr[i];
            if (temp == null){
                break;
            }
            count++;
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
