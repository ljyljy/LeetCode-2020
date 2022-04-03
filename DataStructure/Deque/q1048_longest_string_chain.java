package DataStructure.Deque;

import java.util.Arrays;

public class q1048_longest_string_chain {
    // ���q72�༭���롢����������С������������
    public int longestStrChain(String[] words) {
        // ��str�����������ֵ���.compareTo�޹أ��ɲ����壩
        Arrays.sort(words, (o1, o2)->(o1.length() - o2.length()));
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // ������dp������for
                if (isPrev(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private boolean isPrev(String prev, String nxt) {
        int n1 = prev.length(), n2 = nxt.length();
        if (n1 + 1 != n2) return false;
        // ��1��˫ָ�룬�����ַ�����==n1
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (prev.charAt(i) == nxt.charAt(j)) i++;
            j++;
        }
        return i == n1;

        // // ��2��dp-q392�ж������У�dp[n1][n2]==n1
        // int[][] dp = new int[n1+1][n2+1];
        // dp[0][0] = 0;

        // for (int i = 1; i <= n1; i++) {
        //     for (int j = 1; j <= n2; j++) {
        //         if (prev.charAt(i-1) == nxt.charAt(j-1)) {
        //             dp[i][j] = dp[i-1][j-1] + 1;
        //         } else {
        //             dp[i][j] = dp[i][j-1];
        //         }
        //     }
        // }
        // return dp[n1][n2] == n1;
    }
}

