import java.util.Scanner;

public class pingfanggenglianxi03 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要查询平方根的数：");

        int number = sc.nextInt();

        for (int i = 1; i <= number; i++){
            if (i * i == number){
                System.out.println(i);
                break;
            }
            else if (i * i > number){
                System.out.println(i - 1);
                break;
            }
        }
    }
}
