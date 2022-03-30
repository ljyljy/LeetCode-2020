package DP.P2_Composition;

import java.util.Scanner;

/**
 * 用递归来做，将右下角看做原点(0, 0)，左上角看做坐标(m, n)，下图所示：
 *
 * 从(m, n)—>(0, 0)就分两步走：
 * 往右走一步：f(m, n - 1)—>(0, 0) 加上下走一步：f(m - 1, n)—>(0, 0)
 * 注意：但凡是触碰到边界，也就是说f(x, 0)或者f(0,x)都只有一条直路可走了，这里的x是变量哈。
 * f(m, n) = f(m, n - 1) + f(m - 1, n)
 */
public class HJ91_board_cnt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt(), n = sc.nextInt();
            System.out.println(dp(m, n));
        }
    }

    private static int dp(int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1; // 【初始化】哨兵边界为1
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}
