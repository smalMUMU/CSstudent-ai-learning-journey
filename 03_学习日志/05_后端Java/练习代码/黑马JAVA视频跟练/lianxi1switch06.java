import java.util.Scanner;

public class lianxi1switch06 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入当前的星期数：");
        int day = sc.nextInt();

        if (day >= 1 && day <= 7) {
            if (day >= 1 && day <= 5) {
                System.out.println("工作日");
            } else {
                System.out.println("休息日");
            }
        }
        else
        {
            System.out.println("你输入的数非正常数据，请重新输入！！！");
        }
    }
    //黑马选择使用switch不过确实看起来switch语句糅合在一起也行，但是我是因为联想到范围所以选择使用if语句来编写编程
}
