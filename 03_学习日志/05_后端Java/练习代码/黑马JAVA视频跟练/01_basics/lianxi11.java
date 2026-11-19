import java.util.Scanner;

public class lianxi11{
    public static void main(String[] args)
    {
        //创建对象
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入第一个数字");
        //接收数据
        int number1 = sc.nextInt();

        System.out.println("请输入第二个数字");
        //接收第二个数据
        int number2 = sc.nextInt();

        //计算俩个数据的和
        System.out.println(number1 + number2);
    }
}