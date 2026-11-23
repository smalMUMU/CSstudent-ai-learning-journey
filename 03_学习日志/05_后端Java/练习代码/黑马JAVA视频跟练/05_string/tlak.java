public class tlak {
    public static void main(String[] args) {
        String talk = "cnmd,caibi你玩的真好，cnmd";

        String result = talk.replace("cnmd", "****");
        System.out.println(result);


        String[] arr = {"cnmd", "caibi", "NMSL", "MLGB"};
        for (int i = 0; i < arr.length; i++) {
            talk = talk.replace(arr[i], "***");
        }
        System.out.println(talk);
    }
}
