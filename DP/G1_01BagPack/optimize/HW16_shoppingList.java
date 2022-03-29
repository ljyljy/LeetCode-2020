package DP.G1_01BagPack.optimize;

import java.util.Scanner;

public class HW16_shoppingList {
    // 01背包
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // N：总金额/可用钱数，m: 物品个数
            int N = sc.nextInt(), m = sc.nextInt();
            N /= 10;// 由于价格是10的整数倍，处理一下以降低空间/时间复杂度
//             int[] v = new int[m], p = new int[m], q = new int[m];
            // ??? 数据结构的定义！
            int[][] prices = new int[m+1][3]; // 3：<0:主件，1：附件1,2：附件2>?
            int[][] pv = new int[m+1][3]; // v*p, 价格*价值

            for (int i = 1; i <= m; i++) {
                // v价格，p价值，q主件=0/附件!=0,而是主件号
                int v = sc.nextInt() / 10, p = sc.nextInt(), q = sc.nextInt();
                if (q == 0) { // 主件
                    prices[i][0] = v;
                    pv[i][0] = v*p;
                } else {
                    if (prices[q][1] != 0) {
                        prices[q][1] = v;
                        pv[q][1] = v*p;
                    } else {
                        prices[q][2] = v;
                        pv[q][2] = v*p;
                    }
                }
            }

            // 01背包【二维dp】
            int[][] dp = new int[m+1][N+1]; //前i个主件，金额j时的最大p*v
            for (int i = 1; i <= m; i++) { // 外：遍历物品++
                for (int j = 1; j <= N; j++) {// 内：遍历背包
                    int w0 = prices[i][0], pv0 = pv[i][0];
                    int w1 = prices[i][1], pv1 = pv[i][1];
                    int w2 = prices[i][2], pv2 = pv[i][2];
                    // 持续比较
                    dp[i][j] = j-w0>=0? Math.max(dp[i-1][j], dp[i-1][j-w0]+pv0) : dp[i-1][j];// 主件
                    dp[i][j] = j-w0-w1>=0? Math.max(dp[i][j], dp[i-1][j-w0-w1]+pv0+pv1) : dp[i][j];// 主件+附件1
                    dp[i][j] = j-w0-w2>=0? Math.max(dp[i][j], dp[i-1][j-w0-w2]+pv0+pv2) : dp[i][j]; // ?主件+附件2 勿忘！！
                    dp[i][j] = j-w0-w1-w2>=0? Math.max(dp[i][j], dp[i-1][j-w0-w1-w2]+pv0+pv1+pv2) : dp[i][j];// 主件+附件1&2
                }
            }
            System.out.println(dp[m][N] * 10);
        }
    }
}
