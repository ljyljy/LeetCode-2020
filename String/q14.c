#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

// 法2：推荐
char* longestCommonPrefix(char** strs, int n)
{
    char* pattern = strs[0];
    int n0 = strlen(pattern);
    char* res = (char*)malloc(sizeof(char) * (n0 + 1));
    memset(res, 0, sizeof(char) * (n0 + 1));
    for (int i = 0; i < n0; i++) {
        char ch = pattern[i];
        for (int j = 1; j < n; j++) {
            char* curStr = strs[j];
            int n1 = strlen(curStr);
            if (i >= n1 || ch != curStr[i]) {
                memcpy(res, pattern, sizeof(char) * (i));
                res[i] = 0;
                return res;
            }
        }
    }
    return pattern;
}

// 法1：while + 计算最长公共前缀串
char* longestCommonPrefixv0(char** strs, int n)
{
    char* pattern = strs[0];
    int n0 = strlen(pattern);
    char* res = (char*)malloc(sizeof(char) * (n0 + 1));
    memset(res, 0, sizeof(char) * (n0 + 1));
    // printf("pattern=%s\n", pattern);
    for (int j = 1; j < n; j++)
    {
        char* curStr = strs[j];
        // printf("curStr=%s\n", curStr);
        int n1 = strlen(curStr);
        int ii = 0, jj = 0;
        n0 = fmin(n0, n1); // 坑：最长[公共]串！
        // printf("n0=%d\n", n0);
        while (ii < n0 && jj < n0) {
            if (pattern[ii] == curStr[jj]) {
                ii++; jj++;
            }
            else {
                n0 = ii;// 坑：取最长[公共]串！
            }
        }
    }
    memset(res, 0, sizeof(char) * (n0 + 1));
    memcpy(res, pattern, sizeof(char) * n0);
    res[n0] = 0;
    return res;
}

int main() {
    char* strs[] = { "flower","flow","flight" };
    // printf("cnt=%d\n", DIM(strs));
    printf("res = %s\n", longestCommonPrefix(strs, DIM(strs)));

    char* strs2[] = { "dog","doracecar","dcar" };
    printf("res = %s\n", longestCommonPrefix(strs2, DIM(strs2)));
}