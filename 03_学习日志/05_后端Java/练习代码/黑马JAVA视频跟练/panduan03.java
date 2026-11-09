import java.util.Scanner;

public class panduan03 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你身上携带的钱：");
        int money = sc.nextInt();

        if (money >= 100)
        {
            System.out.println("去吃网红餐厅！");
        }
        else
        {
            System.out.println("去吃沙县小吃~");
        }
    }
}
