import java.util.Scanner;

public class feijipiaolianxizonghe01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入机票原价：");
        int price = sc.nextInt();

        System.out.println("请输入月份：");
        int month = sc.nextInt();

        System.out.println("请输入你的舱的等级，0代表头等舱， 1代表经济舱：");
        int seat = sc.nextInt();

        int price1 = 1;

        if (month >= 5 && month <= 10){
            if (seat == 0){
                price1 = (int) (price * 0.9);
            }else if (seat == 1){
                price1 = (int) (price * 0.85);
            }else{
                System.out.println("你的座位异常请重新输入！！！");
            }
        }else if (month >= 1 && month <= 4 || month >= 11 && month <= 12){
            if (seat == 0){
                price1 = (int) (price * 0.7);
            }else if (seat == 1){
                price1 = (int) (price * 0.65);
            }else{
                System.out.println("你的座位异常请重新输入！！！");
            }
        }else{
            System.out.println("你输入的月份异常请重新输入！！！");
        }

        System.out.println(price1);
    }


    //开始的时候我想着把活外包给方法，但是参数传递来传递去始终不好干，听了一下黑马的思路，还是决定直接使用最简单的嵌套if
    //写完之后，黑马又讲了怎么外包给方法，我现在明白了不能一口气全部外包完，还是要留点基础的，好吧外包方法的我先不写了
     //总结一下吧，先就是先不要全部外包，先自己把代码走一遍，然后回头审视一下代码，看看有哪些代码是重复劳作的，这个时候就可以考虑外包简化代码了
}
