#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
#include <math.h>
#include <limits.h>


// q45. 跳跃游戏II
// 2. 贪心v2（反向查找出发位置，）
//   - 「贪心」地选择距离最后一个位置最远的那个位置
int jump4(int* nums, int n) {
    int curPos = n - 1;
    int curJump = 0;
    while (curPos > 0) {
        for (int i = 0; i <= curPos; i++) {
            if (i + nums[i] >= curPos) {
                curPos = i; // 可以回溯路径，从curPos向前找起点
                curJump++;
                break; // 只找距离curPos最远的，保证curJump最小
            }
        }
    }
    return curJump;
}


// 2. 贪心v1-2（正向查找，写法2）
//   - 「贪心」地选择每轮跳跃最远的位置
int jump3(int* nums, int n) {
    int curJump = 0;
    int maxPos = 0;
    int endPos = 0;
    // 起点∈[0, n-2]。n-1为终点，不起跳
    for (int start = 0; start < n - 1; start++) {
        maxPos = fmax(maxPos, start + nums[start]); // 下一轮最远距离maxPos
        if (start == endPos) { // endPos: 本轮最远距离
            endPos = maxPos; // 贪心：每轮都选择跳跃到最远的位置
            curJump++;
        }
    }
    return curJump;
}


// 2. 贪心v1-1（正向查找）
//   - 「贪心」地选择每轮跳跃最远的位置
int jump2(int* nums, int n) {
    int curJump = 0;
    int maxPos = 0;
    int start = 0, end = 1;
    while (end <= n - 1) { // 当第一次跳到终点n-1，则返回curJump 一定是最小跳跃次数
        maxPos = 0; // 每轮重新计算！
        while (start < end) { // 左闭右开，遍历本轮可以走的每个点
            maxPos = fmax(maxPos, start + nums[start]); // 保证每一轮都跳到最远的地方
            start++;
        }
        curJump++;
        start = end;
        end = maxPos + 1; // 左闭右开
    }
    return curJump;
}


// 1. dp[i]表示从起点j∈[0,i]，跳至终点i处的最小跳跃数
int jump1(int* nums, int n) {
    int dp[n]; // = {INT_MAX}; // curMinJumps[5]
    for (int i = 0; i < n; i++) dp[i] = INT_MAX;

    dp[0] = 0; // 从0起跳，故idx=0, 跳跃数=0
    for (int i = 0; i < n; i++) { // 以i为终点
        for (int j = 0; j < i; j++) { // 查找能够跳至目标i处的potential起点j
            // if (nums[i] + i >= n - 1) -- 倒推：将'n-1'替换为动态终点i, 将'i'替换为终点i倒推查找的潜在起点j
            if (j + nums[j] >= i) { // 找到符合条件的起点j
                dp[i] = fmin(dp[i], dp[j] + 1);
            }
        }
    }
    return dp[n - 1];
}

int main() {
    int nums[] = { 2, 3, 1, 1, 4 };
    int n = sizeof(nums) / sizeof(nums[0]);
    printf("%d\n", jump1(nums, n)); // 2
    printf("%d\n", jump2(nums, n)); // 2
    printf("%d\n", jump3(nums, n)); // 2
    printf("%d\n", jump4(nums, n)); // 2
}