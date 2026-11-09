public class yunsuan07 {
    public static void main(String[] args)
    {
        //这个是探讨++，放在前面和后面有什么区别的一个文件

        int x = 10;
        int y = x++;
        int z = ++x;

        //输出

        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("z:" + z);

        //输出结果是 12 10 12
        // 我之前预判的结果是10 11 12
        //那么有什么区别呢？答案是我忽略了x的变化以为不影响x结果还是影响了x
        //然后++在后面是先赋值运算再+1，++在前面是先+1再赋值运算
    }
}
