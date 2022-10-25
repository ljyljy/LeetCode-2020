package DP;


public class q522_longest_uncommon_subsequence_ii {

    public int findLUSlength(String[] strs) {
        int n = strs.length, maxLen = -1;

        for (int i = 0; i < n; i++) { // i: pattern��(��src)
            if (strs[i].length() <= maxLen) continue;
            boolean ok = true;
            for (int j = 0; j < n && ok; j++) { // j: srcStr
                if (i == j) continue;
                if (checkLCS_v2(strs[j], strs[i])) {
                    ok = false;
                }
            }
            // ˵��strs[i]���������ַ�����������
            if (ok) maxLen = Math.max(maxLen, strs[i].length());
        }
        return maxLen;
    }

    // �ж�pattern�Ƿ���str��������
    // v2: ˫ָ�� - O(n)
    private boolean checkLCS_v2(String str, String pattern) {
        int n1 = str.length(), n2 = pattern.length();
        if (n2 > n1) return false;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (pattern.charAt(j) == str.charAt(i)) j++;
            i++;
        }
        return j == n2;
    }

    // v1: dp - O(n^2)
    private boolean checkLCS(String str, String pattern) {
        int n1 = str.length(), n2 = pattern.length();
        if (n2 > n1) return false;
        int[][] dp = new int[n1+1][n2+1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str.charAt(i-1) == pattern.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else { // ���q1143��LCS(�����У���Ҫ������)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n1][n2] == n2;
    }
}
