#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q70.c 其他方法见Java版

// 法1-2：DP迭代-2
int climbStairs_dp(int n) {
    int dp[n + 1];
    memset(dp, 0, sizeof(dp));

    if (n >= 1) dp[1] = 1;
    if (n >= 2) dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}

// 法2：dfs回溯
int dfs(int n, int* memo) {
    if (n <= 0) return 0;
    if (memo[n] != 0) return memo[n];
    memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
    return memo[n];
}

int climbStairs_dfs(int n) {
    int memo[n + 1];
    memset(memo, 0, sizeof(memo));
    memo[0] = 1;
    if (n >= 1) memo[1] = 1;
    if (n >= 2) memo[2] = 2;
    if (n <= 2) return memo[n];
    return dfs(n - 1, memo) + dfs(n - 2, memo);
}


// 法3：完全背包+求方案数+排列(物品内循环)
// 物品：台阶数(1,2,..m), 题中m=2; 背包总重: n级台阶；求:装满背包有几种方案？
int climbStairs_dp2(int n) {
    int bagsize = n, m = 2;
    int dp[bagsize + 1];
    memset(dp, 0, sizeof(dp));
    dp[0] = 1;
    for (int j = 0; j <= bagsize; j++) {
        for (int i = 1; i <= m; i++) {
            if (j >= i)
                dp[j] += dp[j - i];
        }
    }
    return dp[bagsize];
}

int main() {
    int n = 3;
    printf("%d\n", climbStairs_dfs(n));
    printf("%d\n", climbStairs_dp(n));
    printf("%d\n", climbStairs_dp2(n));
    return 0;
}