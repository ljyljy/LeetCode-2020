package DP.P2_Composition;

import java.util.Scanner;

/**
 * �õݹ������������½ǿ���ԭ��(0, 0)�����Ͻǿ�������(m, n)����ͼ��ʾ��
 *
 * ��(m, n)��>(0, 0)�ͷ������ߣ�
 * ������һ����f(m, n - 1)��>(0, 0) ��������һ����f(m - 1, n)��>(0, 0)
 * ע�⣺�����Ǵ������߽磬Ҳ����˵f(x, 0)����f(0,x)��ֻ��һ��ֱ·�����ˣ������x�Ǳ�������
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
                if (i == 0 || j == 0) dp[i][j] = 1; // ����ʼ�����ڱ��߽�Ϊ1
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}
