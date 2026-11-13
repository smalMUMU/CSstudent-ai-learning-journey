public class shuzijieminixiang06 {
    public static void main(String[] args) {
        int[] arr = {8, 3, 4, 6};

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && arr[i] <= 4){
                arr[i] = 10 + arr[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] -= 5;
        }

        int number = 0;
        for (int i = 0; i < arr.length; i++) {
            number = number * 10 + arr[i];
        }
        System.out.println(number);


        //这是在了解黑马的那个题目的思路后独立写出来的编程，还不错，解密成功了，但是我在想可不可以把这种解密过程包装成方法，但是这道题好像没必要
        //不过黑马的那个取余的思路还是比较可以之前我还在想怎么把取余数倒退回去，但是知道题目条件是个位数之后就简单了，没有什么难度
    }
}
