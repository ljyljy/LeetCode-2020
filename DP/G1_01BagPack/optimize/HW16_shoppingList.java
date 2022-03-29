package DP.G1_01BagPack.optimize;

import java.util.Scanner;

public class HW16_shoppingList {
    // 01����
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // N���ܽ��/����Ǯ����m: ��Ʒ����
            int N = sc.nextInt(), m = sc.nextInt();
            N /= 10;// ���ڼ۸���10��������������һ���Խ��Ϳռ�/ʱ�临�Ӷ�
//             int[] v = new int[m], p = new int[m], q = new int[m];
            // ??? ���ݽṹ�Ķ��壡
            int[][] prices = new int[m+1][3]; // 3��<0:������1������1,2������2>?
            int[][] pv = new int[m+1][3]; // v*p, �۸�*��ֵ

            for (int i = 1; i <= m; i++) {
                // v�۸�p��ֵ��q����=0/����!=0,����������
                int v = sc.nextInt() / 10, p = sc.nextInt(), q = sc.nextInt();
                if (q == 0) { // ����
                    prices[i][0] = v;
                    pv[i][0] = v*p;
                } else {
                    if (prices[q][1] != 0) {
                        prices[q][1] = v;
                        pv[q][1] = v*p;
                    } else {
                        prices[q][2] = v;
                        pv[q][2] = v*p;
                    }
                }
            }

            // 01��������άdp��
            int[][] dp = new int[m+1][N+1]; //ǰi�����������jʱ�����p*v
            for (int i = 1; i <= m; i++) { // �⣺������Ʒ++
                for (int j = 1; j <= N; j++) {// �ڣ���������
                    int w0 = prices[i][0], pv0 = pv[i][0];
                    int w1 = prices[i][1], pv1 = pv[i][1];
                    int w2 = prices[i][2], pv2 = pv[i][2];
                    // �����Ƚ�
                    dp[i][j] = j-w0>=0? Math.max(dp[i-1][j], dp[i-1][j-w0]+pv0) : dp[i-1][j];// ����
                    dp[i][j] = j-w0-w1>=0? Math.max(dp[i][j], dp[i-1][j-w0-w1]+pv0+pv1) : dp[i][j];// ����+����1
                    dp[i][j] = j-w0-w2>=0? Math.max(dp[i][j], dp[i-1][j-w0-w2]+pv0+pv2) : dp[i][j]; // ?����+����2 ��������
                    dp[i][j] = j-w0-w1-w2>=0? Math.max(dp[i][j], dp[i-1][j-w0-w1-w2]+pv0+pv1+pv2) : dp[i][j];// ����+����1&2
                }
            }
            System.out.println(dp[m][N] * 10);
        }
    }
}
