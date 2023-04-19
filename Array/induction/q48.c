#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q48.c
void rotate(int** matrix, int n_row, int* n_cols) {
    int n = n_row; // n*n¾ØÕó
    // [2,0]->[0,0], [0,1]->[1,2]
    // [i,j] -> [J, N-1-I] -> [n-1-i, n-1-j]  -> [n-1-j, i] -> Ñ­»·£º [i, j]...
    for (int i = 0; i < n / 2; i++) {
        for (int j = 0; j < (n + 1) / 2; j++) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[n - 1 - j][i];
            matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
            matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
            matrix[j][n - 1 - i] = tmp;
        }
    }
}

void printMatrix(int** matrix, int n, int* n_cols) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n_cols[i]; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int n = 3;
    int** matrix = (int**)calloc(n, sizeof(int*));
    int* n_cols = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        matrix[i] = (int*)calloc(n, sizeof(int));
        for (int j = 0; j < n; j++) {
            matrix[i][j] = i * n + j + 1;
            n_cols[i] = n;
        }
    }
    printMatrix(matrix, n, n_cols);
    rotate(matrix, n, &n_cols);

    printf("\nAfter rotate:\n");
    printMatrix(matrix, n, n_cols);

    return 0;
}