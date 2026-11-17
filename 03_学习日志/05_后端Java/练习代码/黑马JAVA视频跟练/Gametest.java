public class Gametest {
    public static void main(String[] args) {

        wenzigedoulianxi r1 = new wenzigedoulianxi("小云", 100);
        wenzigedoulianxi r2 = new wenzigedoulianxi("小苏", 100);

        while (true) {
            r1.attack(r2);
            if (r2.getBlood() == 0) {
                System.out.println(r1.getName() + "K.O了" + r2.getName());
                break;
            }

            r2.attack(r1);
            if (r1.getBlood() == 0) {
                System.out.println(r2.getName() + "K.O了" + r1.getName());
                break;
            }
        }
    }
}
