public class fangfachongzai06 {
    public static void main(String[] args) {
        System.out.println(compare(10.0, 10));
        System.out.println(compare(41, 50));

    }

    public static int compare(int a, int b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    public static byte compare(byte a, byte b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    public static double compare(double a, double b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    public static short compare(short a, short b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    //构建重载方法
}
