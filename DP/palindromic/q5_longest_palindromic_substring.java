package DP.palindromic;

public class q5_longest_palindromic_substring {
    private char[] chars;
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        chars = s.toCharArray();
        int len = 0, start = 0, end = 0;
        boolean[][] dp = new boolean[n][n]; // s[i,j]是否回文(左闭右闭)[j>=i]

        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (chars[i] == chars[j] && (j-i<=1 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    int curLen = j - i + 1;
                    if (curLen > len) {
                        len = curLen;
                        start = i; end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }


    public String longestPalindrome_dp2(String s) {
        if (s == null || s.length() < 2) return s;
        chars = s.toCharArray();
        int n = s.length();
        int start = 0, end = 0, maxLen = 0;

        boolean[][] dp = new boolean[n+1][n+1];
        for (int i = 0; i < n; i++) dp[i][i] = true;


        for (int r = 1; r < n; r++) {
            for (int l = r-1; l >= 0; l--) {
                // ∴ 遍历：r正序，l逆序(l < r) -> r外,l内for ↓
                if (chars[r] == chars[l] && (r - l <= 2 || dp[l+1][r-1])){
                    dp[l][r] = true;
                    int curLen = r - l + 1;
                    if (curLen > maxLen) {
                        start = l; end = r; maxLen = curLen;
                    }
                }
            }
        }
        // System.out.println(start + ", " + end + ", " + maxLen);
        return s.substring(start, end+1);
    }

    private boolean ispalindromic(int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++; j++;
        }
        return true;
    }
}
