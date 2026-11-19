import java.util.Scanner;

public class feng7Guolianxi02 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的开始数字：");

        int start = sc.nextInt();

        if (start <= 100) {
            for (int i = start; i <= 100; i++) {
                if (i % 7 == 0 || i % 10 == 7 || i / 10 % 10 == 7){
                    System.out.print("过！！\n");
                    continue;
                }
                System.out.println(i);
            }
        }else{
            System.out.println("你输入的起始数据超出运行范围！！！");
        }
    }
}
