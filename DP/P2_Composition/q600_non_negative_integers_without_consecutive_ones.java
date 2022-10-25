package DP.P2_Composition;

import java.util.Arrays;

public class q600_non_negative_integers_without_consecutive_ones {
    char[] chs;
    int[][] memo;
    int n;
    public int findIntegers(int num) {
        chs = Integer.toString(num, 2).toCharArray(); // 或 Integer.toBinaryString(n)
        n = chs.length;
        memo = new int[n][2]; // <idx, prev=0/1>
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int idx, int prev, boolean isLimit) {
        if (idx == n) return 1; // cnt++
        if (!isLimit && memo[idx][prev] != -1) return memo[idx][prev];
        int top = isLimit? chs[idx] - '0': 1; // 二进制只有0~1
        int cnt = 0;
        for (int bit = 0; bit <= top; bit++) {
            if (prev == 1 && bit == 1) continue;// 跳过连续的 1
            cnt += dfs(idx+1, bit, isLimit && bit == top);
        }
        if (!isLimit) memo[idx][prev] = cnt;
        return cnt;
    }
}
