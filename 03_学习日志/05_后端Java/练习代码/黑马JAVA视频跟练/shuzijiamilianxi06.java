public class shuzijiamilianxi06 {
    public static void main(String[] args) {
        int[] arr = {1, 7, 9, 1};

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + 5;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10;
        }

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int number = 0;
        for (int i = 0; i < arr.length; i++) {
            number = number * 10 + arr[i];
        }

        System.out.println(number);
    }
}
