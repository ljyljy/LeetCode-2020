package DP;

public class q647_palindromic_substrings {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // s[i,j]是否为回文(左闭右闭, j>=i)
        // 初始化全false
        char[] ch = s.toCharArray();
        int cnt = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (ch[i] == ch[j] && (j-i <= 1 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
