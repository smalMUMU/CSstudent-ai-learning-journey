public class bijiaochangfxdemianji04 {
    public static void main(String[] args) {
        double s1 = getarea(10.3, 20.6);
        double s2 = getarea(20.6, 14);

        if (s1 >= s2){
            System.out.println("s1更大");
        }else{
            System.out.println("s2更大");
        }

    }

    public static double getarea(double len, double width){
        double s = len * width;
        return s;
    }

    //方法写之前要问自己和思考的三个问题
    //我要干什么？
    //需要什么
    //函数调用处是否需要继续使用方法的值，那么方法必须使用返回值
}
