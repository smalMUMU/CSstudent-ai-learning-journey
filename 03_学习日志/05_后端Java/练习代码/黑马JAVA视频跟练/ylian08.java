import java.util.Scanner;

public class ylian08 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入男方时髦度：");
        int boyfashion = sc.nextInt();
        System.out.println("输入相亲对象时髦度：");
        int girlfashion = sc.nextInt();

        boolean result = boyfashion > girlfashion;

        System.out.println(result);

        //这里暴露了Scanner的输入语法还是不熟悉，需要专门学习强化一下
    }
}
