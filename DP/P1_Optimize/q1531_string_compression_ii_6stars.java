package DP.P1_Optimize;

import java.util.Arrays;

public class q1531_string_compression_ii_6stars {

    // ��1��dp
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0; // ��ʼ�����մ���ɾ��0�ַ�����С����=0
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i; j++) { // j: ɾ������ <= ������i
                if (j - 1 >= 0) {
                    // 1��ѡ��ɾ����i���ַ�������̳���=s[0,i-1]&ɾj-1���ַ�
                    dp[i][j] = dp[i-1][j-1];
                }
                // 2��ѡ������i���ַ�����
                //     ��̳��� = min{ s[0,i0-1]&ɾdiff(<=j)���ַ� + s[i0,i]���ظ�s[i]��ѹ�������̳���calc}
                //      diff = s[i0,i]�в�����s[i]���ַ�����
                int same = 0, diff = 0; // ����s[i0,i]����=i-i0+1
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
        return 4; // ������ʾ��n < 100����෵��3
    }

    // д��2����ǰƥ��
    // https://leetcode.cn/problems/string-compression-ii/solution/javacan-kao-di-yi-de-dp-by-cdx3424/
    public int getLengthOfOptimalCompression2(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1]; // ������עɾ���ĵ�������Щ�ַ���
        for (int[] x : dp) {
            Arrays.fill(x, n + 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i && j <= k; j++) {
                if (j < k) {
                    // ��ʼ����һ��: dp[i][j + 1] >= min{ dp[i - 1][j] } ��
                    // s[0:i] & ɾ��j+1������̳��ȣ���ʼ��Ϊ(>=) ɾ����i���ַ��� s[0:i-1] & ɾ��j���ַ�����̳���
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - 1][j]);
                }
                int del = 0;
                for (int lf = i; lf > 0; lf--) {
                    if (s.charAt(lf - 1) != s.charAt(i - 1)) { // s[lf+1, i]������ͬ�ַ�
                        if (++del > j) {
                            break;
                        }
                    } else { // s[lf, i]������ͬ�ַ�
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
