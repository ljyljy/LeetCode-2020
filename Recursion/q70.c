#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q70.c ����������Java��

// ��1-2��DP����-2
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

// ��2��dfs����
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


// ��3����ȫ����+�󷽰���+����(��Ʒ��ѭ��)
// ��Ʒ��̨����(1,2,..m), ����m=2; ��������: n��̨�ף���:װ�������м��ַ�����
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