package DP.P1_Optimize;

import java.util.HashMap;
import java.util.Map;

public class q486_predict_the_winner {
    // ���q486, 877
    // ��������1��DFS
    private int[] nums; // �� <"i_j", ���1-���2�ķ�����ֵ(>=0�������1ʤ)>
    private Map<String, Integer> memo = new HashMap<>();
    public boolean PredictTheWinner_dfs(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        return dfs(0, n-1) >= 0;
        // �� ��q877��֮ͬ����>=0���÷�ͬҲ�����1ʤ��������һ������
    }

    private int dfs(int i, int j) {
        if (i == j) return nums[i];
        String key = i + "_" + j;
        if (memo.containsKey(key)) return memo.get(key);

        int pickL = nums[i] - dfs(i+1, j);
        int pickR = nums[j] - dfs(i, j-1);
        int res = Math.max(pickL, pickR);
        memo.put(key, res);
        return res;
    }

    // ��2������
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = nums[i];

        for (int i = n-2; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i+1][j],
                                    nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] >= 0;
        // �� ��q877��֮ͬ����>=0���÷�ͬҲ�����1ʤ��������һ������
    }


}
