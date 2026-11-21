import java.util.Scanner;

public class tongjizifugeshu06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入你想统计个数的字符串：");
        String str = sc.next();

        int smallCount = 0;
        int bigCount = 0;
        int numberCount = 0;
        int otherCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z'){
                smallCount++;
            }else if (c >= 'A' && c <= 'Z'){
                bigCount++;
            }else if (c >= '0' && c <= '9'){
                numberCount++;
            }else{
                otherCount++;
            }
        }

        System.out.println("小写字母有" + smallCount + "个");
        System.out.println("大写字母有" + bigCount + "个");
        System.out.println("数字有" + numberCount + "个");
        System.out.println("其他字符有" + otherCount + "个");
    }
}
