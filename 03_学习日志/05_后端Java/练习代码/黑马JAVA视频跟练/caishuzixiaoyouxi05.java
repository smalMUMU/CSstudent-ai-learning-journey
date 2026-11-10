import java.util.Random;
import java.util.Scanner;

public class caishuzixiaoyouxi05 {
    public static void main(String[] args)
    {
        Random r = new Random();
        int number = r.nextInt(1,101);

        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.println("请输入你要猜的数字：");
            int target = sc.nextInt();
            if (count == 3){
                System.out.println("很遗憾，你的次数用光了！！！再接再厉吧！！！");
                break;
            }
            if (target == number){
                System.out.println("恭喜你猜中了！！！");
                break;
            }
            else if(target < number){
                System.out.println("猜小了！！！");
            }else{
                System.out.println("猜大了！！！");
            }
            count++;

        }
    }
}
