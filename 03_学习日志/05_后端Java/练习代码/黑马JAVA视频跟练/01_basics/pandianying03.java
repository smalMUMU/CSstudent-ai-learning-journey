import java.util.Scanner;

public class pandianying03 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的电影票序号：");

        int number = sc.nextInt();
        if (number > 0 && number <= 100)
        {
            if (number % 2 == 0) {
                System.out.println("坐右边！");
            } else {
                System.out.println("坐左边！");
            }
        }
        else
        {
            System.out.println("你输入的电影票序号，超出范围，输入错误！！！");
        }
    }
}
