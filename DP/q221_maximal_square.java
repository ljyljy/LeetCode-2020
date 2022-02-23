package DP;

public class q221_maximal_square {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1]; // mat[i-1][j-1]处的最大边长
        int maxSide = 0; // 最大边长
        // 初始化：最上、最左哨兵（边界全0，略）
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {// ?勿漏if！
                    dp[i][j] = Math.min(dp[i-1][j-1],
                            Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
