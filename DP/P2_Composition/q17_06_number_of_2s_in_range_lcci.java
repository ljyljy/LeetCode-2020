package DP.P2_Composition;

import java.util.Arrays;

public class q17_06_number_of_2s_in_range_lcci {
    char[] numChs;
    int[][] memo;
    int n;

    public int numberOf2sInRange(int num) {
        numChs = String.valueOf(num).toCharArray();
        n = numChs.length;
        // num∈[0, 1e9], 最多10位数（< 10个1）
        memo = new int[n][n]; // "idx_OneCnt"
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int idx, int twoCnt, boolean isLimit) {
        if (idx == n) return twoCnt;
        if (!isLimit && memo[idx][twoCnt] != -1) return memo[idx][twoCnt];
        int cnt = 0;
        int top = isLimit ? numChs[idx] - '0' : 9;
        for (int bit = 0; bit <= top; bit++) {
            cnt += dfs(idx + 1, twoCnt + (bit == 2 ? 1 : 0),
                    isLimit && bit == top);
        }
        if (!isLimit) memo[idx][twoCnt] = cnt;
        return cnt;
    }
}
