package DP;

import java.util.Scanner;

public class q1143_HJ75_longest_common_substring {
    // �Ա�q1143�������������LCSeq(��Ҫ������) �� LCStr(���⣬Ҫ��������)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine(), p = sc.nextLine();
            System.out.println(getLCStr(s, p));
        }
    }

    private static int getLCStr(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        int[][] dp = new int[n1+1][n2+1]; // s[0,i-1]��p[0,j-1]��������Ӵ�(����LCStr)
        int maxLen = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s.charAt(i-1) == p.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0; // �߹����Ӵ����������������Ż�ʱ�����¼���
                    // vs-q1143: LCS������(������): dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen; // vs2: LCSeq: dp[n1][n2];
    }
}
