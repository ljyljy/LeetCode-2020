#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q62.c
/*
知识点

- dfs有返回值，int。出口：
  - 一条完整路径，返回1
  - 越界，返回0
- 二维下标转一维
  - ` idx = i * n_cols + j  `
*/
// 1. dp
int uniquePaths_DP(int m, int n) {
    int dp[m][n];
    memset(dp, 0, sizeof(dp));
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}

// 2. dfs-memo
int dfs(int m, int n, int i, int j, int* memo) {
    if (i >= m || j >= n) return 0; // 防止idx越界
    int idx = i * n + j;
    if (memo[idx] != 0) return memo[idx];
    if (i == m - 1 && j == n - 1) {
        return 1; // 一条完整路径
    }
    int cnt_right = dfs(m, n, i, j + 1, memo);
    int cnt_down = dfs(m, n, i + 1, j, memo);
    memo[idx] = cnt_right + cnt_down;
    return memo[idx];
}

int uniquePaths(int m, int n) {
    int size = m * n + 1;
    int memo[size]; // 数组代替哈希key="i_j" -> i*n_col+j
    memset(memo, 0, sizeof(memo));
    return dfs(m, n, 0, 0, memo);
}

int main() {
    int m = 3, n = 7;
    int ans = uniquePaths(m, n);
    printf("%d\n", ans);
    return 0;
}