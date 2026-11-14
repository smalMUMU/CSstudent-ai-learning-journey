public class erweishuzuxiaolianxi09 {
    public static void main(String[] args) {
        int[][] arr = {
                {50, 60, 69, 78},
                {20, 30, 50, 50},
                {10, 80, 400, 12},
                {10, 45, 91, 78}
        };

        int yearSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = getSum(arr[i]);
            System.out.println("当前季度的营业额为" + sum);
            yearSum += sum;
        }
        System.out.println(yearSum);


    }

    public static int getSum(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
