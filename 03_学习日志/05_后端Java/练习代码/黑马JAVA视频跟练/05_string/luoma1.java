import java.util.Scanner;

public class luoma1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true){
            System.out.println("输入你的字符串数字：");
            str = sc.next();
            boolean flag = checkStr(str);
            if (flag){
                break;
            }else{
                System.out.println("你输入的不是合法数字字符串！！请重新输入");
            }
        }

        StringBuilder strr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int num = c - 48;
            String luo = getLuoma(num);
            strr.append(luo);
        }

        String end_str = strr.toString();

        System.out.println(end_str);

    }
    //还要写一个变换成罗马数字的方法
    public static String getLuoma(int num){
        String[] str = {"", "I ", "II ", "III ", "IV ", "V ", "VI ", "VII ", "VIII ", "IX "};
        return str[num];
    }


    //这是判断是否合理的一个
    public static boolean checkStr(String str){
        if (str.length() > 9 ){
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9'){
                return false;
            }
        }

        return true;
    }
}
