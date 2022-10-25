#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// 写法与java基本一致

// 类比：q915、合唱队、q9_42
// 求分割点，使得分割点的「左边的子数组的最大值」小于等于「右边的子数组的最小值」。
// {0,...,k}max <= {k+1,...,n}min
int partitionDisjoint(int* nums, int n) {
    int dp_maxL[n];
    int dp_minR[n];
    dp_maxL[0] = nums[0];
    dp_minR[n - 1] = nums[n - 1];
    for (int i = 1; i < n; i++) {
        dp_maxL[i] = fmax(dp_maxL[i - 1], nums[i]);
    }
    for (int i = n - 2; i >= 0; i--) {
        dp_minR[i] = fmin(dp_minR[i + 1], nums[i]);
    }
    for (int i = 0; i + 1 < n; i++) {
        if (dp_maxL[i] <= dp_minR[i + 1]) {
            return i + 1; // len = idx+1
        }
    }
    return -1;
}