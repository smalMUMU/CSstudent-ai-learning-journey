import java.util.Scanner;

public class qiuyuandemianji03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想计算的圆的半径：");

        double r = sc.nextInt();

        sr(r);
    }

    public static void sr(double r){
        double s = r * r *3.14;
        System.out.println(s);
    }
}
