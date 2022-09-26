#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>


#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

double* dicesProbability(int n, int* returnSize) {
    double dp[n + 1][6 * n + 1];
    double p = 1.0 / 6;
    // 初始化
    // for (int i = 0; i <= n; i++) {
    //     for (int j = 0; j <= 6 * n; j++) {
    //         dp[i][j] = 0;
    //     }
    // } // 或memset
    memset(dp, 0, sizeof(dp));
    for (int j = 1; j <= 6; j++) {
        dp[1][j] = p;
    }

    for (int i = 2; i <= n; i++) { // 投掷次数
        for (int j = i; j <= i * 6; j++) {  // 累计点数和
            for (int cur = 1; cur <= 6; cur++) {  // 当前骰子点数
                if (j - cur >= 0) {
                    dp[i][j] += dp[i - 1][j - cur] * p;
                }
            }
        }
    }

    double* res = (double*)calloc(6 * n, sizeof(double));
    int idx = 0;
    for (int j = 1; j <= 6 * n; j++) {
        if (dp[n][j] != 0) {
            res[idx++] = dp[n][j];
        }
    }
    *returnSize = idx;
    return res;
}

int main() {
    int n = 3;
    int returnSize = 0;
    double* res = dicesProbability(n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%lf ", res[i]);
    }
}