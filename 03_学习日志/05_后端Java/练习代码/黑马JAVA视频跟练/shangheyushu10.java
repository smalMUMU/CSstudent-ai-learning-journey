public class shangheyushu10 {
    public static void main(String[] args)
    {
        //这道题是讲的是不用 / 和 % 来计算我们的商和余数
        int a = 100;
        int b = 10;
        int count = 0;
        while (a >= b){
            a -= b;
            count++;
        }
        System.out.println("余数：" + a);
        System.out.println("商：" + b);
    }
}
