import java.util.Random;
import java.util.Scanner;

public class shuzuyuansufuzhi04 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的初始数组长度:");
        int len = sc.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            int randomnumber = r.nextInt(10);
            arr[i] = randomnumber;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("\n");
        //打印初始数组确保是否复制好

        int[] farr = new int[arr.length];
        for (int i = 0; i < farr.length; i++) {
            farr[i] = arr[i];
        }
        for (int i = 0; i < farr.length; i++) {
            System.out.print(farr[i]);
        }
        System.out.println("\n");

        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (farr[i] != arr[i]){
                flag = false;
                break;
            }
        }
        if (flag){
            System.out.println("数组复制成功！！！");
        }else{
            System.out.println("数组复制失败！！！");
        }

    }
}
