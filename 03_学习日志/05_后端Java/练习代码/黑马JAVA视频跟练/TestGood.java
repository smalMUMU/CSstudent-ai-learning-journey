public class TestGood {
    public static void main(String[] args) {
        Good[] arr = new Good[3];
        //这里报错了，然后我看黑马的视频调整过来了，这里我不明白的一个点就是为什么是Good[]这种形式

        Good g1 = new Good(001, "华为硕士电脑", 15000, 100);
        Good g2 = new Good(002, "MacBook", 16000, 100);
        Good g3 = new Good(003, "联想拯救者", 14999, 200);

        arr[0] = g1;
        arr[1] = g2;
        arr[2] = g3;

        for (int i = 0; i < arr.length; i++) {
            Good g = arr[i];
            System.out.println(g.getId() + "," + g.getName() + "," + g.getPrice() + "," + g.getKucun());
            //写到这里我又忘记了输出是什么，怎么输出的，先单独输出一个g试试看输出的是什么
            //输出的是地址，这里我又看了一下黑马的写法，应该是获取地址然后用get方法一个一个取出来？
            //先照着写一下试试看
        }


    }
}
