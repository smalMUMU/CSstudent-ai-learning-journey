import java.util.Scanner;

public class zifuchuanfanzhuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想反转的字符串：");
        String str = sc.next();
        String str1 = fanzhuan(str);
        System.out.println(str);
        System.out.println(str1);
    }

    public static String fanzhuan(String str){
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            result = result + c;
        }
        return result;
    }
}
