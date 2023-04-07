#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>


// 0407 todo: q907、1856
char** res; // res[curCnt], stk/path[top]
int curCnt, basicSize;

void dfs(int n, int left, int right, char* path, int top) {
    if (left == n && right == n) {
        res[curCnt] = (char*)malloc(sizeof(char) * (2 * n + 1));
        strcpy(res[curCnt++], path);

        if (curCnt == basicSize) { // 动态扩容
            basicSize *= 2;
            res = (char**)realloc(res, sizeof(char*) * basicSize);
        }
        return;
    }
    if (left < n) {
        path[top] = '('; // 不可top++，因为下一个if可能还要用到top
        dfs(n, left + 1, right, path, top + 1);
    }
    if (right < left) {
        path[top] = ')';
        dfs(n, left, right + 1, path, top + 1);
    }
}

char** generateParenthesis(int n, int* returnSize) {
    curCnt = 0, basicSize = 8;
    res = (char**)calloc(basicSize, sizeof(char*));

    int top = 0;
    char* path = (char*)calloc(2 * n + 1, sizeof(char)); // stk
    int left = 0, right = 0;

    dfs(n, left, right, path, top);
    *returnSize = curCnt;
    return res;
}

int main() {
    int n = 3;
    int returnSize;
    char** ans = generateParenthesis(n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%s ", ans[i]);
    }
    return 0;
}