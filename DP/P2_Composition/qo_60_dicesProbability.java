package DP.P2_Composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class qo_60_dicesProbability {
    // 二维数组，n 个骰子所以有 n 行，而每一行代表有 n 个骰子时，各值的出现次数，即如果2个骰子，各值的取值范围是 2 ~ 12(2 * 6)，出现次数取决于上一层的值(1 ~ 6)
    // n + 1 的原因是，第 0 行浪费啦，为了便于理解，第一个骰子对应第一行，而不是第 0 行
    // 每一行值范围是 n * 6 + 1 的原因跟上面一样，比如 2 个骰子，取值从 2 开始，到 12 结束，所以要多开一列，容纳 12 这个下标
    // 法2: 概率型DP
//    f[i][j]表示投掷i次，和为j的概率
//    f[i][j] = f[i-1][j-k] / 6, j>=k
    public double[] dicesProbability(int n) {
        double[][] dp = new double[n+1][n*6+1]; // 投掷 i 次，点数和 j 的出现次数。
        double p = 1.0 / 6;  // 每次投掷，每个点（1~6）的概率相等
        // 初始化，投掷一次骰子，得到1到6每个点的概率都是六分之一
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = p;
        }
        for (int i = 2; i <= n; i++) { // 投掷次数
            for (int j = i; j <= i * 6; j++) {  // 累计点数和
                for (int cur = 1; cur <= 6; cur++) {  // 当前骰子点数
                    if (j - cur >= 0) {
                        dp[i][j] += dp[i-1][j - cur] * p;
                    }
                }
            }
        }

        double[] res = new double[n*6+1]; // 投掷 n 次，点数和∈[n,6*n]
        int idx = 0;
        for (int j = 0; j <= n * 6; j++) {
            if (dp[n][j] != 0) {
                res[idx++] = dp[n][j];
            }
        }
        return Arrays.copyOf(res, idx);
    }


    // 法1: 求次数 / allCnts=6^n
    public double[] dicesProbability2(int n) {
        int[][] dp = new int[n+1][6*n+1]; // 投掷完 i 次骰子后，点数和 j 的出现次数。
        // 初始化
        for (int i = 1; i <= 6; i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) { // 投掷次数
            for (int j = i; j <= 6*i; j++) { // 累计点数和
                for (int cur = 1; cur <= 6; cur++) { // 当前骰子点数
                    if (j - cur >= 0) {
                        dp[i][j] += dp[i-1][j-cur];
                    }
                }

            }
        }

        // 1骰子 - 1~6 -> 6次
        // 2骰子 - [1,1],[1,2] ...[6,6] -> 6^2次, 种类=2~12 --> 11种=5n+1
        double all = Math.pow(6, n); // 骰子累计点数和的【排列总数】
        // double[] res = new double[5*n+2]; // [1]*n, ...[6]*n --> 共6n-n+1=5n+1个
        List<Double> res = new ArrayList<>();
        for (int j = 1; j <= 6*n; j++) {
            // dp[n][j]：投掷n次骰子，点数为j的次数
            if (dp[n][j] == 0) continue;
            res.add(dp[n][j] / all);
        }

        double[] res_ = new double[res.size()];
        int k = 0;
        for (double ans: res) {
            res_[k++] = ans;
        }
        return res_;
    }
}
