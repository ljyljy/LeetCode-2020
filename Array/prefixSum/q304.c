#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <ctype.h>

// q304. Range Sum Query 2D - Immutable
typedef struct {
    int** sumMat; // (m+1) * (n+1)
    int m;
    int n;
} NumMatrix;

NumMatrix* numMatrixCreate(int** matrix, int matrixSize, int* matrixColSize) {
    NumMatrix* obj = (NumMatrix*)calloc(1, sizeof(NumMatrix));
    obj->m = matrixSize;
    obj->n = matrixSize == 0 ? 0 : matrixColSize[0];
    obj->sumMat = (int**)calloc((obj->m) + 1, sizeof(int*));
    obj->sumMat[0] = (int*)calloc((obj->n) + 1, sizeof(int)); // ÎðÍü£¡£¡

    for (int i = 1; i <= obj->m; i++) {
        obj->sumMat[i] = (int*)calloc((obj->n) + 1, sizeof(int));
        for (int j = 1; j <= obj->n; j++) {
            obj->sumMat[i][j] = obj->sumMat[i - 1][j] + obj->sumMat[i][j - 1] \
                - obj->sumMat[i - 1][j - 1] + matrix[i - 1][j - 1];
        }
    }
    return obj;
}

int numMatrixSumRegion(NumMatrix* obj, int row1, int col1, int row2, int col2) {
    row1++; col1++; row2++; col2++;
    return obj->sumMat[row2][col2] - obj->sumMat[row2][col1 - 1]  \
        - obj->sumMat[row1 - 1][col2] + obj->sumMat[row1 - 1][col1 - 1];
}

void numMatrixFree(NumMatrix* obj) {
    if (obj->sumMat) {
        for (int i = 0; i < obj->m; i++) {
            free(obj->sumMat[i]);
        }
        free(obj->sumMat);
    }
    free(obj);
}

/**
 * Your NumMatrix struct will be instantiated and called as such:
 * NumMatrix* obj = numMatrixCreate(matrix, matrixSize, matrixColSize);
 * int param_1 = numMatrixSumRegion(obj, row1, col1, row2, col2);

 * numMatrixFree(obj);
*/

int main() {
    int** matrix = (int**)calloc(3, sizeof(int*));
    matrix[0] = (int*)calloc(3, sizeof(int));
    matrix[1] = (int*)calloc(3, sizeof(int));
    matrix[2] = (int*)calloc(3, sizeof(int));
    matrix[0][0] = 3; matrix[0][1] = 0; matrix[0][2] = 1;
    matrix[1][0] = 5; matrix[1][1] = 6; matrix[1][2] = 3;
    matrix[2][0] = 1; matrix[2][1] = 2; matrix[2][2] = 0;
    int matrixSize = 3;
    int matrixColSize[] = { 3, 3, 3 };

    NumMatrix* obj = numMatrixCreate(matrix, matrixSize, matrixColSize);
    int param_1 = numMatrixSumRegion(obj, 1, 1, 2, 2);
    printf("%d\n", param_1);
    numMatrixFree(obj);
    return 0;
}