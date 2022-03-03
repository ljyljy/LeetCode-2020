package DP;

public class q97_interleaving_string {
    char[] ss1, ss2, ss3;
    public boolean isInterleave(String s1, String s2, String s3) {
        ss1 = s1.toCharArray(); ss2 = s2.toCharArray(); ss3 = s3.toCharArray();
        // if (s1 == null || s2 == null || s3 == null) return false;
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1 + n2 != n3) return false;
        // dp[i][j]: s1ǰi���ַ������ȣ�& s2.ǰj���ַ� �ܷ�ƥ��s3.ǰi+j���ַ���len��
        boolean[][] dp = new boolean[n1+1][n2+1];
        dp[0][0] = true;
        for (int i = 1; i <= n1; i++) // ���±���Ҫlen/i-1
            dp[i][0] = dp[i-1][0] &&  ss1[i-1] == ss3[i-1];
        for (int j = 1; j <= n2; j++)
            dp[0][j] = dp[0][j-1] &&  ss2[j-1] == ss3[j-1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // (1) ����s1ǰi�� && s2ǰj-1�� && ss2��j��==ss3��ǰĩβ(��i+j��, idx���1) (2) ͬ��
                dp[i][j] = (dp[i][j-1] && ss2[j-1] == ss3[i+j-1])
                        || (dp[i-1][j] && ss1[i-1] == ss3[i+j-1]);
            }
        }
        return dp[n1][n2];
    }
}
