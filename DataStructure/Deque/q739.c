#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>


// 法1：暴力法, TLE
int* dailyTemperatures_BF(int* temperatures, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n) {
            if (temperatures[j] > temperatures[i]) {
                res[i] = j - i;
                break;
            }
            j++;
        }
        if (j == n) res[i] = 0;
    }
    *returnSize = n;
    return res;
}

// 法2: 单调栈
/**
1. 递减栈
    - 当前元素temperatures[PEEK]，遇到下一个更大元素：temperatures[i]，则记录 & 出栈
    - 当前元素temperatures[PEEK]，遇到下一个更小元素：temperatures[i]，则入栈（递减栈）
 */
#define PEEK (stk[top-1])
int* dailyTemperatures(int* temperatures, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    // 单调栈-递减栈
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;

    for (int i = 0; i < n; i++) {
        // 当前元素temperatures[PEEK]，下一个更大元素：temperatures[i]，则记录 & 出栈
        while (top != 0 && temperatures[PEEK] < temperatures[i]) {
            res[PEEK] = i - PEEK;
            --top; // 出栈
        }
        stk[top++] = i;
    }
    *returnSize = n;
    return res;
}

int main() {
    int temperatures[] = { 73, 74, 75, 71, 69, 72, 76, 73 }; // 1 1 4 2 1 1 0 0
    int n = sizeof(temperatures) / sizeof(temperatures[0]);
    int returnSize;
    int* res = dailyTemperatures(temperatures, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}
