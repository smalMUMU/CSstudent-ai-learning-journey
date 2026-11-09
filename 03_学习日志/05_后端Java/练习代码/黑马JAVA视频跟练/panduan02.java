import java.util.Scanner;

public class panduan02 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入女婿的酒量为几俩：");

        int number = sc.nextInt();

        if (number > 2)
        {
            System.out.println("小伙子酒量不错！");
        }
        else
        {
            System.out.println("小孩不能喝酒就回家去！");
        }
    }
}
