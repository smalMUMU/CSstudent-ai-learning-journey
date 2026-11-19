import java.util.Random;

public class shuzuisuijilianxi2 {
    public static void main(String[] args)
    {
        int[] arr = new int[10];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int number = r.nextInt(1, 101);
            arr[i] = number;
        }

        int sum = 0;
        double average;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        average = sum / 10.0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < average){
                count++;
            }
        }

        System.out.println("总和：" + sum);
        System.out.println("平均数：" + average);
        System.out.println("低于平均数的个数：" + count);
        //这里我暴露了随机生成数的不熟悉，然后还有randonm我也不熟悉，所以可能有点生疏，但是写出来还是简单，
        //原来ln是换行的意思原来如此，理解了又多一个小的知识点
    }
}
