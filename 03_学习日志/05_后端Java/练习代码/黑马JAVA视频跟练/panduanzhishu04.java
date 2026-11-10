import java.util.Scanner;

public class panduanzhishu04 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个正整数来判断是否为质数：");
        int number = sc.nextInt();
        int flag = 1;

        for (int i = 2; i * i < number; i++){
            if (number % i == 0) {
                flag = -1 * flag;
                break;
            }
        }
        if (flag == -1){
            System.out.println("这个数字不是质数！！！");
        }else{
            System.out.println("这个数字是质数！！！");
        }


    }
}
