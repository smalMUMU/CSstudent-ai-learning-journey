public class Sstudent {
    private int id;
    private String name;
    private int age;

    public Sstudent(){

    }

    public Sstudent(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 15 && age <= 20){
            this.age = age;
        }
    }
}
