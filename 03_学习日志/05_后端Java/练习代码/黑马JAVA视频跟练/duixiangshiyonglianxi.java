public class duixiangshiyonglianxi {
    public static void main(String[] args) {
        Gfriend girlFriend = new Gfriend();

        girlFriend.setName("小云云");
        girlFriend.setAge(18);        // 先合法
        girlFriend.setAge(-18);       // 再非法
        System.out.println(girlFriend.getAge());    // → 18（原值保留 ✅）

        System.out.println(girlFriend.getName());
        System.out.println(girlFriend.getAge());
        girlFriend.sleep();
        girlFriend.eat();
    }
}
