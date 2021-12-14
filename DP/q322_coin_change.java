package DP;

import java.util.Arrays;

public class q322_coin_change {
    // ��ȫ����(��ѭ������), ���(��ѭ��-��Ʒ)/����, �������ġ���ֵ������
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int bagsize = amount;
        int[] dp = new int[bagsize + 1];
        // dp��ʼ��
        Arrays.fill(dp, Integer.MAX_VALUE); // ��min
        dp[0] = 0; // �ܽ��=0��Ӳ�Ҹ�����Ϊ0
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // ��ֹ���?
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    // ������--��ֵdp[j];   ����--Ӳ����+1
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[bagsize] == Integer.MAX_VALUE? -1: dp[bagsize];
    }
}
