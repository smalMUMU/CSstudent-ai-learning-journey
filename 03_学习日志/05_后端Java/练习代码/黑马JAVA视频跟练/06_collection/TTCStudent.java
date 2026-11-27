import java.util.ArrayList;
import java.util.Scanner;

public class TTCStudent {
    public static void main(String[] args) {
        ArrayList<CStudent> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入你的学生姓名：");
            String name = sc.next();
            System.out.println("请输入你的学生年龄：");
            int age = sc.nextInt();
            CStudent stu = new CStudent(name, age);
            list.add(stu);
        }

        for (int i = 0; i < list.size(); i++) {
            CStudent temp = list.get(i);
            System.out.println(temp.getName() + ", " + temp.getAge());
        }
        System.out.println(list.toString());
    }
}
