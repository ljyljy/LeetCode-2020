package DP;

public class q115_distinct_subsequences {
    public int numDistinct(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[][] dp = new int[n1+1][n2+1]; // �ַ���ƥ��s[i-1] t[j-1]�ķ�����
        // ��ʼ����1) dp[0,1:]=0(ģʽ���ǿգ�������ƥ��s="", ������=0)
        dp[0][0] = 1; // 2) ��&��ƥ��1�ַ���
        // ��©? 3) dp[1:, 0]=1(ģʽ��Ϊ�գ�����s��ƥ��մ�t, ������=1)
        for (int i = 0; i <= n1; i++) dp[i][0] = 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    // 1) [i-1][j-1] & ��ǰ[i][j]��ͬ���һ�ַ���
                    // 2) �����ǵ�ǰ[i][j]��ϣ��γ���һ�ַ�����[i-1][j] <- ģʽ��j����
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]; // ?���q115, 1143, 392
                } else { // �޷����ǵ�ǰ[i][j]&[i-1][j-1]����ֻ��һ�ַ���:[i-1][j]
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n1][n2];
    }
}
