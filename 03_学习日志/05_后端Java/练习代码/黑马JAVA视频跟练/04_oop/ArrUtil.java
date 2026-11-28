public class ArrUtil {
    private ArrUtil(){

    }

    public static  void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int getMax(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }

        return max;
    }
}
