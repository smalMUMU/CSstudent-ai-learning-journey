public class duanlu10 {
    public static void main(String[] args)
    {
        int a = 10;
        int b = 10;
        boolean result = ++a < 5 & ++b < 5;

        System.out.println(result);
        System.out.println(a);
        System.out.println(b);


        //探索短路符号和普通逻辑符号的区别，在于短路只要不满足就不会执行后续
    }
}
