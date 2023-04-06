#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 法1：暴力法
int* nextGreaterElements_BF(int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        int j = (i + 1) % n;
        while (j != i) {
            if (nums[j] > nums[i]) {
                res[i] = nums[j];
                break;
            }
            j = (j + 1) % n;
        }
        if (j == i) res[i] = -1;
    }
    *returnSize = n;
    return res;
}

// 法2: 单调栈
/**
1. 递减栈
    - 当前元素nums[PEEK]，遇到下一个更大元素：nums[i]，则记录 & 出栈
    - 当前元素nums[PEEK]，遇到下一个更小元素：nums[i]，则入栈（递减栈）
2. 循环数组
    - 为了模拟循环数组，需要将下标idx % n、栈容量扩大为n*2（勿忘）
 */
#define PEEK (stk[top-1])
int* nextGreaterElements_MonoStack(int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        res[i] = -1; // 初始化，memset对int只能全局赋值0
    }
    // 单调栈-递减栈
    int* stk = (int*)calloc(n * 2, sizeof(int)); // ∵循环数组 ∴栈容量=n*2【易错】
    int top = 0;

    for (int i = 0; i < n * 2; i++) { // 循环遍历: n*2, 下标idx % n
        // 当前元素nums[PEEK]，下一个更大元素：nums[i]，则记录 & 出栈
        while (top != 0 && nums[PEEK % n] < nums[i % n]) {
            res[PEEK % n] = nums[i % n];
            --top; // 出栈
        }
        stk[top++] = i % n;
    }
    *returnSize = n;
    return res;
}