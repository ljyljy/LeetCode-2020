package DP;

import java.util.HashMap;
import java.util.Map;

public class q1269_cnts_2stay_same_place {
    // 法1：dfs + memo + 剪枝 (|pos-0| > 剩余steps)
    // -- 如：若走到离原点有5步的距离，而你还剩下4步可以走，无论你怎么走都不可能走到原点了。所以如果跑太远了，就不需要在计算了，可以返回0
    private final int MOD = (int)1e9 + 7;
    private int arrLen;
    public int numWays_dfs(int steps, int arrLen) {
        this.arrLen = arrLen; // 减少dfs传参
        Map<String, Integer> memo = new HashMap<>();
        return dfs(0, steps, memo);
    }

    private int dfs(int pos, int steps, Map<String, Integer> memo) {
        if (steps == 0) return pos == 0? 1 : 0; // 方案数=0/1(via累加), 勿写反！
        String key = pos + "_" + steps; // 下标_剩余步数
        if (memo.containsKey(key)) return memo.get(key);
        long res = 0;
        // 若与原点距离|pos-0| > 剩余steps, 则无解(剪枝)
        if (pos <= steps) {
            //计算往3个方向走的结果
            // 1. 停在原地
            res += dfs(pos, steps-1, memo) % MOD;
            // 2. 向前/左走1步
            if (pos + 1 < arrLen)
                res += dfs(pos+1, steps-1, memo) % MOD;
            // 3. 向后/右走1步
            if (pos - 1 >= 0)
                res += dfs(pos-1, steps-1, memo) % MOD;
            res %= MOD; // 不可以直接放入memo.put！必须更新赋值res！否则dfs下探时res会不准确！

        }
        memo.put(key, (int) res);
        return memo.get(key);
    }

    // 法2-1：动归（不剪枝pos_min, 会TLE !!!）
    public int numWays_dp1(int steps, int arrLen) {
        final int MOD = (int)1e9 + 7;
        int pos_min = Math.min(steps / 2, arrLen - 1); // 详见剪枝
        long[][] dp = new long[steps + 1][pos_min + 1];
        // 初始化dp： 另外dp[1][2:end] = 0, dp[0][:] = 0
        dp[1][0] = 1;
        if (pos_min >= 1) dp[1][1] = 1; //step>1时有效，否则dp[:][1]越界
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= pos_min; j++) {
                // 1. 停在原地
                dp[i][j] += dp[i-1][j];
                // 2. 从左边来
                if (j - 1 >= 0)
                    dp[i][j] += dp[i-1][j-1];
                // 3. 从右边来
                if (j + 1 <= pos_min)
                    dp[i][j] += dp[i-1][j+1];
                dp[i][j] %= MOD;
            }
        }
        return (int)dp[steps][0];
    }

    // 法2-2：动归（剪枝pos_min + 滚动数组）
    public int numWays(int steps, int arrLen) {
        final int MOD = (int)1e9 + 7;
        int mod = 2; // 滚动数组
        int pos_min = Math.min(steps / 2, arrLen - 1); // 详见剪枝
        long[][] dp = new long[mod][pos_min + 1];
        // 初始化dp： 另外dp[1][2:end] = 0, dp[0][:] = 0
        dp[1][0] = 1;
        if (pos_min >= 1)  dp[1][1] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= pos_min; j++) {
                // 1. 停在原地
                if (i != 1) // ❤ dp[1][0] 不可被dp[0]覆盖
                    dp[i % mod][j] = dp[(i-1) % mod][j]; // 当前轮次的滚动数组需要直接覆盖旧值(上上轮)，而非累加！
                // 2. 从左边来
                if (j - 1 >= 0)
                    dp[i % mod][j] += dp[(i-1) % mod][j-1];
                // 3. 从右边来
                if (j + 1 <= pos_min)
                    dp[i % mod][j] += dp[(i-1) % mod][j+1];
                dp[i % mod][j] %= MOD;
            }
        }
        for (int i = 0; i < dp.length; i++)
            System.out.println(dp[i][0]);
        return (int)dp[(steps) % mod][0];
    }


    // 法2-x：动归 - TLE
    public int numWays_TLE(int steps, int arrLen) {
        final int MOD = (int)1e9 + 7;
        long[][] dp = new long[steps+1][arrLen+1];
        dp[1][0] = 1; dp[1][1] = 1; // dp[1][2:end] = 0, dp[0][:] = 0
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                // 1. 停在原地
                dp[i][j] += dp[i-1][j];
                // 2. 从左边来
                if (j - 1 >= 0)
                    dp[i][j] += dp[i-1][j-1];
                // 3. 从右边来
                if (j + 1 < arrLen)
                    dp[i][j] += dp[i-1][j+1];
                dp[i][j] %= MOD;
            }
        }
        return (int)dp[steps][0];
    }

}
