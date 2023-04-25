#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q59.c
/*
- ��άתһά��idx = i * col + j
    -  һάת��ά��i = idx / col, j = idx % col
- ȥ��(������������ʱ����res[0][0])��
    -  visited[idx] = true
-  ����ʽ�������ʲ�����˫��for������for-dir����row & col
    -  for-dir������ѭ����if dir++, ��������, ��num > n*nʱ�˳�
*/
int** res;
int* n_cols;
int curCnt;
bool* visited;
static const int _row[] = { 0, 1, 0, -1 };
static const int _col[] = { 1, 0, -1, 0 };

bool isValid(int x, int y, int n) {
    return 0 <= x && x < n && 0 <= y && y < n && !visited[x * n + y];
}

int** generateMatrix(int n, int* returnSize, int** returnColumnSizes) {
    res = (int**)calloc(n * n, sizeof(int*));
    n_cols = (int*)calloc(n, sizeof(int));
    // curCnt = n;
    for (int i = 0; i < n; i++) {
        res[i] = (int*)calloc(n, sizeof(int));
        n_cols[i] = n;
    }
    visited = (bool*)calloc(n * n, sizeof(bool));

    // ����ʽ�������ʲ�����˫��for������for-dir����row & col
    int row = 0, col = 0, num = 1;

    for (int dir = 0; dir < 4; ) { // ��num > n*nʱ�˳�����������ѭ����
        res[row][col] = num++;
        // printf("res[%d][%d] = %d\n", row, col, res[row][col]);
        visited[row * n + col] = true;

        int tryRow = row + _row[dir];
        int tryCol = col + _col[dir];
        if (!isValid(tryRow, tryCol, n)) {
            dir = (dir + 1) % 4;
        }
        row += _row[dir];
        col += _col[dir];
        if (num > n * n) break;
    }
    free(visited);
    *returnSize = n;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    int n = 3;
    int returnSize = 0;
    int** returnColumnSizes = (int**)calloc(n, sizeof(int*));
    int** res = generateMatrix(n, &returnSize, returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[0][i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}