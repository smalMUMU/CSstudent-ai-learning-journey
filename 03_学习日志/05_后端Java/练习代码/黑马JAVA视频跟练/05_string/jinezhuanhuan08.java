import java.util.Scanner;

public class jinezhuanhuan08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money;
        while (true){
            System.out.println("请输入你的金额：");
            money = sc.nextInt();
            if (money >= 0 && money <= 9999999){
                break;
            }else{
                System.out.println("你输入的金额范围异常，请重新输入！！！");
            }
        }

        String moneyStr = "";
        while (true){
            if (money == 0){
                break;
            }
            int num = money % 10;
            moneyStr = getCaplitNumber(num) + moneyStr;
            money /= 10;
        }
        //这个是测试点，看代码是否正常运行
        /*
        for (int i = 0; i < moneyStr.length(); i++) {
            char c = moneyStr.charAt(i);
            System.out.println(c);
        }
         */

        int count = 7 - moneyStr.length();
        for (int i = 0; i < count; i++) {
            moneyStr = "零" + moneyStr;
        }
        //测试点
        /*
        for (int i = 0; i < moneyStr.length(); i++) {
            char c = moneyStr.charAt(i);
            System.out.println(c);
        }
         */

        String[] arr = {"佰", "十", "万", "千", "佰", "十", "元"};

        String result = "";

        for (int i = 0; i < moneyStr.length(); i++) {
            char c = moneyStr.charAt(i);
            result = result + c + arr[i];
        }

        System.out.println(result);


    }

    //把数字转换成汉字的方法
    public static String getCaplitNumber(int num){
        String[] arr = {"零", "壹", "贰", "叁", "四", "伍", "陆", "柒", "捌", "玖"};

        return arr[num];
    }
}
