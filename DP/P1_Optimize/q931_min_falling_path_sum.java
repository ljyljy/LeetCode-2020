package DP.P1_Optimize;

import java.util.Arrays;

public class q931_min_falling_path_sum {
    int[][] matrix, memo;
    int n;
    private final int INIT = -66666;
    // 法2：dp, 顺推
    public int minFallingPathSum(int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length; // m == n
        int[][] dp = new int[n+1][n+2]; // base case: 加行哨兵-top、列哨兵-左右边界[0;n+1]
        for (int i = 0; i < n+1; i++) {
//            Arrays.fill(dp[0], 0); // 自动初始化为0
            dp[i][0] = Integer.MAX_VALUE;// 左右两列-边界
            dp[i][n+1] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= n; j++) { // 保证col下标∈[j-1:j+1]合法∈[1, n+1]
                dp[i][j] = matrix[i-1][j-1] +
                    Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i-1][j+1]));
            }
        }
        // 可行解都在 dp[n - 1][1...n-1] 中
        return Arrays.stream(dp[n]).min().getAsInt();
    }


    // 法1：dfs + memo
    public int minFallingPathSum_dfs(int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length; // m == n
        int minSum = Integer.MAX_VALUE;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], INIT);
        }
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, dfs(0, j));
        }
        return minSum;
    }

    private int dfs(int row, int col) {
        if (row == n) return 0;
        if (memo[row][col] != INIT) return memo[row][col];
        memo[row][col] = matrix[row][col]; // 初始化
        int minNxt = Integer.MAX_VALUE;
        if (col-1 >= 0) {
            minNxt = Math.min(minNxt, dfs(row+1, col-1));
        }
        minNxt = Math.min(minNxt, dfs(row+1, col));
        if (col+1 < n) {
            minNxt = Math.min(minNxt, dfs(row+1, col+1));
        }
        memo[row][col] += minNxt;
        return memo[row][col];
    }
}
