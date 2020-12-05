package CodingPractice;

import java.util.Arrays;

class MatrixDp {
    //define a Pair object to store min and max path product at every cell in the grid

    public static void main(String[] args) {
//        int[][] mat = {{1,-2,1},
//                {1,-2,1},
//                {3,-4,1}};
//        System.out.println(maxProductPath(mat));
        System.out.println(checkPalindromeFormation("ulacfd", "jizalu"));
    }
    public static boolean isPa(String s, int i, int j) {
        for (; i < j; ++i, --j)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    public static boolean check(String a, String b) {
        for (int i = 0, j = a.length() - 1; i < j; ++i, --j)
            if (a.charAt(i) != b.charAt(j)) {
                System.out.println(i + " -- " + j);
                return isPa(a, i, j) || isPa(b, i, j);
            }
        return true;
    }

    public static boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }
    static class Pair{
        Long min;
        Long max;
        Pair(Long min, Long max){
            this.min=min;
            this.max=max;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }



    public static int maxProductPath(int[][] grid) {
        if(grid.length==0)
            return 0;

        int r = grid.length;
        int c = grid[0].length;
        int mod = 1000000007;

        Pair[][] dp = new Pair[r][c];

        //initialize first cell as (grid[0][0],grid[0][0]) since min and max are the same here
        dp[0][0]= new Pair((long)grid[0][0],(long)grid[0][0]);

        //initialize first row, min=max= prev row product* grid[i][j]
        for(int i=1;i<r;i++)
            dp[i][0]= new Pair(dp[i-1][0].min*grid[i][0],dp[i-1][0].max*grid[i][0]);
        System.out.println(Arrays.deepToString(dp));
        //initialize first column, min=max= prev col product* grid[i][j]
        for(int j=1;j<c;j++)
            dp[0][j]= new Pair(dp[0][j-1].min*grid[0][j],dp[0][j-1].max*grid[0][j]);
        System.out.println(Arrays.deepToString(dp));
        //from grid[1][1] to grid[r][c]
        for(int i=1;i<r;i++){
            for(int j=1;j<c;j++){
                long min = grid[i][j] * Math.min(dp[i-1][j].min,dp[i][j-1].min);
                long max = grid[i][j] * Math.max(dp[i-1][j].max,dp[i][j-1].max);
                dp[i][j] = new Pair(Math.min(min,max), Math.max(min,max));
            }
        }
        return dp[r-1][c-1].max<0?-1:(int)(dp[r-1][c-1].max%mod);
    }
}