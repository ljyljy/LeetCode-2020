#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#define DIM(x) sizeof(x) / sizeof(*x)

// 法2：dp, 顺推
int minFallingPathSum_DP(int** matrix, int matrixSize, int* matrixColSize) {
    int n = matrixSize;
    int dp[n + 1][n + 2];// base case: 加行哨兵-top、列哨兵-左右边界[0;n+1]
    for (int i = 0; i < n + 1; i++) {
        for (int j = 0; j < n + 2; j++) {
            dp[i][j] = 0; // 非base case，都赋值为0
            dp[i][0] = INT_MAX;// 左右两列-边界
            dp[i][n + 1] = INT_MAX;
        }
    }
    for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j <= n; j++) { // 保证col下标∈[j-1:j+1]合法∈[1, n+1]
            dp[i][j] = matrix[i - 1][j - 1] +
                fmin(dp[i - 1][j - 1], fmin(dp[i - 1][j], dp[i - 1][j + 1]));
        }

    }
    int minSum = INT_MAX;
    for (int j = 1; j <= n; j++) {
        minSum = fmin(minSum, dp[n][j]);
    }
    return minSum;
}

// 法1：dfs + memo
const int INIT = -66666;
int dfs(int** matrix, int n, int row, int col, int(*memo)[n]);
int minFallingPathSum(int** matrix, int matrixSize, int* matrixColSize) {
    int n = matrixSize;
    int minSum = INT_MAX;
    int memo[n][n];
    // memset(memo, -66666, sizeof(int) * n * n); // memset按字节赋值，无法正确赋值非"全0/-1"的值
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            memo[i][j] = INIT;
            // printf("matrix[i][j] = %d\n", matrix[i][j]);
        }
    }

    for (int j = 0; j < n; j++) {
        minSum = fmin(minSum, dfs(matrix, n, 0, j, memo));
    }
    return minSum;
}

int dfs(int** matrix, int n, int row, int col, int(*memo)[n]) {
    if (row == n) return 0;
    if (memo[row][col] != INIT) return memo[row][col];

    memo[row][col] = matrix[row][col];
    int minNxt = INT_MAX;

    if (col - 1 >= 0) {
        minNxt = fmin(minNxt, dfs(matrix, n, row + 1, col - 1, memo));
    }
    minNxt = fmin(minNxt, dfs(matrix, n, row + 1, col, memo));
    if (col + 1 < n) {
        minNxt = fmin(minNxt, dfs(matrix, n, row + 1, col + 1, memo));
    }
    memo[row][col] += minNxt;
    return memo[row][col];
}

int main() {
    // int matrix[3][3] = { {2,1,3},{6,5,4},{7,8,9} }; // 不能强转为二级指针，只能对应(int*)[3]
    // int m = DIM(matrix);
    // int* colSize = (int*)malloc(sizeof(int) * m);
    // for (int j = 0; j < m; j++) {
    //     colSize[j] = m;
    // }
    int m = 3;
    int** matrix = (int**)malloc(sizeof(int*) * m);
    int fill[3][3] = { {2,1,3},{6,5,4},{7,8,9} };
    for (int i = 0; i < 3; i++) {
        matrix[i] = (int*)malloc(sizeof(int) * m);
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            matrix[i][j] = fill[i][j];
        }
    }

    int ans = minFallingPathSum((int**)matrix, m, &m);
    printf("ans = %d\n", ans);
}