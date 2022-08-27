#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

#define DUMMY_NUM 2;

int largestRectangleArea(int* heights, int n) {
    int maxArea = 0;
    int m = n + DUMMY_NUM;
    int *tmp = malloc(sizeof(int) * m);
    memset(tmp, 0, sizeof(int) * m); // 勿忘赋值！首尾哨兵！
    for (int i = 1; i <= n; ++i) { // 0 [1,n] n+1
        tmp[i] = heights[i-1];
    }
    int *stk = malloc(sizeof(int) * m);
    memset(stk, 0, sizeof(int) * m);
    int top = 0;
    for (int r = 0; r < m; ++r) {
        while (top > 0 && tmp[r] < tmp[stk[top-1]]) { // 栈顶应为top-1！
            int h = tmp[stk[top-1]];
            top--;
            int width = r - stk[top-1] - 1;
            maxArea = fmax(maxArea, width * h);
        }
        stk[top++] = r; // 栈顶idx=0，top=1 -> 因此当stk非空后，取栈顶应为top-1！
    }
    return maxArea;
}