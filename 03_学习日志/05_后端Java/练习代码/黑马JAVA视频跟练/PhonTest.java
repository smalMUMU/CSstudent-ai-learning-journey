public class PhonTest {
    public static void main(String[] args) {
        Phon[] arr = new Phon[3];
        
        Phon p1 = new Phon("小米", 58888, "绿色");
        Phon p2 = new Phon("华为", 9999, "黄色");
        Phon p3 = new Phon("Vivo", 5555, "红色");

        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            Phon p = arr[i];
            sum += p.getPrice();
        }

        System.out.println("总和：" + sum);

        double avg = sum * 1.0 / arr.length;

        System.out.println("平均值：" + avg);
    }
}
