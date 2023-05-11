#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
// #include <limits.h>

char** res;
int* path;
int basicSize, curCnt, curLen;

void dfs(char* s, int idx, int validCnt, int* path);
int isValid(char* s, int start, int end);

char** restoreIpAddresses(char* s, int* returnSize) {
    basicSize = 8, curCnt = 0, curLen = 0;
    res = (char**)calloc(basicSize, sizeof(char*));
    path = (int*)calloc(4, sizeof(int));
    dfs(s, 0, 0, path);
    *returnSize = curCnt;
    return res;
}

void dfs(char* s, int idx, int validCnt, int* path) {
    int n = strlen(s);
    if (idx == n) {
        if (validCnt == 4) {
            res[curCnt] = (char*)calloc(4, sizeof(int) + sizeof(char)); // 4组"255."，最后无'.' 有'\0'
            sprintf(res[curCnt], "%d.%d.%d.%d", path[0], path[1], path[2], path[3]);
            if (++curCnt == basicSize) {
                basicSize *= 2;
                res = (char**)realloc(res, basicSize * sizeof(char*));
            }
            // curLen = 0;
        }
        return;
    }
    int remainLen = n - idx, remainCnt = 4 - validCnt;
    if (remainLen < remainCnt || remainLen > remainCnt * 3) return;

    //  path中的num最大值255，长度为3 ↓
    for (int i = idx; i < fmin(n, idx + 3); i++) {
        int curNum = 0;
        if ((curNum = isValid(s, idx, i)) == -1) continue;
        path[curLen++] = curNum;
        dfs(s, i + 1, validCnt + 1, path);
        --curLen;
    }
}

int isValid(char* s, int start, int end) {
    int len = end - start + 1;
    if (s[start] == '0' && len != 1) return -1; // 前导0，非法
    int num = 0;
    for (int i = start; i <= end; i++) {
        num = num * 10 + s[i] - '0';
        if (num > 255) return -1;
    }
    return num;
}

int main()
{
    char* s = "101023"; // "25525511135"
    int returnSize = 0;
    char** res = restoreIpAddresses(s, &returnSize);
    printf("returnSize = %d\n", returnSize);

    for (int i = 0; i < returnSize; i++) {
        printf("%s\n", res[i]);
    }
    return 0;
}