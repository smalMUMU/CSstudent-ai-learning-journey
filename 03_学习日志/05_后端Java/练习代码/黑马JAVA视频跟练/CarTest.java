import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Car[] arr = new Car[3];

        for (int i = 0; i < arr.length; i++) {
            Car c = new Car();
            System.out.println("请输入你的车子品牌：");
            String brand = sc.next();
            c.setName(brand);

            System.out.println("请输入汽车价格：");
            int price = sc.nextInt();
            c.setPrice(price);

            System.out.println("请输入汽车颜色：");
            String color = sc.next();
            c.setColor(color);

            arr[i] = c;
        }

        for (int i = 0; i < arr.length; i++) {
            Car one = arr[i];
            System.out.println(one.getName() + "," + one.getPrice() + "," + one.getColor());
        }
    }
}
