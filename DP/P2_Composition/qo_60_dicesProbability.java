package DP.P2_Composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class qo_60_dicesProbability {
    // 二维数组，n 个骰子所以有 n 行，而每一行代表有 n 个骰子时，各值的出现次数，即如果2个骰子，各值的取值范围是 2 ~ 12(2 * 6)，出现次数取决于上一层的值(1 ~ 6)
    // n + 1 的原因是，第 0 行浪费啦，为了便于理解，第一个骰子对应第一行，而不是第 0 行
    // 每一行值范围是 n * 6 + 1 的原因跟上面一样，比如 2 个骰子，取值从 2 开始，到 12 结束，所以要多开一列，容纳 12 这个下标
    public double[] dicesProbability(int n) {
        int[][] dp = new int[n+1][6*n+1]; // 投掷完 i 枚骰子后，点数和 j 的出现次数。
        // 初始化
        for (int i = 1; i <= 6; i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) { // 骰子数
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
