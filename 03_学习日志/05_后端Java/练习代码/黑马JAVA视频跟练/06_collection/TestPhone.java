import java.util.ArrayList;

public class TestPhone {
    public static void main(String[] args) {
        ArrayList<CPhone> list = new ArrayList<>();

        CPhone p1 = new CPhone("小米", 1888);
        CPhone p2 = new CPhone("苹果", 9999);
        CPhone p3 = new CPhone("华为", 5000);

        list.add(p1);
        list.add(p2);
        list.add(p3);

        ArrayList<CPhone> new_list = getPriceinfo(list);
        for (int i = 0; i < new_list.size(); i++) {
            CPhone temp = new_list.get(i);
            System.out.println(temp.getPrice() + ", " + temp.getBrand());
        }
    }

    //这里写一个返回的方法
    public static ArrayList<CPhone> getPriceinfo(ArrayList<CPhone> list){
        ArrayList<CPhone> new_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CPhone temp = list.get(i);
            if (temp.getPrice() <= 6000){
                new_list.add(temp);
            }
        }

        return new_list;
    }
}
