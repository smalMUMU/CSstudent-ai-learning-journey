public class shuzhulianxi1 {
    public static void main(String[] args)
    {
        int[] arr = {33, 5, 22, 44, 55};//这里黑马指出了max要用arr[0]来填写，因为如果数组都是负数的话，那么他们的值的最大值就不是0，我开
        //我开始写的max是0，原来如此，吸取教训了，以后只用已经有的数据不自己造数据
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        System.out.println(max);
    }
}
