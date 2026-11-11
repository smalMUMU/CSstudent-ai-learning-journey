import java.util.Random;

public class shuzulianxidaluanshuju4 {
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        //这次是跟着黑马敲的，有点感觉了。对random的运用有点感觉了
        
    }
}
