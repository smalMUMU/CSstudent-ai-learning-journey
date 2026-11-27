import java.util.ArrayList;

public class lianxi001 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        System.out.println(list);
        String str = list.remove(0);
        System.out.println(str);
        System.out.println(list);

        String str2 = list.remove(2);
        System.out.println(list);
        System.out.println(str2);

        String str3 = list.set(1, "mmm");
        System.out.println(str3);
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            String str4 = list.get(i);
            System.out.println(str4);
        }

    }
}
