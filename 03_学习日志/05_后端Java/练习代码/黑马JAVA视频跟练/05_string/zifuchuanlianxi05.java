import java.util.Scanner;

public class zifuchuanlianxi05 {
    public static void main(String[] args) {
        String userName = "yunbao";
        String rightPassword = "1314520";

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入你的用户名：");
            String user = sc.next();
            System.out.println("请输入账号密码：");
            String password = sc.next();

            if (userName.equals(user) && rightPassword.equals(password)){
                System.out.println("用户登录成功");
                break;
            }else{
                if (i == 2){
                    System.out.println("你的次数已经用光，请联系客服处理！");
                }else{
                    System.out.println("输入的账号或者密码存在错误，请重新输入，当前你还剩下" + (2 - i) + "次机会.");
                }
            }
        }
    }
}
