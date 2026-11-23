import java.util.Scanner;

public class duichengzifuchuanlianxi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String str = sc.next();

        String result = new StringBuilder().append(str).reverse().toString();

        if (str.equals(result)){
            System.out.println("当前字符串是对称字符串");
        }else{
            System.out.println("当前字符串不是对称的字符串!!");
        }
    }
}
