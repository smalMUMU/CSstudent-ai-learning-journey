import java.util.Random;

public class choujiangdediyizhongshixianfangshi07 {
    public static void main(String[] args) {
        int[] arr = {2, 588, 888, 1000, 10000};

        int[] newArr = new int[arr.length];

        Random r = new Random();

        for (int i = 0; i < arr.length; ) {
            int randomnumber = r.nextInt(arr.length);
            int prize = arr[randomnumber];
            if (check(newArr, prize)){
                newArr[i] = prize;
                i++;
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + " ");
        }


    }


    public static boolean check(int[] newArr, int need){
        boolean flag = true;
        for (int i = 0; i < newArr.length; i++) {
            if (need == newArr[i]){
                flag = false;
            }
        }
        return flag;
    }


    //黑马优化了抽奖池子里面的奖金顺序，我没写，现在写一下，我还是不想写，算了还是写一下
}
