#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
#include <math.h>
// #include <limits.h>

// 法1：双指针
int trap_v1(int* height, int n) {
    // 左侧[0,i]/右侧[j,n)目前最高的柱子 ↓
    int l_maxH = height[0], r_maxH = height[n - 1];
    int res = 0;

    for (int i = 0, j = n - 1; i <= j; ) {// 而非i<j！否则会漏算i==j时的列
        if (l_maxH < r_maxH) {// 写法1（获取左右侧最高柱子的较矮者）
            l_maxH = fmax(l_maxH, height[i]);
            res += l_maxH - height[i++];
        }
        else {
            r_maxH = fmax(r_maxH, height[j]);
            res += r_maxH - height[j--];
        }
    }

    return res;
}

// 法2：单调栈
#define PEEK stk[top-1]
int trap(int* height, int n) {
    int* stk = (int*)calloc(n, sizeof(int)), top = 0;
    int res = 0;

    for (int r = 0; r < n; r++) {// 而非i<j！否则会漏算i==j时的列
        while (top > 0 && height[r] > height[PEEK]) {
            int mid = stk[--top]; // 获取栈顶 & 弹栈
            if (top > 0) { // 仍非空
                int l = PEEK;
                int width = r - l - 1;
                int h = fmin(height[r], height[l]) - height[mid];
                res += width * h;
            }
        }
        stk[top++] = r;
    }
    return res;
}

int main() {
    int height[] = { 0,1,0,2,1,0,1,3,2,1,2,1 };
    int n = sizeof(height) / sizeof(height[0]);
    int res = trap(height, n);
    printf("%d\n", res);

    int height2[] = { 4,2,0,3,2,5 };
    n = sizeof(height2) / sizeof(height2[0]);
    res = trap(height2, n);
    printf("%d\n", res);
    return 0;
}