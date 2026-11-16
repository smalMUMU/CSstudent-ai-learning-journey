public class Gfriend {
    private String name;
    private int age;

    public Gfriend(String name, int age){
        this.name = name;
        this.age = age;
        setAge(age);
    }

    public void setName(String name){
        this.name = name;
    }//作用给name成员赋值

    public String getName(){
        return name;
    }//对外提供name属性

    public void setAge(int age){
        if (age >= 18 && age <= 40){
            this.age = age;
        }else{
            System.out.println("非法数据请重新输入！！！");
        }
    }

    public int getAge(){
        return age;
    }


    public void sleep(){
        System.out.println(name + "在睡觉");
    }

    public void eat(){
        System.out.println(name + "在吃饭噢");
    }
}
