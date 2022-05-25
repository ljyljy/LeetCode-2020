package DP;

import java.util.*;

///关键是找到递推式 f(n)=f(n-1)+f(n-2) (n>=4)
///递推式的解释:对于第n个月的兔子数量：有两部分组成，
///一部分是上个月的兔子f(n-1)，另一部是满足3个月大的兔子
///会生一只兔子f(n-2)
public class q509_HJ37_fib {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            // 法1：DFS
//             int[] memo = new int[n+1]; // 使用idx[1, n]
//             Arrays.fill(memo, -1);
//             System.out.println(calcCnt(n, memo));
            // 法2：dp
            System.out.println(calcCnt_dp(n));
        }
    }
    // 法2：dp迭代
    private static int calcCnt_dp(int n){
        int[] dp = new int[n+1];
        if (n >= 1) dp[1] = 1; //
        if (n >= 2) dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 法1：DFS+MEMO
    private static int calcCnt(int n, int[] memo){
        if (n < 0 || n > memo.length-1) return 0;
        if (memo[n] != -1) return memo[n];
        if (n == 1 || n == 2) {
            memo[n] = 1;
            return 1;
        }
        memo[n] = calcCnt(n-1, memo) + calcCnt(n-2, memo);
        return memo[n];
    }

}

