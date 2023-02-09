#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

/*
  2】
  1）二维指针malloc、memset(..., len+1)
  2）调用时，勿混淆char[]与char*（错误！没有数组长度属性！导致段错误！）
*/

// new
char* convert(char* s, int numRows) {
    int len = strlen(s);
    if (numRows == 1 || len <= numRows) return s;
    char** mat = (char**)calloc(numRows, sizeof(char*));
    for (int i = 0; i < numRows; i++) {
        mat[i] = (char*)calloc(len + 1, sizeof(char));// 【勿忘+1 ('\0')】
    }

    int* lastColIdxOfRows = (int*)calloc(numRows, sizeof(int));
    int row_i = 0, flag = -1;
    for (int i = 0; i < len; i++) {
        int curCol = lastColIdxOfRows[row_i]++;
        mat[row_i][curCol] = s[i];
        if (row_i == 0 || row_i == numRows - 1) {
            flag = -flag;
        }
        row_i += flag;
    }

    char* res = (char*)calloc(len + 1, sizeof(char));
    int idx = 0;
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < lastColIdxOfRows[i]; j++) {
            res[idx++] = mat[i][j];
        }
        free(mat[i]);
        mat[i] = NULL;
    }
    free(mat);
    mat = NULL;
    res[len] = 0; // res[idx] = 0;
    return res;
}

// Old
void FREE(void* p) {
    free(p);
    p = NULL;
}
char* convert(char * s, int n){
    int len = strlen(s);
    if (n == 1 || n >= len) return s;

    char** mat = (char**)malloc(sizeof (char*) * n);  // List<StringBuilder>, n*(len+1)
    for (int i = 0; i < n; i++) {// n个sb，全部初始化
        // 每行都保证能存下最大长度=len的字符串
        mat[i] = malloc(sizeof(char) * (len+1)); // 【勿忘+1 ('\0')】
        memset(mat[i], 0, sizeof(char) * (len+1));
    }
    int* rows_lastIdx = (int*)malloc(sizeof (int) * n); // 每行上次读到的idx
    memset(rows_lastIdx, 0, sizeof (int) * n);

    int row_i = 0, flag = -1;
    for (int i = 0; i < len; i++) {
        char c = s[i];
        int curColIdx = rows_lastIdx[row_i]++;// 指针取值后，后移+1
        mat[row_i][curColIdx] = c;
        // printf("i=%d, j=%d, c=%c\n", i, curColIdx, c);
        if (row_i == 0 || row_i == n-1) {
            flag = -flag;
        }
        row_i += flag;
    }
    // 覆盖s
    int idx = 0;
    for (int i = 0; i < n; i++) {
        int curColIdx = rows_lastIdx[i];
        // printf("curColIdx = %d\n", curColIdx);
        for (int j = 0; j < curColIdx; j++) {
            s[idx++] = mat[i][j];
            // printf("mat[%d][j]=%c, sizeof(mat[i])=%d\n", i, mat[i][j], strlen(mat[i]));
        }
        FREE(mat[i]);
    }
    // s[idx] = 0;
    FREE(mat);
    FREE(rows_lastIdx);
    return s;
}
//leetcode submit region end(Prohibit modification and deletion)

 int main() {
     char s[] = "PAYPALISHIRING"; // 【勿混淆指针与数组】！若定义为char* s,则丢失了数组长度属性！导致程序出错！
     int numRows = 3;
     char *res = convert(s, numRows);
     printf("%s\n", res);
     return 0;
 }