public class yunsuan08 {
    public static void main(String[] args)
    {
        //这里是探讨+=的使用方法和细节

        int a = 10;
        int b = 20;
        a += b;

        System.out.println(a);
        System.out.println(b);

        //其他定义
        short s = 1;
        s += 1;

        System.out.println(s);

        //ok，成功运行了，这里我出了一个小差错，就是再第8行的代码+=运算的时候，额外写了一个int定义，忘记了之前已经定义的，导致语法错误
        //这里黑码讲了一个细节，这个+= 和普通的 +1，存在区别
        //就是，+= 会隐藏一个强制转换，short s 那一段就是隐藏了一个强制转换，转回了short，如果按照普通的+ 1 来写，就要加一个强转才能等价 如 s += 1 等价于 s = (short) (s + 1)

    }
}
