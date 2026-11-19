import java.util.Scanner;

public class swtichmian05 {
    public static void main(String[] args)
    {
        System.out.println("[1] 兰州拉面");
        System.out.println("[2] 武汉热干面");
        System.out.println("[3] 北京炸酱面");
        System.out.println("[4] 陕西油泼面");

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想吃的面条菜单数字：");

        int noodle = sc.nextInt();

        switch (noodle)
        {
            case 1:
                System.out.println("吃兰州拉面");
                break;
            case 2:
                System.out.println("吃武汉热干面");
                break;
            case 3:
                System.out.println("吃北京炸酱面");
                break;
            case 4:
                System.out.println("吃陕西油泼面");
                break;
            default:
                System.out.println("啥也不吃，狗日的吃方便面去吧！！！");
                break;
        }
    }
}
