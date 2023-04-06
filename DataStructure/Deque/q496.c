#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 法1：暴力，O(nq*n)
int* nextGreaterElement_BF(int* nums_query, int nq, int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(nq, sizeof(int));
    for (int i = 0; i < nq; i++) {
        int j = 0;
        while (j < n && nums[j] != nums_query[i]) j++; // nums中查找curNum，下标为j
        // 退出while：j>=n越界 或 nums[j] == curNum
        while (j < n && nums[j] <= nums_query[i]) j++; // nums中查找nums[k] > curNum，即为ans
        res[i] = j >= n ? -1 : nums[j];
    }
    *returnSize = nq;
    return res;
}


// 法2：单调栈-递减栈，O(nq+n)
/**
技巧1： 判断单调栈是递增or递减栈？
a) 求某个元素[x,即peek]右边第一个比它大的元素[i]：
    - 如果当前元素[i]>栈顶[peek]（本题无重复元素）, 则栈顶找到右边第一个比它大的元素，即为[i]，则栈顶出栈。
        - 记录答案 res[nums[peek]]=nums[i], 但并非所有nums都参与query，所以需要map记录。
            - map[nums[peek]]=nums[i]，最后遍历query，res[i]=map[query[i]]
    - 继续判断栈顶元素，直到栈顶元素比[i]小，则[i]入栈，这样栈一定是递减的。
b) vs 左边第一个比他小-递减栈, q1856/907
    - 栈顶元素[peek]是当前元素[i]左边第一个比它小的元素，即为[i]，则栈顶出栈。继续判断栈顶元素，直到栈顶元素比[i]大，则[i]入栈，这样栈一定是递增的。
 */
# define PEEK (stk[top-1])
int* nextGreaterElement(int* nums_query, int nq, int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(nq, sizeof(int));
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0; // 栈顶为[top-1], 左闭右开！
    int* map = (int*)calloc(10001, sizeof(int));  // 题目中nums[i]的范围为[1,10000]

    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[PEEK] < nums[i]) {
            map[nums[PEEK]] = nums[i]; // Map记录所有结果，最后过滤为res
            --top; /* 出栈 */
        }
        stk[top++] = i;
    }
    for (int i = 0; i < nq; i++) {
        res[i] = map[nums_query[i]] == 0 ? -1 : map[nums_query[i]];
    }
    *returnSize = nq;
    return res;
}

int main() {
    int nums_query[] = { 4, 1, 2 };
    int nq = sizeof(nums_query) / sizeof(nums_query[0]);
    int nums[] = { 1, 3, 4, 2 };
    int n = sizeof(nums) / sizeof(int);
    int returnSize;
    int* res = nextGreaterElement(nums_query, nq, nums, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}