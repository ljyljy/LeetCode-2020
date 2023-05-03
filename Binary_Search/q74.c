#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q74.c

// 法3:荐（BFS）
bool searchMatrix(int** matrix, int m, int* matrixColSize, int target) {
    int n = matrixColSize[0];
    int row = 0, col = n - 1;
    while (row < m && col >= 0) {
        if (matrix[row][col] == target) {
            return true;
        }
        else if (target > matrix[row][col]) {
            row++;
        }
        else if (target < matrix[row][col]) {
            col--;
        }
    }
    return false;
}

// 【荐】法2：二分*1次（先第一列-获取行号row_i，后在row_i行继续二分查找target）
//  - 二维转一维：arr[i][j] -> idx = i*n+j
//  - 一维转二维：idx -> i = idx/n, j = idx%n
bool searchMatrix_binSearch1(int** matrix, int m, int* matrixColSize, int target) {
    int n = matrixColSize[0];
    int start = 0, end = m * n - 1;
    while (start < end) { // [L, mid] [mid+1, R]
        int mid = start + end >> 1;
        int midVal = matrix[mid / n][mid % n];
        if (midVal == target) {
            return true;
        }
        else if (midVal < target) {
            start = mid + 1;
        }
        else {
            end = mid;
        }
    }
    return matrix[start / n][start % n] == target;
}

// 法1：二分两次，略。见Java版

int main() {
    int m = 3, n = 4;
    int** matrix = (int**)calloc(m, sizeof(int*));
    for (int i = 0; i < m; i++) {
        matrix[i] = (int*)calloc(n, sizeof(int));
    }
    int matrixColSize[3] = { n };

    int cnt = 1;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++, cnt += 2) {
            matrix[i][j] = cnt;
        }
    }

    int target = 5;
    bool res = searchMatrix(matrix, m, matrixColSize, target);
    printf("%d\n", res);

    target = 6;
    res = searchMatrix_binSearch1(matrix, m, matrixColSize, target);
    printf("%d\n", res);

    return 0;
}