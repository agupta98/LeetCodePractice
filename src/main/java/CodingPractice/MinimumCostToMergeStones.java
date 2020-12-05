package CodingPractice;

import java.util.Arrays;

public class MinimumCostToMergeStones {
    public static void main(String[] args) {
        int[] stones = {3,5,1,2,6};
        int k = 3;
        System.out.println(mergeStones(stones, k));
    }

    public static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }
        System.out.println(Arrays.toString(prefixSum));
        int[][] dp = new int[n][n];
        for (int len = k; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int t = i; t < j; t += k - 1) {
                    System.out.println(i + " -- " +j + " -- " + t + " - "+ dp[i][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][t] + dp[t + 1][j]);
                    System.out.println(Arrays.deepToString(dp));
                }
                if ((j - i) % (k - 1) == 0) {
                    dp[i][j] += prefixSum[j + 1] - prefixSum[i];
                }
               System.out.println("Outside " + Arrays.deepToString(dp));
            }
        }
        return dp[0][n - 1];
    }
}



//class Solution {
//    public:
//    int mergeStones(vector<int>& stones, int K) {
//        int n = stones.size();
//        if ((n - 1) % (K - 1) != 0) return -1;
//        vector<int> sums(n + 1);
//        vector<vector<int>> dp(n, vector<int>(n));
//        for (int i = 1; i < n + 1; ++i) {
//            sums[i] = sums[i - 1] + stones[i - 1];
//        }
//        for (int len = K; len <= n; ++len) {
//            for (int i = 0; i + len <= n; ++i) {
//                int j = i + len - 1;
//                dp[i][j] = INT_MAX;
//                for (int t = i; t < j; t += K - 1) {
//                    dp[i][j] = min(dp[i][j], dp[i][t] + dp[t + 1][j]);
//                }
//                if ((j - i) % (K - 1) == 0) {
//                    dp[i][j] += sums[j + 1] - sums[i];
//                }
//            }
//        }
//        return dp[0][n - 1];
//    }
//};
