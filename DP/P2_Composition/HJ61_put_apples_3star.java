package DP.P2_Composition;

import java.util.*;

/* dp，方案数
其实这题考的是数学啊，首先当有0个苹果或者是1个盘子的时候，只有一种分法，而其他情况
可以分为两种情况讨论：
   1、苹果n < 盘子k，则至少有k-n个盘子是空的，此时就相当于将n个苹果分到n个盘子中，此时(n,k)=(n,n)
   2、苹果n > 盘子k,分法是两种情况分法的【和】，（A.不选）当前至少有一个空盘子 or （B.全选）没有空盘子，即(n,k) = A.(n,k-1) + B.(n-k,k)
        A. n个苹果全部装在k-1个盘子；
        B. 先一次性装满k个盘子，剩余n-k个苹果，继续递归/dp
*/
public class HJ61_put_apples_3star {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), k = sc.nextInt(); // n苹果，k盘子
//            System.out.println(dfs(n, k));
            System.out.println(dp(n, k));
        }
    }

    private static int dp(int n, int k) {
        int[][] dp = new int[n+1][k+1]; // 苹果i & 至多盘子j时的方案数
        // 0个苹果或者是1个盘子, 只有一种方案
        for (int i = 0; i <= n; i++) dp[i][1] = 1; // 1盘子,方案数=1；0盘子，方案数=0
        for (int j = 0; j <= k; j++) dp[0][j] = 1; // 0苹果，方案数=1

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                //          ↓不放满        ↓放满(前提：苹果i>=盘子j)
                dp[i][j] = dp[i][j-1] + (i-j>=0? dp[i-j][j] : 0);
            }
        }
        return dp[n][k];// 苹果n & 至多盘子k时的方案数
    }

    private static int dfs(int n, int k){
        if(n <= 1 || k==1) {
            return 1;
        } else if (n < k) { // n苹果数 < k盘子数，最多只能装n个盘子（会有k-n空盘子）
            return dfs(n, n); // 下探：n个苹果、至多n个盘子的方案数
        } else { // 下探：n苹k-1盘(不放满/至少1个空盘) & (全放满)
            return dfs(n,k - 1) + dfs(n - k, k);
        }
    }
}
