#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/*
栈实现1（推荐）
- 操作
  - 初始化，top = 0
  - 【栈顶】 stk[top-1], 左闭右开！！！
  - 【判空】 top == 0
  - 【pop/弹栈】top--
    - top--，老栈顶后续会被覆盖
  - 【push/入栈】top++
- 例题
  - q907, 20, 84, 32， 496
*/
int sumSubarrayMins(int* nums, int n) {
    int left[n], right[n];
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;  // 栈顶为[top-1], 左闭右开！
    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[stk[top - 1]] >= nums[i]) {
            top--; /* 出栈 */  // 保持递增栈
        }
        left[i] = i - (top == 0 ? -1 : stk[top - 1]);
        stk[top++] = i;
    }
    // memset(stk, 0, sizeof(stk)); // 不可！！LC-堆溢出！？
    top = 0; // stk.clear() - 勿漏！
    for (int i = n - 1; i >= 0; i--) {
        // 此处不可取等！严格大于：[peek]>[i], 否则子序列存在重复, 如[1]、[1]
        while (top != 0 && nums[stk[top - 1]] > nums[i]) {
            top--;
        }
        right[i] = (top == 0 ? n : stk[top - 1]) - i;
        stk[top++] = i;
    }
    long long sum = 0;
    const long long MOD = 1e9 + 7;
    for (int i = 0; i < n; i++) {
        sum = (sum + (long long)nums[i] * left[i] * right[i]) % MOD;
    }
    return (int)sum;
}