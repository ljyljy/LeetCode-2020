package DP;

public class q583_delete_operation_for_two_strings {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int[][] dp = new int[n1+1][n2+1];
        // ��ʼ����dp[0, j] = j(�մ�vs����j�ķǿմ�, ɾ������=j��); dp[i, 0]ͬ��
        for (int i = 0; i <= n1; i++) dp[i][0] = i;
        for (int j = 0; j <= n2; j++) dp[0][j] = j;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]; // ����=ǰһ��[i-1][j-1]����(�����������)
                } else { // ����=min{ɾ2�� [i-1]&[j-1], ɾ1�� [i-1], ɾ1�� [j-1]}
                    dp[i][j] = Math.min(dp[i-1][j-1] + 2,
                            Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1));
                }
            }
        }
        return dp[n1][n2];
    }
}
