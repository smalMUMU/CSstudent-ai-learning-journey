public class Gfriend {
    private String name;
    private int age;

    public void setName(String n){
        name = n;
    }//作用给name成员赋值

    public String getName(){
        return name;
    }//对外提供name属性

    public void setAge(int a){
        if (a >= 18 && a <= 40){
            age = a;
        }else{
            System.out.println("非法数据请重新输入！！！");
        }
    }

    public int getAge(){
        return age;
    }


    public void sleep(){
        System.out.println("小云云在睡觉");
    }

    public void eat(){
        System.out.println("小云云在吃饭噢");
    }
}
