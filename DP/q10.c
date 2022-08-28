/*
    二维数组初始化、
    strcpy、strcat坑！（\0）
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>


char *ss, *pp;

void FREE(void* p) {
    free(p);
    p = NULL;
}

bool matches (int i, int j);
bool isMatch(char * s, char * p){
    int nS = strlen(s), nP = strlen(p);
    ss = malloc(sizeof (char) * (nS + 2)); // 首' ', 尾'\0'
    pp = malloc(sizeof (char) * (nP + 2));
    ss[0] = ' '; pp[0] = ' ';
    // // 以下等价于 ss = " " + s;
    // // 法1：strcpy
    // char *ss_1 = &ss[1], *pp_1 = &pp[1];
    // strcpy(ss_1, s); strcpy(pp_1, p);
    // // 法2：strcat
    ss[1] = '\0'; strcat(ss, s);
    pp[1] = '\0'; strcat(pp, p);
    // printf("%s\n", ss); printf("%s\n", pp);

    int dp[nS+1][nP+1];
    memset(dp, 0, sizeof(dp));  // 勿忘初始化全0！
    // for (int i = 0; i <= nS; i++) { // 初始化-法2
    //     for (int j = 0; j <= nP; j++) {
    //         dp[i][j] = 0;
    //     }
    // }
    dp[0][0] = 1; // dp[>0][0]一定是false
    for (int i = 0; i <= nS; ++i) {
        for (int j = 1; j <= nP; ++j) {
            if (pp[j] != '*') {
                dp[i][j] = (i-1>=0 && dp[i-1][j-1]) && matches(i, j);
            } else {
                dp[i][j] = dp[i][j-2] || (i-1>=0 && dp[i-1][j]) && matches(i, j-1);
            }
        }
    }
    FREE(ss);
    FREE(pp);
    return dp[nS][nP];
}

bool matches (int i, int j) {
   return ss[i] == pp[j] || pp[j] == '.';
}