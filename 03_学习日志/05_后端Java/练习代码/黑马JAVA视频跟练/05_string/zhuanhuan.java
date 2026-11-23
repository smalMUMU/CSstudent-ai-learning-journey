import java.util.Scanner;

public class zhuanhuan {
    public static void main(String[] args) {
        int[] a = new int[4];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < a.length; i++) {
            System.out.println("输入一个数字，将其组成一个数组：");
            int num = sc.nextInt();
            a[i] = num;
        }

        String str = arrToString(a);
        System.out.println(str);

    }

    public static String arrToString(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1){
                sb.append(arr[i]);
            }else{
                sb.append(arr[i]).append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
