import java.util.Scanner;

public class lianxixuanze06 {
    public static void main(String[] args)
    {
        System.out.println("[1] 机票查询");
        System.out.println("[2] 机票预订");
        System.out.println("[3] 机票改签");
        System.out.println("[4] 退出服务");

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你选择的服务的序号：");

        int number = sc.nextInt();
        switch (number)
        {
            case 1 -> System.out.println("使用机票查询服务");
            case 2 -> System.out.println("使用机票预订服务");
            case 3 -> System.out.println("使用机票改签服务");
            case 4 -> System.out.println("退出服务！！！");
            default -> System.out.println("输入异常数据！！！\n直接退出服务！！！");
        }
    }
}
