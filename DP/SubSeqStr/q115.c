#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

int numDistinct(char* str, char* pat) {
    int n1 = strlen(str), n2 = strlen(pat);
    if (n1 < n2) return 0; // 坑：避免超长无效串
    unsigned long long dp[n1 + 1][n2 + 1]; // 坑：注意类型！
    memset(dp, 0, sizeof(long long) * (n1 + n2 + 2));
    for (int i = 0; i <= n1; i++) {
        dp[i][0] = 1;
    }

    // unsigned long long cnt = 0;
    for (int i = 1; i <= n1; i++) {
        for (int j = 1; j <= n2; j++) {
            if (str[i - 1] == pat[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
            else {
                dp[i][j] = dp[i - 1][j];
            }
            // cnt = fmax(cnt, dp[i][j]);
        }
    }
    return dp[n1][n2]; // cnt;
}
