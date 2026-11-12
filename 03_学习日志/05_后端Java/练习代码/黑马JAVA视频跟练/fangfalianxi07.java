public class fangfalianxi07 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        printArr(arr);
    }


    public static void printArr(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1){
                System.out.print(arr[i]);
            }else{
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println("]");
    }
}
