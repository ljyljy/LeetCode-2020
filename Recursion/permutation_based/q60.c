#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
// #include <limits.h>

// q60.c

char** res;
int basicSize, curCnt, curLen;
// int* pathLens;

void dfs(int n, int idx, char* path, bool* used, int k) {
    if (idx > n || curCnt >= k) return; // ºÙ÷¶£¨∑Ò‘ÚTLE
    if (curLen == n) {
        res[curCnt] = (char*)calloc(n + 1, sizeof(char)); // ŒÕ¸'\0'
        memcpy(res[curCnt++], path, curLen + 1); // todo: vs strcpy
        if (curCnt == basicSize) {
            basicSize *= 2;
            res = (char**)realloc(res, basicSize * sizeof(char*));
        }
        return;
    }
    for (int i = 0; i < n; i++) {
        if (used[i]) continue;
        used[i] = true;
        path[curLen++] = i + '1';
        dfs(n, i + 1, path, used, k);
        used[i] = false;
        curLen--;
    }
}

char* getPermutation(int n, int k) {
    basicSize = 8, curCnt = 0, curLen = 0;
    res = (char**)calloc(basicSize, sizeof(char*));
    bool* used = (bool*)calloc(n, sizeof(bool));
    char* path = (char*)calloc(n + 1, sizeof(char)); // ŒÕ¸'\0'
    dfs(n, 0, path, used, k);
    return res[k - 1];
}

int main() {
    int n = 3, k = 3;
    char* res = getPermutation(n, k);
    printf("%s\n", res);

    n = 4, k = 9;
    res = getPermutation(n, k);
    printf("%s\n", res);
}