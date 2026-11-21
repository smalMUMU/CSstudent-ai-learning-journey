import java.util.Scanner;

public class zifuchuandepingjie {
    public static void main(String[] args) {
        int[] arr = new int[4];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入这个数组的第" + (i + 1) + "个元素:");
            int number = sc.nextInt();
            arr[i] = number;
        }

        String str = connectString(arr);
        System.out.println(str);
    }

    public static String connectString(int[] arr){
        if (arr == null){
            return "";
        }

        if (arr.length == 0){
            return "[]";
        }

        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            if(i == arr.length - 1){
                result = result + arr[i];
            }else{
                result = result + arr[i] + ",";
            }
        }
        result = result + "]";

        return result;
    }
}
