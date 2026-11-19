public class jidusuanfanhuizhi04 {
    public static void main(String[] args) {

        //直接调用
        getsum(10, 20, 50);

        //赋值调用
        int sum = getsum(10, 20, 50);
        System.out.println(sum);

        //输出调用
        System.out.println(getsum(10, 20, 50));

    }
    //这一道题是拿来熟悉返回值的调用和其他的返回值调用方式

    public static int getsum(int num1, int num2, int num3){
        int result = num1 + num2+ num3;
        return result;
    }
}
