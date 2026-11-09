import java.util.Scanner;

public class panlianxi03 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入客户实际支付金额：");

        int pay = sc.nextInt();

        if (pay >= 600)
        {
            System.out.println("支付成功！");
        }
        else
        {
            System.out.println("余额不足，支付失败！");
        }
    }
}
