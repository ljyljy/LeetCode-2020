package DP;

import java.util.Scanner;

public class q1143_HJ65_LCStr2 {
    //    ���������ַ���a,b�е�������Ӵ�
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            // ?Ϊ�˱�֤��ӡ���ǽ϶̴���LCStr���̶�s1�ĳ���С��s2�ĳ���
            if (s1.length() > s2.length()) {
                String tmp = s1;
                s1 = s2;
                s2 = tmp;
            }
            getLCStr2(s1, s2);
        }
    }

    private static void getLCStr2(String s1, String s2) {
        char[] ss1 = s1.toCharArray(), ss2 =s2.toCharArray();
        int n1 = ss1.length, n2 = ss2.length;
        int[][] dp = new int[n1+1][n2+1]; // s[: i-1], s2[:j-1]��������Ӵ�
        int maxLen = 0;
        String lcs = "";

        for (int i = 1; i <= n1; i++) { // �̴�����ѭ����
            for (int j = 1; j <= n2; j++) {
                if (ss1[i-1] == ss2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (maxLen < dp[i][j]) {
                        maxLen = dp[i][j];
//                        lcs = s2.substring(j-maxLen, j); // ��1��s2[j-maxLen: j-1]
                        lcs = s1.substring(i-maxLen, i); // ��2��s1[i-maxLen: i-1]
                    }
                }
            }
        }
        System.out.println(lcs);
    }
}
