package DP.P1_Optimize;

import java.util.Arrays;

public class q1531_string_compression_ii_6stars {

    // 法1：dp
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0; // 初始化：空串，删除0字符的最小长度=0
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i; j++) { // j: 删除个数 <= 串长度i
                if (j - 1 >= 0) {
                    // 1）选择删除第i个字符，则最短长度=s[0,i-1]&删j-1个字符
                    dp[i][j] = dp[i-1][j-1];
                }
                // 2）选择保留第i个字符，则：
                //     最短长度 = min{ s[0,i0-1]&删diff(<=j)个字符 + s[i0,i]中重复s[i]串压缩后的最短长度calc}
                //      diff = s[i0,i]中不等于s[i]的字符个数
                int same = 0, diff = 0; // 归类s[i0,i]，∑=i-i0+1
                for (int i0 = i; i0 >= 1 && diff <= j; i0--) {
                    if (s.charAt(i0-1) == s.charAt(i-1)) {
                        same++;
                        dp[i][j] = Math.min(dp[i][j],
                                        dp[i0-1][j-diff] + calc(same));
                    } else diff++;
                }

            }
        }
        return dp[n][k];
    }

    private int calc(int same) {
        if (same == 1) return 1;
        if (same < 10) return 2; // 'a9'
        if (same < 100) return 3; // 'a99'
        return 4; // 根据提示：n < 100，最多返回3
    }

    // 写法2：向前匹配
    // https://leetcode.cn/problems/string-compression-ii/solution/javacan-kao-di-yi-de-dp-by-cdx3424/
    public int getLengthOfOptimalCompression2(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1]; // 并不关注删除的到底是哪些字符！
        for (int[] x : dp) {
            Arrays.fill(x, n + 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i && j <= k; j++) {
                if (j < k) {
                    // 初始化下一层: dp[i][j + 1] >= min{ dp[i - 1][j] } ↓
                    // s[0:i] & 删除j+1个的最短长度，初始化为(>=) 删除第i个字符后， s[0:i-1] & 删除j个字符的最短长度
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - 1][j]);
                }
                int del = 0;
                for (int lf = i; lf > 0; lf--) {
                    if (s.charAt(lf - 1) != s.charAt(i - 1)) { // s[lf+1, i]连续相同字符
                        if (++del > j) {
                            break;
                        }
                    } else { // s[lf, i]连续相同字符
                        dp[i][j] = Math.min(dp[i][j],
                                dp[lf - 1][j - del] + len(i - lf + 1 - del) + 1);
                    }
                }
            }
        }
        return dp[n][k];
    }

    public int len(int k) {
        if (k <= 1) return 0;
        else if (k > 1 && k < 10) return 1;
        else if (k >= 10 && k < 100) return 2;
        else return 3;
    }
}
