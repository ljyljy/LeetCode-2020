#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

int threeSumClosest(int* nums, int n, int target) {
    qsort(nums, n, sizeof(int), cmp);
    int minDiff = INT_MAX, minSum = INT_MAX;
    for (int i = 0; i < n; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        int lf = i + 1, rt = n - 1;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt] + nums[i];
            int curDiff = abs(sum - target);
            if (curDiff == 0) {
                return sum;
            }
            else if (curDiff < minDiff) {
                minDiff = curDiff;
                minSum = sum;
            }

            if (sum < target) lf++;
            else rt--;
        }
    }
    return minSum;
}
