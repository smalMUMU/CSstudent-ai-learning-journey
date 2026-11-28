import java.util.ArrayList;

public class StudentTool01 {
    private StudentTool01(){

    }

    public static int getMaxAge(ArrayList<> arr){
        //这里不选择max初始值为arr里面的原因是年龄是正数最小的就是0，避免取数麻烦
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            StudentTool01 stu = arr.get(i);
            if (max <= stu.age){
                max = stu.age;
            }
        }

        return max;
    }
}
