package CodingPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSubMatrix {

    public static void main(String[] args) {
        int[][] mat = {{1,0,1},
                {1,1,0},
                {1,1,0}};
        System.out.println(numSubmat(mat));
        String email = "testabc@gmail.com";
        String[] username = email.split("@");
        char[] maskedEmail = new char[username[0].length()];
        maskedEmail[0] = username[0].charAt(0);
        Arrays.fill(maskedEmail, 1, username[0].length() - 1, '*');
        maskedEmail[username[0].length()-1] = username[0].charAt(username[0].length() - 1);
        System.out.println(new String(maskedEmail)+"@" +username[1]);
      //  String maskedEmail =  username[0].substring(0, 1) +

    }

    public static int numSubmat(int[][] mat) {

        int M = mat.length, N = mat[0].length;

        int res = 0;
        for (int up = 0; up < M; ++up) {
            int[] h = new int[N];
            List<Integer> al = new ArrayList<>();
            Arrays.fill(h, 1);
            for (int down = up; down < M; ++down) {
                for (int k = 0; k < N; ++k) {
                    al.add(mat[down][k]);
                    h[k] &= mat[down][k];
                }
                System.out.println(al);
                System.out.println(Arrays.toString(h));
             //   System.out.println();
                res += countOneRow(h);
            }
            System.out.println();
        }

        return res;
    }

    private static int countOneRow(int[] A) {

        int res = 0, length = 0;
        for (int i = 0; i < A.length; ++i) {
            length = (A[i] == 0 ? 0 : length + 1);
            res += length;
        }
        return res;
    }
}
