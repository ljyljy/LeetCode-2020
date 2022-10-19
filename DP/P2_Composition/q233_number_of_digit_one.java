package DP.P2_Composition;

import java.util.Arrays;

public class q233_number_of_digit_one {
    char[] chsNum;
    int[][] memo;
    public int countDigitOne(int num) {
        chsNum = String.valueOf(num).toCharArray();
        int n = chsNum.length;
        // num��[0, 1e9], ���10λ����< 10��1��
        memo = new int[n][10]; // "idx_OneCnt"
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int idx, int oneCnt, boolean isLimit) {
        if (idx == chsNum.length) return oneCnt;
        if (!isLimit && memo[idx][oneCnt] != -1) {
            return memo[idx][oneCnt];
        }
        int cnt = 0;
        int top = isLimit? chsNum[idx] - '0' : 9;
        for (int bit = 0; bit <= top; bit++) {
            cnt += dfs(idx+1, oneCnt+(bit == 1? 1:0),
                    isLimit && bit == top);
        }
        // ����limit���򲻼�¼memo������
        if (!isLimit) memo[idx][oneCnt] = cnt; // ��limit���ż�¼��״̬�Ľ��
        return cnt;
    }
}
