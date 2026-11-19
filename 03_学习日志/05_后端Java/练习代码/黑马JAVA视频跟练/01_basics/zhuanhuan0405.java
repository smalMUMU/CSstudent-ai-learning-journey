public class zhuanhuan0405 {
    public static void main(String[] args)
    {
        //这个文件是拿来探究，数据强制转换之间的关系的
        byte a = 20;
        byte c = 30;
        byte b = (byte) (a + c);

        System.out.println(b);
        //第一次报错了，为什么呢？
        //从刚才上课的讲解来看，原因是因为这个转换只转换了a，并没有转换整体的运算结果
        //所以得出结论，含运算符的降转，强制转换要给运算式子加上括号
        //加上括号输出结果正确，结论得以验证
        //还要注意强制转换还要考虑数据移除问题，如果溢出的byte数字int强制转换为byte，会报错
    }
}
