public class kongzhiyuju01 {
    public static void main(String[] args)
    {
        for (int i = 1; i <= 5; i++){
            if (i == 3){
                continue;
            }
            System.out.println("小老虎吃到了第" + i + "个包子");
        }
    }
    //挺有用的，之前以为continue就是继续
    //这个例子教会了我，continue是跳出当前循环然后执行下一次循环，还好这次学习Java补充到了基础
    //然后break的我没写，因为我感觉我对这个break比较熟悉，这个就是直接跳出当前循环,直接结束整个循环
}
