package Recursion.graph;

public class q329_longest_increasing_path_in_a_matrix {
//    ������dp��
    private int[] _x = { 0, 1, 0, -1};
    private int[] _y = {-1, 0, 1, 0};
    private int[][] matrix, memo;
    private int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix; m = matrix.length; n = matrix[0].length;
        memo = new int[m][n];
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curLen = dfs(i, j);
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }

    private int dfs(int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j]++; // pathLen���뵱ǰ���

        for (int dir = 0; dir < 4; dir++) {
            int newI = i + _x[dir], newJ = j + _y[dir];
            // �߽��� & mat[newI, newJ]����
            if (isValid(newI, newJ) && matrix[newI][newJ] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j],
                        dfs(newI, newJ) + 1); // ������(newI, newJ)�ߣ�pathLen+1������dp��
            }
        }
        return memo[i][j];
    }

    private boolean isValid(int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }
}
