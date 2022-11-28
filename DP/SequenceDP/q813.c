#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

double largestSumOfAverages(int* nums, int n, int k) {
    double sum[n + 1];
    sum[0] = 0.0;
    for (int i = 1; i <= n; i++) {
        sum[i] = sum[i - 1] + nums[i - 1];
    }
    double dp[n + 1][k + 1];
    memset(dp, 0, sizeof(int) * n * k);
    for (int i = 1; i <= n; i++) {
        dp[i][1] = sum[i] / i;
    }
    for (int i = 2; i <= n; i++) {
        for (int j = 2; j <= k; j++) {
            dp[i][j] = 0.0; // ��C�ӡ�C���У�������ʼ����
            for (int x = j - 1; x < i; x++) {
                dp[i][j] = fmax(dp[i][j],
                    dp[x][j - 1] + (sum[i] - sum[x]) / (i - x));
                // �� ����=[j-1]+[1]������[0,x-1], j-1�� & [x,i-1], 1��
                // [i,j].preSum = sum[j+1]-sum[i]
            }

        }
    }
    return dp[n][k];
}

#define DIM(x) sizeof(x) / sizeof(*x)

int main() {
    int nums[] = { 9,1,2,3,9 };
    int n = DIM(nums), k = 3;
    double res = largestSumOfAverages(nums, n, k);
    printf("%lf\n", res);
}