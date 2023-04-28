#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q64.c

// 1. dp
int minPathSum_dp(int** grid, int m, int* gridColSize) {
    int n = gridColSize[0];
    int dp[m][n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0 && j == 0) dp[i][j] = grid[0][0];
            else if (i == 0 && j != 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
            else if (i != 0 && j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
            else dp[i][j] = fmin(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    return dp[m - 1][n - 1];
}

// 2. dfs
int** g_grid;
int minSum = INT_MAX;

int dfs(int m, int n, int i, int j, int* memo) {
    int idx = i * n + j; // 二维转一维
    if (memo[idx] != 0) return memo[idx];

    int curSum_Left = INT_MAX, curSum_Up = INT_MAX, curMinSum = INT_MAX; // 初值-非法值

    if (i == 0 && j == 0) {
        curMinSum = g_grid[i][j];
    }
    else if (i != 0 && j == 0) {
        curSum_Up = dfs(m, n, i - 1, j, memo);
        curMinSum = curSum_Up + g_grid[i][j];
    }
    else if (i == 0 && j != 0) {
        curSum_Left = dfs(m, n, i, j - 1, memo);
        curMinSum = curSum_Left + g_grid[i][j];
    }
    else {// if (i != 0 && j != 0) {
        curSum_Up = dfs(m, n, i - 1, j, memo);
        curSum_Left = dfs(m, n, i, j - 1, memo);
        curMinSum = fmin(curSum_Left, curSum_Up) + g_grid[i][j];
    }

    memo[idx] = curMinSum;
    if (i == m - 1 && j == n - 1) {
        // printf("curMinSum = %d\n", curMinSum);
        minSum = curMinSum;
    }
    return memo[idx];
}

int minPathSum(int** grid, int m, int* gridColSize) {
    g_grid = grid;
    int n = gridColSize[0];
    int memo[m * n + 1]; // 数组代替哈希
    memset(memo, 0, sizeof(memo));

    dfs(m, n, m - 1, n - 1, memo);
    return minSum;
}

int main() {
    int m = 3, n = 3;
    int grid[3][3] = {
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1} };
    int* gridColSize = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) gridColSize[i] = n;
    int** p = (int**)malloc(sizeof(int*) * m); // 二维数组grid的各行指针
    for (int i = 0; i < m; i++) p[i] = grid[i];

    int res = minPathSum(p, m, gridColSize);
    printf("res = %d\n", res);

    // system("pause");
    return 0;
}