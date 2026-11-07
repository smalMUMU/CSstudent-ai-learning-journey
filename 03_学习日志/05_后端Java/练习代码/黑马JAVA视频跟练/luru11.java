import java.util.Scanner;

public class luru11{
    public static void main(String[] args)
    {
        //创建对象，表示我现在要用Scanner这个类
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入整数：");

        //接收数据
        //变量i记录了键盘录入的数据
        int i = sc.nextInt();

        System.out.println(i);
    }
}