package DP.P2_Composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class qo_60_dicesProbability {
    // ��ά���飬n ������������ n �У���ÿһ�д����� n ������ʱ����ֵ�ĳ��ִ����������2�����ӣ���ֵ��ȡֵ��Χ�� 2 ~ 12(2 * 6)�����ִ���ȡ������һ���ֵ(1 ~ 6)
    // n + 1 ��ԭ���ǣ��� 0 ���˷�����Ϊ�˱�����⣬��һ�����Ӷ�Ӧ��һ�У������ǵ� 0 ��
    // ÿһ��ֵ��Χ�� n * 6 + 1 ��ԭ�������һ�������� 2 �����ӣ�ȡֵ�� 2 ��ʼ���� 12 ����������Ҫ�࿪һ�У����� 12 ����±�
    // ��2: ������DP
//    f[i][j]��ʾͶ��i�Σ���Ϊj�ĸ���
//    f[i][j] = f[i-1][j-k] / 6, j>=k
    public double[] dicesProbability(int n) {
        double[][] dp = new double[n+1][n*6+1]; // Ͷ�� i �Σ������� j �ĳ��ִ�����
        double p = 1.0 / 6;  // ÿ��Ͷ����ÿ���㣨1~6���ĸ������
        // ��ʼ����Ͷ��һ�����ӣ��õ�1��6ÿ����ĸ��ʶ�������֮һ
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = p;
        }
        for (int i = 2; i <= n; i++) { // Ͷ������
            for (int j = i; j <= i * 6; j++) {  // �ۼƵ�����
                for (int cur = 1; cur <= 6; cur++) {  // ��ǰ���ӵ���
                    if (j - cur >= 0) {
                        dp[i][j] += dp[i-1][j - cur] * p;
                    }
                }
            }
        }

        double[] res = new double[n*6+1]; // Ͷ�� n �Σ������͡�[n,6*n]
        int idx = 0;
        for (int j = 0; j <= n * 6; j++) {
            if (dp[n][j] != 0) {
                res[idx++] = dp[n][j];
            }
        }
        return Arrays.copyOf(res, idx);
    }


    // ��1: ����� / allCnts=6^n
    public double[] dicesProbability2(int n) {
        int[][] dp = new int[n+1][6*n+1]; // Ͷ���� i �����Ӻ󣬵����� j �ĳ��ִ�����
        // ��ʼ��
        for (int i = 1; i <= 6; i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) { // Ͷ������
            for (int j = i; j <= 6*i; j++) { // �ۼƵ�����
                for (int cur = 1; cur <= 6; cur++) { // ��ǰ���ӵ���
                    if (j - cur >= 0) {
                        dp[i][j] += dp[i-1][j-cur];
                    }
                }

            }
        }

        // 1���� - 1~6 -> 6��
        // 2���� - [1,1],[1,2] ...[6,6] -> 6^2��, ����=2~12 --> 11��=5n+1
        double all = Math.pow(6, n); // �����ۼƵ����͵ġ�����������
        // double[] res = new double[5*n+2]; // [1]*n, ...[6]*n --> ��6n-n+1=5n+1��
        List<Double> res = new ArrayList<>();
        for (int j = 1; j <= 6*n; j++) {
            // dp[n][j]��Ͷ��n�����ӣ�����Ϊj�Ĵ���
            if (dp[n][j] == 0) continue;
            res.add(dp[n][j] / all);
        }

        double[] res_ = new double[res.size()];
        int k = 0;
        for (double ans: res) {
            res_[k++] = ans;
        }
        return res_;
    }
}
