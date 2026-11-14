public class erweishuzudongtaichushihua {
    public static void main(String[] args) {
        int[][] arr = new int[3][5];

        arr[0][0] = 91;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
