import java.util.ArrayList;

public class jihedebianlilianxi {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("yunbao喜欢纱英");
        list.add("shaying喜欢云宝");
        list.add("99");

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1){
                System.out.print(list.get(i));
            }else{
                System.out.print(list.get(i) + ", ");
            }
        }
        System.out.print("]");
    }
}
