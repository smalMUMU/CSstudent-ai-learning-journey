public class luoji09 {
    public static void main(String[] args)
    {
        //这个文件拿来探讨Java中的逻辑运算符，
        //第一个且 &
        System.out.println(true & true); // 输出true
        System.out.println(true & false); //false
        System.out.println(false & true); // false
        System.out.println(false & false); //false

        //分割线
        System.out.println("----------------------------------------");

        //第二个或 |
        System.out.println(true | true); //true
        System.out.println(true | false); //true
        System.out.println(false | true); //true
        System.out.println(false | false); //false

        //分割线
        System.out.println("------------------------------------------");

        //第三个异或，^
        System.out.println(true ^ true);
        System.out.println(true ^ false);
        System.out.println(false ^ true);
        System.out.println(false ^ false);


        //第四个，非！
        System.out.println(! true);  //输出为false说明可以用空格隔开美观
        System.out.println(!true);
        System.out.println(!false);

    }
}
