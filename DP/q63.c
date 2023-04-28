#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q63.c

// 1. dp
int uniquePathsWithObstacles_dp(int** obstacleGrid, int m, int* obstacleGridColSize) {
    int n = obstacleGridColSize[0];
    int dp[m][n];
    memset(dp, 0, sizeof(dp));
    for (int i = 0; i < m; i++) {
        if (obstacleGrid[i][0] == 1) break;
        dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
        if (obstacleGrid[0][j] == 1) break;
        dp[0][j] = 1;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[i][j] == 1) continue;
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}

// 2. dfs-memo
int** g_obstacleGrid;
int dfs(int m, int n, int i, int j, int* memo) {
    if (i >= m || j >= n) return 0; // 防止idx越界
    int idx = i * n + j;
    if (memo[idx] != 0) return memo[idx]; // 或memo初始化为-1（双层for，不可memset）
    if (g_obstacleGrid[i][j] == 1) {
        memo[idx] = 0;
        return 0;
    }
    if (i == m - 1 && j == n - 1 && g_obstacleGrid[i][j] != 1) {
        return 1; // 一条完整路径
    }

    int cnt_right = dfs(m, n, i, j + 1, memo);
    int cnt_down = dfs(m, n, i + 1, j, memo);
    memo[idx] = cnt_right + cnt_down;
    return memo[idx];
}

int uniquePathsWithObstacles(int** obstacleGrid, int m, int* obstacleGridColSize) {
    g_obstacleGrid = obstacleGrid;
    int n = obstacleGridColSize[0];
    int memo[m * n + 1]; // 数组代替哈希key="i_j" -> i*n_col+j
    memset(memo, 0, sizeof(memo));
    return dfs(m, n, 0, 0, memo);
}

int main() {
    int m = 3, n = 7;
    int obstacleGrid[3][7] = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0}
    };
    int* obstacleGridColSize = (int*)malloc(sizeof(int) * m);
    for (int i = 0; i < m; i++) obstacleGridColSize[i] = n;
    int** obstacleGrid_ptr = (int**)malloc(sizeof(int*) * m);
    for (int i = 0; i < m; i++) obstacleGrid_ptr[i] = obstacleGrid[i];
    int ans = uniquePathsWithObstacles(obstacleGrid_ptr, m, obstacleGridColSize);
    printf("%d\n", ans);
    return 0;
}