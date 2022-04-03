package DataStructure.Deque;

import java.util.Arrays;

public class q392_is_subsequence {
    // Ӧ�ã�q1048��ַ�����
    // ��2��˫ָ��-ֱ�Ӱ�s����˳��ȥ����t�����ɡ�
    public boolean isSubsequence(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == n1;
    }

    // ��1��dp
    public boolean isSubsequence_dp(String pat, String str) {
        if (pat == null || pat.length() == 0) return true;
        int n1 = pat.length(), n2 = str.length();
        char[] ch1 = pat.toCharArray(), ch2 = str.toCharArray();
        int[][] dp = new int[n1+1][n2+1];
//        dp[0][0] = 0;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else { // ?���q115, 1143, 392
                    dp[i][j] = dp[i][j-1];// ģʽ���±�i����
                }
            }
        }
        return dp[n1][n2] == n1;// Ӧ�ã�q1048��ַ�����
    }
}
