import java.util.ArrayList;

public class TestCStudent {
    public static void main(String[] args) {
        ArrayList<CStudent> list = new ArrayList<>();

        CStudent stu1 = new CStudent("xiaohong", 15);
        CStudent stu2 = new CStudent("xiaoming", 18);
        CStudent stu3 = new CStudent("chuyun", 18);

        list.add(stu1);
        list.add(stu2);
        list.add(stu3);

        for (int i = 0; i < list.size(); i++) {
            CStudent temp = list.get(i);
            System.out.println(temp.getName() + ", " + temp.getAge());
        }
    }
}
