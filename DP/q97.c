#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q97. 交错字符串

bool isInterleave(char* s1, char* s2, char* s3) {
    int n1 = strlen(s1), n2 = strlen(s2), n3 = strlen(s3);
    if (n1 + n2 != n3) return false;
    // dp[i][j]: s1前i个字符（长度）& s2.前j个字符 能否匹配s3.前i+j个字符（len）
    bool dp[n1 + 1][n2 + 1];  // +1: 避免""空串时，dp[0][0]越界
    dp[0][0] = true; // 0处哨兵：空串匹配空串
    for (int i = 1; i <= n1; i++)
        dp[i][0] = dp[i - 1][0] && s1[i - 1] == s3[i - 1];
    for (int j = 1; j <= n2; j++)
        dp[0][j] = dp[0][j - 1] && s2[j - 1] == s3[j - 1];

    for (int i = 1; i <= n1; i++) {
        for (int j = 1; j <= n2; j++) {
            dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) ||
                (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1]);
        }
    }
    return dp[n1][n2];
}

int main()
{
    char* s1 = "aabcc", * s2 = "dbbca", * s3 = "aadbbcbcac";
    printf("%d\n", isInterleave(s1, s2, s3)); // true
    return 0;
}