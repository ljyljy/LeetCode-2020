package DP.palindromic;

public class q516_longest_palindromic_subsequence {
    // д��2��dp[i,j]--s[i,j]
    public int longestPalindromeSubseq0(String s) {
        int n = s.length();
        if (n <= 1) return n;
        char[] ch = s.toCharArray();
        int[][] dp = new int[n][n]; // s[i,j]������������г���(j>i)
        for (int i = 0; i < n; i++) dp[i][i] = 1; // ���ַ�Ϊ����(i==j)

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {// j>i(����[j-1]<0Խ��)
                if (ch[i] == ch[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else { // �޷����ɻ��ģ�����s[i+1,j]��s[i,j-1]
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    // д��1��dp[i,j]--s[i-1, j-1]
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n <= 1) return n;
        char[] ch = s.toCharArray();
        int[][] dp = new int[n+1][n+1]; // s[i-1,j-1]������������г���(j>i)
        for (int i = 1; i <= n; i++) dp[i][i] = 1; // ���ַ�Ϊ����(i==j)

        for (int i = n; i >= 1; i--) {
            for (int j = i+1; j <= n; j++) {// j>i(����[j-1]<0Խ��)
                if (ch[i-1] == ch[j-1]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else { // �޷����ɻ��ģ�����s[i+1,j]��s[i,j-1]
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[1][n];
    }

}
