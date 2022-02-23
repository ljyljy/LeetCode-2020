package DP;

public class q221_maximal_square {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1]; // mat[i-1][j-1]�������߳�
        int maxSide = 0; // ���߳�
        // ��ʼ�������ϡ������ڱ����߽�ȫ0���ԣ�
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {// ?��©if��
                    dp[i][j] = Math.min(dp[i-1][j-1],
                            Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
