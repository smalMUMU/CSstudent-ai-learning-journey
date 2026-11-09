import java.util.Scanner;

public class xiaoming04 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入小明的考试成绩：");

        int score = sc.nextInt();

        if (score >= 0 && score <= 100)
        {
            if (score >= 95 && score <= 100) {
                System.out.println("奖励自行车一辆！");
            } else if (score >= 90 && score <= 94) {
                System.out.println("游乐场玩一天！");
            } else if (score >= 80 && score <= 89) {
                System.out.println("奖励一个变形金刚！");
            } else {
                System.out.println("好小子，吃你爹一棒！！！");
            }
        }
        else
        {
            System.out.println("你输入的成绩不合理，异常录入，请重新输入！！！");
        }

    }
}
