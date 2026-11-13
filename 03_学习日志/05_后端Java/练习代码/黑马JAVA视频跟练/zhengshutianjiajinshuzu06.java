public class zhengshutianjiajinshuzu06 {
    public static void main(String[] args) {
        int number = 12345;
        int temp = number;
        int count = 0;
        while(number != 0){
            number /= 10;
            count++;
        }
        int[] arr = new int[count];

        int index = arr.length - 1;
        while(temp != 0){
            int gt = temp % 10;
            arr[index] = gt;
            temp /= 10;
            index--;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
