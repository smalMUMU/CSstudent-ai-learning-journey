import java.util.Random;
import java.util.Scanner;

public class shuangseqiuilianxi08 {
    public static void main(String[] args) {
        int[] arr = createNumber();
        int[] userInputArr = userInputNumber();

        int readcount = 0;
        int bluecount = 0;

        for (int i = 0; i < userInputArr.length - 1; i++) {
            int readNumber = userInputArr[i];
            for (int j = 0; j < arr.length - 1; j++){
                if (readNumber == arr[i]){
                    readcount++;
                    break;
                }
            }
        }

        int blueNumber = userInputArr[userInputArr.length - 1];
        if (blueNumber == arr[arr.length - 1]){
            bluecount++;
        }

        if (readcount == 6 && bluecount == 1){
            System.out.println("恭喜你，中奖1000万！！！");
        }else if(readcount == 6 && bluecount == 0){
            System.out.println("恭喜你，中奖500万！！");
        }else if (readcount == 5 && bluecount == 1){
            System.out.println("共享你，中奖3000！");
        }else if ((readcount == 5 && bluecount == 0) || (readcount == 4 && bluecount == 1)){
            System.out.println("中奖200！");
        }else if ((readcount == 4 && bluecount == 0) || (readcount == 3 && bluecount == 1)){
            System.out.println("中奖10");
        }else if((readcount == 2 && bluecount == 1) || (readcount == 1 && bluecount == 1) || (readcount == 0 && bluecount == 1)){
            System.out.println("中奖5");
        }else{
            System.out.println("感谢参与！！！");
        }
    }

    public static int[] userInputNumber(){
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; ) {
            System.out.println("请依次输入你采购的第" + (i + 1) + "红球号码：");
            int readNumber = sc.nextInt();
            if (readNumber >= 1 && readNumber <= 33){
                boolean flag = contains(arr, readNumber);
                if (!flag){
                    arr[i] = readNumber;
                    i++;
                }else{
                    System.out.println("当前红球号码已经存在，请重新输入：");
                }
            }else{
                System.out.println("当前红球号码已经超出范围，请重新输入！！！");
            }
        }

        while (true){
            System.out.println("请输入蓝球号码（1-16）：");
            int blueNumber = sc.nextInt();
            if (blueNumber >= 1 && blueNumber <= 16){
                arr[arr.length - 1] = blueNumber;
                break;
            }
            System.out.println("超出范围请重新输入！！！");
        }

        return arr;
    }
    
    public static int[] createNumber(){
        int[] arr = new int[7];

        Random r = new Random();
        for (int i = 0; i < 6; ) {
            int readNumber = r.nextInt(33) + 1;
            boolean flag = contains(arr, readNumber);
            if (!flag){
                arr[i] = readNumber;
                i++;
            }
        }

        int blueNumber = r.nextInt(16) + 1;
        arr[arr.length - 1] = blueNumber;
        return arr;
    }

    public static boolean contains(int[] arr, int number){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number){
                return true;
            }
        }
        return false;
    }

    //这是跟着黑马敲的走了一遍思路
}
