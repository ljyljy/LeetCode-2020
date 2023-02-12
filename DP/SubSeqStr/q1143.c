int longestCommonSubsequence(char * s1, char * s2){
    int n1 = strlen(s1), n2 = strlen(s2);
    int dp[n1+1][n2+1];
    memset(dp, 0, sizeof(dp));

    for (int i = 1; i <= n1; i++) {
        for (int j = 1; j <= n2; j++) {
            if (s1[i-1] == s2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = fmax(dp[i][j-1], dp[i-1][j]);
            }
        }
    }
    return dp[n1][n2];
}