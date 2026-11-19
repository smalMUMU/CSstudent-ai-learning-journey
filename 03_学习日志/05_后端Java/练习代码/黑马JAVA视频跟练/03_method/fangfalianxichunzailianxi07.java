import java.util.Random;
import java.util.Scanner;

public class fangfalianxichunzailianxi07 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        int[] abc = new int[10];
        for (int i = 0; i < abc.length; i++) {
            int number = r.nextInt(1, 20);
            abc[i] = number;
        }
        for (int i = 0; i < abc.length; i++) {
            System.out.print(abc[i] + ",");
        }
        //显示数组看真的能否查询
        System.out.print('\n');
        System.out.println("请输入你想查询的数字：");
        int target = sc.nextInt();
        int count = need(abc, target);
        if (count == 1){
            System.out.println(target + "这个数字在数组中存在！！！");
        }else{
            System.out.println(target + "这个数在数组中不存在！！！！");
        }
    }


    public static int need(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target){
                return 1;
            }
        }
        return -1;
    }
}
