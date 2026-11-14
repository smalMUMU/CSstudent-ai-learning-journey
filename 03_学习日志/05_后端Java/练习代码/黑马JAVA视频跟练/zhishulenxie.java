public class zhishulenxie {
    public static void main(String[] args) {
        int[] arr = {57, 88, 98, 77, 91};
        for (int i = 0; i < arr.length; i++) {
            if(check(arr[i])){
                System.out.println(arr[i] + "是一个质数！");
            }else{
                System.out.println(arr[i] + "不是一个质数！");
            }
        }

    }

    public static boolean check(int num){
        boolean flag = true;
        for (int i = 2; i * i <= num; i++){
            if (num % i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
