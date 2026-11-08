import java.util.Scanner;

public class suanfu06 {
    public static void main(String[] args)
    {
        Scanner user = new Scanner(System.in);
        System.out.println("请输入一个三位数：");
        int number = user.nextInt();

        int ge = number % 10;
        int shi = number / 10 % 10;
        int bai = number / 100 % 10;

        System.out.println("个位是：" + ge);
        System.out.println("十位是：" + shi);
        System.out.println("百位是：" + bai);

    }
}
