import java.util.Random;

public class fangfalianxi207 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[30];
        for (int i = 0; i < a.length; i++) {
            int number = r.nextInt(20, 121);
            a[i] = number;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }//拿来打印数组看是不是真的取出最大值

        System.out.print("\n");
        //发现没有换行加一个换行符

        int max = getmax(a);
        System.out.println(max);

    }

    public static int getmax(int[] arr){
        int count = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (count < arr[i]){
                count = arr[i];
            }
        }
        return count;
    }
}
