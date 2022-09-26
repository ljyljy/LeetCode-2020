package DP.P2_Composition;

public class q688_knight_probability_in_chessboard {
    private int n;
    private final int _x[] = {-2, -2, 2, 2, -1, -1, 1, 1};
    private final int _y[] = {-1, 1, -1, 1, -2, 2, -2, 2};

    // 法1：概率型dp
    public double knightProbability(int n, int steps, int startX, int startY) {
        double[][][] dp = new double[steps+1][n][n]; // 走k步，棋盘n*n
        double p = 1.0 / 8; // 等概率
        this.n = n;

        for (int step = 0; step <= steps; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[0][i][j] = 1; // 初始化
                    } else { // 动规，迭代
                        for (int dir = 0; dir < 8; dir++) {
                            int newX = i + _x[dir], newY = j + _y[dir];
                            if (!isValid(newX, newY)) continue;
                            dp[step][i][j] += dp[step-1][newX][newY] * p;
                        }
                    }
                }
            }
        }
        return dp[steps][startX][startY];
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n ;
    }

    // 法2：dfs + memo
    // 提示：1 <= n <= 25, 0 <= k <= 100
    double[][][] dp = new double[101][26][26]; // 走k步，棋盘n*n, 默认全0
    double p = 1.0 / 8; // 等概率

    public double knightProbability_dfs(int n, int steps, int startX, int startY) {
        this.n = n;
        return dfs(steps, startX, startY);
    }

    private double dfs(int step, int x, int y) {
        if (!isValid(x, y)) return 0; // 必须加在递归出口！
        if (step == 0) return 1;
        if (dp[step][x][y] != 0) return dp[step][x][y]; // memo

        // 以下：dp[step][x][y] == 0，未计算过
        for (int dir = 0; dir < 8; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            // if (!isValid(newX, newY)) return 0; // 不可加！WA！！
            dp[step][x][y] +=  1.0 / 8 * dfs(step-1, newX, newY) ;
        }
        return dp[step][x][y];
    }
}
