package DP;

public class q96_unique_BST {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) { // 枚举n
            for (int j = 1; j <= i; j++) { // 以j为root
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}
