#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

/* bool��������ϣ��
- ��Ŀ��1 <= n <= 9��
  - 1��row + col��[0, 18];
  - 2��row - col��[-n, n]->�±���Ҫ + n��ƫ����[0, 2n]��
*/
bool cols[10], xyDiff[20], xySum[20]; // ���ڼ�¼ÿһ�С�ÿһ���Խ����Ƿ����лʺ�
char*** res;
int curCnt, basicSize, curLen;
int* pathLens; // ���ڼ�¼ÿ����ĳ���, == *returnColumnSizes

void dfs(int n, int row, int* path);
bool isValid(int row, int col, int n);

char*** solveNQueens(int n, int* returnSize, int** returnColumnSizes) {
    basicSize = 8, curCnt = 0, curLen = 0;
    res = (char***)calloc(basicSize, sizeof(char**));
    pathLens = (int*)calloc(basicSize, sizeof(int));
    int* path = (int*)calloc(n, sizeof(int));  // ����path[i]��0Ϊ��Чֵ������Ҫ��ʼ��Ϊ-1
    // path:������ �ʺ���кţ�����ת��Ϊ����
    for (int i = 0; i < n; i++) {
        path[i] = -1;
    }

    dfs(n, 0, path);

    *returnSize = curCnt;
    *returnColumnSizes = pathLens;
    free(path);
    return res;
}


void dfs(int n, int row, int* path) {
    if (row == n) {
        res[curCnt] = (char**)calloc(n, sizeof(char*)); // ÿ����(res[curCnt])����һ��n*n������(char**)
        for (int i = 0; i < n; i++) { // ����ÿһ��
            res[curCnt][i] = (char*)calloc(n + 1, sizeof(char)); // ÿһ�У�ĩβ'\0'
            memset(res[curCnt][i], '.', n);
            res[curCnt][i][path[i]] = 'Q';
            res[curCnt][i][n] = '\0';
            // printf("%s \t", res[curCnt][i]);
        }
        pathLens[curCnt++] = n; // curLen == n

        if (curCnt == basicSize) {
            basicSize *= 2;
            res = (char***)realloc(res, basicSize * sizeof(char**));
            pathLens = (int*)realloc(pathLens, basicSize * sizeof(int));
        }
        return;
    }

    for (int col = 0; col < n; col++) {
        if (!isValid(row, col, n)) continue;
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = true;
        path[curLen++] = col; // path[row] = col
        dfs(n, row + 1, path);
        // path[--curLen] = -1; // v1������, �ָ���Чֵ-1
        curLen--; // v2������, �´�pathֱ�Ӷ�curLen������
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = false;
    }
}

bool isValid(int row, int col, int n) {
    // xyDiff��[-n,n]��Ҫ�±�ƫ�ƣ���֤idx��[0, 2n]��
    return !(cols[col] || xyDiff[row - col + n] || xySum[row + col]);
}

int main() {
    int n = 4;
    int returnSize;
    int* returnColumnSizes;
    char*** res = solveNQueens(n, &returnSize, &returnColumnSizes);

    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < n; j++) {
            printf("%s \t", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}