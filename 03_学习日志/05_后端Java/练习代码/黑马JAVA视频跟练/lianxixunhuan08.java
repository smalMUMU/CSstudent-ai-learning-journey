import java.util.Scanner;

public class lianxixunhuan08 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入两个数字：");

        int n = sc.nextInt();
        int m = sc.nextInt();
        int count = 0;

        if (n <= m) {
            for (int i = n; i <= m; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    count++;
                }
            }
        }
        else
        {
            System.out.println("输入异常数据，请重新输入！！！");
        }

        System.out.println(count);
    }
}
