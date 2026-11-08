import java.util.Scanner;

public class suanlian103 {
    public static void main(String[] args)
    {
        //这道练习题目是让用户输入一个三位数字，然后再分别打印，个位数，十位数和百位数
        Scanner user = new Scanner(System.in);
        System.out.println("请输入一个三位数：");
        int number = user.nextInt();
        //果然练习还是有用，这里我忘记了要用变量来承接输入，我以为第一步就已经干好了，还是太单纯，没有记牢

        int ge = number % 10;
        int shi = number / 10 % 10;
        int bai = number / 100 % 10;

        System.out.println(ge);
        System.out.println(shi);
        System.out.println(bai);
    }
}
