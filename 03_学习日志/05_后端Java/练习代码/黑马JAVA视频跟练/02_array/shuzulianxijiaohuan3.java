public class shuzulianxijiaohuan3 {
    public static void main(String[] args)
    {
        //这次采用黑马的写法，看看和我的写法有什么出入

        int[] arr = {1, 2, 3, 4, 5};

        for (int i = 0, j = arr.length - 1; i < j; i++, j--){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        //一个for循环里面有俩个参数变量
        //没明白用意为啥不直接使用一个变量？？？反正都有关联对称性质
    }
}
