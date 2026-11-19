import java.util.Scanner;

public class pingweilianxiheimabanben05 {
    public static void main(String[] args) {
        int[] scoreArr = getScores();
        int max = getMax(scoreArr);
        int min = getMin(scoreArr);
        int sum = getSum(scoreArr);

        int avg = (sum - max - min)/(scoreArr.length - 2);
        System.out.println(avg);
    }

    public static int getSum(int[] scoreArr){
        int sum = 0;
        for (int i = 0; i < scoreArr.length; i++) {
            sum += scoreArr[i];
        }
        return sum;
    }

    public static int getMax(int[] scoreArr){
        int max = scoreArr[0];
        for (int i = 0; i < scoreArr.length; i++) {
            if (max < scoreArr[i]) {
                max = scoreArr[i];
            }
        }
        return max;
    }

    public static int getMin(int[] scoreArr){
        int min = scoreArr[0];
        for (int i = 0; i < scoreArr.length; i++) {
            if (min > scoreArr[i]){
                min = scoreArr[i];
            }
        }
        return min;
    }


    public static int[] getScores(){
        int[] scores = new int[6];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < scores.length; ) {
            System.out.println("请输入评委打分：");
            int score = sc.nextInt();
            if (score >= 0 && score <= 100){
                scores[i] = score;
                i++;
            }else{
                System.out.println("成绩超出范围请继续录入，当前的i为：" + i);
            }
        }

        return scores;
    }
}
