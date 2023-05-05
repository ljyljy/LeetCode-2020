#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

#define PEEK stk[top-1]

int largestRectangleArea(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    stk[top++] = -1; // 左哨兵入栈
    memset(stk, 0, sizeof(stk));
    for (int r = 0; r < m; r++) {
        // 看到更低的柱子[r]，前面就可以计算了[递增栈?，与q42接雨水相反！]
        while (top > 0 && heights2[PEEK] > heights2[r]) {
            // ↓ 由于递增栈，因此之前的最高h即为栈顶[PEEK]
            int maxH = heights2[PEEK]; // 取栈顶
            --top; // 并弹栈
            int curArea = maxH * (r - PEEK - 1); // 宽度=[PEEK+1, r-1]（因为之前弹栈了，因此这里PEEK需+1）
            maxArea = fmax(maxArea, curArea);
        }
        stk[top++] = r;
    }

    return maxArea;
}

int main() {
    int heights[] = { 2, 1, 5, 6, 2, 3 };
    int n = sizeof(heights) / sizeof(heights[0]);
    int ans = largestRectangleArea(heights, n);
    printf("%d", ans);
    return 0;
}