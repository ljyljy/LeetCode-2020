#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

// q85.c

#define PEEK stk[top-1]
int getCurArea(int* heights, int n);

int maximalRectangle(char** matrix, int m, int* matrixColSize) {
    int n = matrixColSize[0] + 2; // 左右哨兵
    int heights2[n];
    memset(heights2, 0, sizeof(heights2)); // 勿忘！

    heights2[0] = heights2[n - 1] = -1;
    int maxArea = 0;
    for (int i = 0; i < m; i++) {
        // printf("%d, ", heights2[0]);
        for (int j = 1; j < n - 1; j++) {// 左哨兵偏移1位, 右哨兵保持-1 不更新
            if (matrix[i][j - 1] == '0') {
                heights2[j] = 0;
            }
            else {
                heights2[j] += matrix[i][j - 1] - '0';
            }
            // printf("%d, ", heights2[j]);
        }
        // printf("%d \n", heights2[n-1]);
        maxArea = fmax(maxArea, getCurArea(heights2, n));
    }
    return maxArea;
}

// 推荐v1：左哨兵不提前入栈
int getCurArea(int* heights, int n) {
    int stk[n], top = 0;
    memset(stk, 0, sizeof(stk));

    int maxArea = 0;
    for (int r = 0; r < n; r++) { // 左右哨兵 高度为-1
        // 当前[r] < [PEEK]高度，[PEEK]处可以计算maxArea了（∴维护递增栈）
        while (top > 0 && heights[PEEK] > heights[r]) {
            int maxH = heights[PEEK];
            --top; // 弹栈，暴露左边界(新栈顶PEEK')
            int width = r - 1 - PEEK; // 宽度=[PEEK'+1, r-1]
            maxArea = fmax(maxArea, maxH * width);
        }
        stk[top++] = r;
    }
    return maxArea;
}


/* 左哨兵提前入栈，则需要改2处：
  - 1) stk[top++] = 0;
  - 2) for 从 idx=1 开始遍历
  - 3) while (top > 1 && ...)
*/
int getCurArea_v2(int* heights, int n) {
    int stk[n], top = 0;
    memset(stk, 0, sizeof(stk));
    stk[top++] = 0; // v2: 左哨兵(idx=0)提前入栈，后续需保证栈内至少有一个左哨兵(top>1)

    int maxArea = 0;
    for (int r = 1; r < n; r++) { // 左右哨兵为-1
        // 当前[r] < [PEEK]高度，[PEEK]处可以计算maxArea了（∴维护递增栈）
        while (top > 1 && heights[PEEK] > heights[r]) {
            int maxH = heights[PEEK];
            --top; // 弹栈，暴露左边界(新栈顶PEEK')
            int width = r - 1 - PEEK; // 宽度=[PEEK'+1, r-1]
            maxArea = fmax(maxArea, maxH * width);
            // printf("PEEK'+1=%d, r-1=%d, width=%d, curArea=%d, maxArea=%d\n", PEEK+1, r-1, width, maxH * width, maxArea);
        }
        stk[top++] = r;
    }
    return maxArea;
}

int main(int argc, char const* argv[]) {
    int m = 4;
    int matrixColSize[] = { 5, 5, 5, 5 };
    char* matrix[] = {
        "10100",
        "10111",
        "11111",
        "10010"
    };
    int res = maximalRectangle(matrix, m, matrixColSize);
    printf("res = %d\n", res);
    return 0;
}