import java.util.Random;
import java.util.Scanner;

public class shuangseqiulenxie {
    public static void main(String[] args) {
        int[] userchoose = new int[7];
        //写到这里发现用户输入的和系统抽取的奖项写反了，数组写反了，但是语法没错就将就这样吧，我把userchoose仿作系统的奖项好了，继续写
        userchoose = userGet(userchoose);
        for (int i = 0; i < userchoose.length; i++) {
            System.out.print(userchoose[i] + " ");
        }
        System.out.println();//空格换行

        int[] maxprize = new int[7];
        maxprize = getPrize(maxprize);

        int readCount = 0;
        int blueCount = 0;

        for (int i = 0; i < userchoose.length - 1; i++) {
            for (int j = 0; j < maxprize.length - 1; j++) {
                if (userchoose[i] == maxprize[j]){
                    readCount++;
                }
            }
        }

        if (userchoose[userchoose.length - 1] == maxprize[maxprize.length - 1]){
            blueCount++;
        }

        if (readCount == 6 && blueCount == 1){
            System.out.println("一等奖");
        }else if(readCount == 6 && blueCount == 0){
            System.out.println("二等奖");
        }else if(readCount == 5 && blueCount == 1){
            System.out.println("三等奖");
        }else if ((readCount == 5 && blueCount == 0) || (readCount == 4 && blueCount == 1)){
            System.out.println("四等奖");
        }else if ((readCount == 4 && blueCount == 0) || (readCount == 3 && blueCount == 1)){
            System.out.println("五等奖");
        }else if ((readCount == 2 || readCount == 1 || readCount == 0) && blueCount == 1){
            System.out.println("六等奖");
        }else{
            System.out.println("谢谢参与，下次再来！");
        }

    }

    public static int[] userGet(int[] arr){
        Random r = new Random();
        for (int i = 0; i < arr.length - 1; ) {
            int readNumber = r.nextInt(1, 34);
            if (checkNums(arr, readNumber)){
                arr[i] = readNumber;
                i++;
            }
        }

        int blueNumber = r.nextInt(1, 17);
        arr[arr.length - 1] = blueNumber;

        return arr;
    }

    public static int[] getPrize(int[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请从1-33中选择6个作为你的红球号码");

        for (int i = 0; i < arr.length - 1; ) {
            System.out.println("请输入你选择的第" + (i + 1) + "个红球号码：");
            int readNums = sc.nextInt();
            if (readNums >= 1 && readNums <= 33) {
                if (checkNums(arr, readNums)) {
                    arr[i] = readNums;
                    i++;
                } else {
                    System.out.println("你输入的红球号码重复输入了，请重新输入！！！");
                }
            }else{
                System.out.println("你输入的数据超出范围请重新输入");
            }
        }

        System.out.println("请从1-16中选择一个作为你的蓝球号码：");
        while (true){
            System.out.println("请输入你选择的蓝球号码：");
            int blueNums = sc.nextInt();
            if (blueNums >= 1 && blueNums <= 16){
                arr[arr.length - 1] = blueNums;
                break;
            }else{
                System.out.println("你输入的蓝球超出范围，请重新输入！！！");
            }
        }

        return arr;
    }

    public static boolean checkNums(int[] arr, int nums) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (nums == arr[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
