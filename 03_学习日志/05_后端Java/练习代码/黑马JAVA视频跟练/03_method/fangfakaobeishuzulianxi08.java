import java.util.Random;
import java.util.Scanner;

public class fangfakaobeishuzulianxi08 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int number = r.nextInt(1, 11);
            arr[i] = number;
        }
        //接下来把数组打印一下，方便后期矫正是否成功拷贝
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("\n");

        System.out.println("请输入你想拷贝的起始位置：");
        int from = sc.nextInt();
        System.out.println("请输入拷贝结束的位置：");
        int to = sc.nextInt();

        if (from <= to && to <= 10 && from > 0){
            int[] abc = copyOfRange(arr, from, to);
            for (int i = 0; i <= to - from; i++){
                System.out.print(abc[i]);
            }
        }else{
            System.out.println("异常数据请重试！！！");
        }
    }

    public static int[] copyOfRange(int[] arr, int from, int to){
        int[] new_arr = new int[to - from + 1];
        int count = 0;
        for (int i = from - 1; i < to; i++) {
            new_arr[count] = arr[i];
            count++;
        }
        return new_arr;
    }
    //我去找到报错原因了，之前定义的是int方法，所以返回数组是错的现在直接在int后面加上[]就可以返回数组了，好的问题解决了我要开始编码了
}
