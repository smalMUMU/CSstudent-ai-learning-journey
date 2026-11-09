import java.util.Scanner;

public class shangpinglianxi04 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的会员等级：");

        int level = sc.nextInt();
        int price = 1000;

        if (level >= 1 && level <= 3)
        {
            if (level == 1)
            {
                System.out.println(price * 0.9);
            }
            else if (level == 2)
            {
                System.out.println(price * 0.8);
            }
            else if (level == 3)
            {
                System.out.println(price * 0.7);
            }
        }
        else
        {
            System.out.println("不是会员还有脸来买东西？？？" + price);
        }
    }
}
