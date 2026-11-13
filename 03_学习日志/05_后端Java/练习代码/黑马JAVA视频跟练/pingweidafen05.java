import java.util.Random;

public class pingweidafen05 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int randomnumber = r.nextInt(101);
            arr[i] = randomnumber;
        }
        int max = arr[0];
        int min = arr[0];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
            if (min > arr[i]){
                min = arr[i];
            }
            sum += arr[i];
        }

        System.out.println((sum - max - min) / 4);
        //怎么删除我不会，先看一下黑马,黑马说只需要求出来不需要删除，那就简单多了
    }
}
