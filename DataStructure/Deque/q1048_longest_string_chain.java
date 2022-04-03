package DataStructure.Deque;

import java.util.Arrays;

public class q1048_longest_string_chain {
    // 类比q72编辑距离、最长公共子序列、最长递增子序列
    public int longestStrChain(String[] words) {
        // 按str长度升序（与字典序.compareTo无关，可不定义）
        Arrays.sort(words, (o1, o2)->(o1.length() - o2.length()));
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // 子序列dp：两层for
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
        // 法1：双指针，相似字符个数==n1
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (prev.charAt(i) == nxt.charAt(j)) i++;
            j++;
        }
        return i == n1;

        // // 法2：dp-q392判断子序列：dp[n1][n2]==n1
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

