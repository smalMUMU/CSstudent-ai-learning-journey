public class panduanzhishu02 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 100; i <= 200; i++){
            boolean flag = true;
            for (int j = 2; j * j < i; j++){
                if (i % j == 0){
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println("当前数字" + i + "为质数");
                count++;
            }
        }
        System.out.println("一共有" + count + "质数");
    }
}
