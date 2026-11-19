import java.util.Random;

public class wenzigedoulianxi{
    private String name;
    private int blood;
    private char gender;
    private String face;

    String[] boyface = {"白净", "飒爽", "兰花", "d"};
    String[] girlface = {"娇柔", "温柔", "娇气", "白莲", "n"};

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFace() {
        return face;
    }

    public void setFace(char gender) {
        Random r = new Random();
        if (gender == '男'){
            int index = r.nextInt(boyface.length);
            this.face = boyface[index];
        }else if (gender == '女'){
            int index = r.nextInt(girlface.length);
            this.face = girlface[index];
        }else{
            this.face = "相貌平平";
        }
    }

    public wenzigedoulianxi(){

    }

    public wenzigedoulianxi(String name, int blood, char gender){
        this.name = name;
        this.blood = blood;
        this.gender = gender;
        setFace(gender);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getBlood(){
        return blood;
    }

    public void setBlood(int blood){
        this.blood = blood;
    }

    public void attack(wenzigedoulianxi role){
        Random r = new Random();
        int hurt = r.nextInt(20) + 1;

        int remainBlood = role.getBlood() - hurt;
        remainBlood = remainBlood < 0 ? 0 : remainBlood;
        role.setBlood(remainBlood);

        System.out.println(this.getName() + "举起拳头，打了" + role.getName() + "一下，" + "造成了" + hurt + "点伤害，" + role.getName() + "还剩下了" + remainBlood + "点血");
    }

    public void showRoleInfo(){
        System.out.println("姓名为：" + getName());
        System.out.println("性别为：" + getGender());
        System.out.println("血量为：" + getBlood());
        System.out.println("外貌为：" + getFace());
    }
}
