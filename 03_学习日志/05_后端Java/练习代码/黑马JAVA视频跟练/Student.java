public class Student {

    // ===== 5 分钟作业：不看任何旧代码，按六步填完 =====
    // ① 想出这个类有哪些数据（3 个字段，自己定，比如 姓名 / 年龄 / 成绩）
    // ② 把 3 个字段写成 private
    // ③ 写一个【无参构造】public Student(){ }
    // ④ 写一个【带参构造】public Student(参数…){ this.x = x; … }
    //    有约束的字段（比如年龄、成绩）在构造里也要校验
    // ⑤ 每个字段一对 getXxx() / setXxx()（需要校验的 set 里加 if）
    // ⑥ 一个业务方法（例如 打印自己的全部信息 / 判断成绩是否及格）
    //
    // 判据：一次不查语法写完、编译通过
    // 顺序永远不变：① 想数据 → ② 字段 → ③ 无参构造 → ④ 带参构造 → ⑤ get/set → ⑥ 业务方法

    private String name;
    private int age;
    private int score;

    public Student(){

    }

    public Student(String name, int age, int score){
        this.name = name;
        setAge(age);
        setScore(score);
    }

    public int getAge(){
        return age;
    }
    public int getScore(){
        return score;
    }
    public String getName(){
        return name;
    }

    public void setAge(int age){
        if (age >= 15 && age <= 30){
            this.age = age;
        }
    }
    public void setName(String name){
        this.name = name;
    }
    public void setScore(int score){
        if (score >= 0 && score <= 100){
            this.score = score;
        }
    }

}
