#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q73.c

// 法1：空间O(m+n)
void setZeroes(int** matrix, int m, int* matrixColSize) {
    int n = matrixColSize[0];
    bool rows[m], cols[n]; // 数组替代哈希
    memset(rows, 0, sizeof(rows));
    memset(cols, 0, sizeof(cols));

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                rows[i] = cols[j] = true;
            }
        }
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (rows[i] || cols[j]) {
                matrix[i][j] = 0;
            }
        }
    }
}

int main() {
    int m = 3, n = 4;
    int** matrix = (int**)calloc(m, sizeof(int*));
    for (int i = 0; i < m; i++) {
        matrix[i] = (int*)calloc(n, sizeof(int));
    }
    int matrixColSize[3] = { n };

    int cnt = 1;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++, cnt++) {
            matrix[i][j] = cnt;
        }
    }
    matrix[1][1] = 0;
    matrix[2][3] = 0;

    // 遍历matrix
    printf("original matrix:\n");
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
    }


    setZeroes(matrix, m, matrixColSize);

    // 遍历matrix
    printf("\nsetZeroes:\n");
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
    }

    return 0;
}