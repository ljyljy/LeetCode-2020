#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

/*
知识点

- 左右哨兵、维护递增栈
- **左哨兵**是为了保证所有柱子都可以被计算，因为左哨兵最矮（高-1），势必能保证后续柱子是递增的。
  - **左哨兵**无需提前入栈！放在for循环里即可
  - 否则：提前入栈(`stk[top++] = 0`)，则仍需保证这一点：
    - for从r=1开始遍历，且while内需保持 top>1 ，即左哨兵一旦进栈，由于是minVal，因此永远不会出栈
      - 置`while (top > 1 && heights2[PEEK] > heights2[r]) {...}`
- **右哨兵**是为了防止最后柱子入栈，但无法被计算。因此必须要有个小于最后柱子的右哨兵，保证最后柱子能被弹栈 & 计算！

*/

// q84.c

#define PEEK stk[top-1]

// 推荐v1：左哨兵不提前入栈
int largestRectangleArea1(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    memset(stk, 0, sizeof(stk));
    // stk[top++] = 0; // 左哨兵无需提前入栈，放在for循环里即可

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

/* v2：左哨兵提前入栈，则需要改3处：
  - 1) stk[top++] = 0;
  - 2) for 从 idx=1 开始遍历
  - 3) while (top > 1 && ...)
*/
int largestRectangleArea(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    memset(stk, 0, sizeof(stk));
    stk[top++] = 0; // 左哨兵(idx=0)提前入栈，后续需保证栈内至少有一个左哨兵(top>1)

    for (int r = 1; r < m; r++) {
        // 看到更低的柱子[r]，前面就可以计算了[递增栈?，与q42接雨水相反！]
        while (top > 1 && heights2[PEEK] > heights2[r]) {
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