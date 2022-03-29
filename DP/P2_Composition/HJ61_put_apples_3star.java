package DP.P2_Composition;

import java.util.*;

/* dp��������
��ʵ���⿼������ѧ�������ȵ���0��ƻ��������1�����ӵ�ʱ��ֻ��һ�ַַ������������
���Է�Ϊ����������ۣ�
   1��ƻ��n < ����k����������k-n�������ǿյģ���ʱ���൱�ڽ�n��ƻ���ֵ�n�������У���ʱ(n,k)=(n,n)
   2��ƻ��n > ����k,�ַ�����������ַ��ġ��͡�����A.��ѡ����ǰ������һ�������� or ��B.ȫѡ��û�п����ӣ���(n,k) = A.(n,k-1) + B.(n-k,k)
        A. n��ƻ��ȫ��װ��k-1�����ӣ�
        B. ��һ����װ��k�����ӣ�ʣ��n-k��ƻ���������ݹ�/dp
*/
public class HJ61_put_apples_3star {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), k = sc.nextInt(); // nƻ����k����
//            System.out.println(dfs(n, k));
            System.out.println(dp(n, k));
        }
    }

    private static int dp(int n, int k) {
        int[][] dp = new int[n+1][k+1]; // ƻ��i & ��������jʱ�ķ�����
        // 0��ƻ��������1������, ֻ��һ�ַ���
        for (int i = 0; i <= n; i++) dp[i][1] = 1; // 1����,������=1��0���ӣ�������=0
        for (int j = 0; j <= k; j++) dp[0][j] = 1; // 0ƻ����������=1

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                //          ��������        ������(ǰ�᣺ƻ��i>=����j)
                dp[i][j] = dp[i][j-1] + (i-j>=0? dp[i-j][j] : 0);
            }
        }
        return dp[n][k];// ƻ��n & ��������kʱ�ķ�����
    }

    private static int dfs(int n, int k){
        if(n <= 1 || k==1) {
            return 1;
        } else if (n < k) { // nƻ���� < k�����������ֻ��װn�����ӣ�����k-n�����ӣ�
            return dfs(n, n); // ��̽��n��ƻ��������n�����ӵķ�����
        } else { // ��̽��nƻk-1��(������/����1������) & (ȫ����)
            return dfs(n,k - 1) + dfs(n - k, k);
        }
    }
}
