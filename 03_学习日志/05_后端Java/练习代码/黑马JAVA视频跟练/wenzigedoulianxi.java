import java.util.Random;

public class wenzigedoulianxi{
    private String name;
    private int blood;

    public wenzigedoulianxi(){

    }

    public wenzigedoulianxi(String name, int blood){
        this.name = name;
        this.blood = blood;
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
}
