import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem01 {
    public static void main(String[] args) {
        ArrayList<SSStudent> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        loop: while (true){
            System.out.println("----------------------欢迎来到云云的学生管理系统------------------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入你的选择：");
            String choose = sc.next();
            switch (choose){
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("退出");
                    break loop;
                }
                default -> System.out.println("没有这个选项请重新输入！！！");
            }
        }
    }

    // 添加学生
    public static void addStudent(ArrayList<SSStudent> list){
        SSStudent stu = new SSStudent();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入学生id：");
            String id = sc.next();
            boolean flag = checkContains(list, id);
            if (flag) {
                stu.setId(id);
                break;
            } else {
                System.out.println("ID录入重复，请重新输入！！！");
            }
        }


        System.out.println("请输入学生姓名：");
        String name = sc.next();
        stu.setName(name);

        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        stu.setAge(age);

        System.out.println("请输入家庭住址：");
        String address = sc.next();
        stu.setAddress(address);

        //这里我漏了一步add，知道了为啥打印不出来了
        list.add(stu);

        System.out.println("学生信息添加成功！！！");
    }

    //删除学生
    public static void deleteStudent(ArrayList<SSStudent> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除的学生的id：");
        String id = sc.next();
        int index = getIndex(list, id);

        if (index >= 0){
            list.remove(index);
            System.out.println("id为： " + id + "删除成功！！");
        }else{
            System.out.println("你想删除的学生不存在！！！");
        }
    }

    //修改学生
    public static void updateStudent(ArrayList<SSStudent> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学生id：");
        String id = sc.next();
        int index = getIndex(list, id);

        if (index == -1){
            System.out.println("你要修改的学生不存在！！请重新输入");
            return;
        }
        SSStudent stu = list.get(index);
        System.out.println("输入你要修改的学生姓名：");
        String name = sc.next();
        stu.setName(name);

        System.out.println("请输入你要修改的学生年龄：");
        int age = sc.nextInt();
        stu.setAge(age);

        System.out.println("请输入你要修改的学生的住址：");
        String address = sc.next();
        stu.setAddress(address);

        System.out.println("修改成功！！！");
    }

    //查询学生
    public static void queryStudent(ArrayList<SSStudent> list){
        if (list.isEmpty()){
            System.out.println("当前没有学生信息请添加后再查询！！！");
            return;
        }

        System.out.println("id\t\t姓名\t\t年龄\t\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            SSStudent temp = list.get(i);
            System.out.println(temp.getId() + "\t\t" + temp.getName() + "\t\t" + temp.getAge() + "\t\t" + temp.getAddress());
        }
    }

    //判断id是否存在
    public static boolean checkContains(ArrayList<SSStudent> list, String id){
        /*
        for (int i = 0; i < list.size(); i++) {
            SSStudent temp = list.get(i);
            String stu_temp = temp.getId();
            if (stu_temp.equals(id)){
                return false;
            }
        }
         */
        return getIndex(list, id) == -1;
    }

    //找删除id的索引的方法
    public static int getIndex(ArrayList<SSStudent> list, String id){
        for (int i = 0; i < list.size(); i++) {
            SSStudent stu = list.get(i);
            String stuID = stu.getId();
            if (stuID.equals(id)){
                return i;
            }
        }

        return -1;
    }
}
