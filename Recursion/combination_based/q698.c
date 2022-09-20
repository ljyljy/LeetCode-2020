#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#ifndef DIM
#define DIM(x) sizeof(x) / sizeof(*x)
#endif

// 修改：
static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

bool dfs_v1(int* nums, int n, int bucketLeft, int idx, int bucketSum, int targetSum, bool* used);

bool canPartitionKSubsets(int* nums, int n, int k) {
    if (k > n) return false;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += nums[i];
    }
    if (sum % k != 0) return false;
    qsort(nums, n, sizeof(nums[0]), cmp);
    int targetSum = sum / k;
    bool used[n]; // 编译不报错， 不可批初始化为{false}，因为长度为变量！运行时再初始化如下
    for (int i = 0; i < n; i++) {
        used[i] = false;
    }

    return dfs_v1(nums, n, k, n - 1, 0, targetSum, used);
}


// bucketLeft: 剩余装桶数，idx：数组下标，targetSum：当前桶待装满的剩余和
bool dfs_v1(int* nums, int n, int bucketLeft, int idx, int bucketSum, int targetSum, bool* used) {
    if (bucketLeft == 0) return true;
    if (bucketSum == targetSum) {
        return dfs_v1(nums, n, bucketLeft - 1, n - 1, 0, targetSum, used);
        // ↑ 【【【勿忘return】】】!!!否则【WA，不应继续for】!!!
    }

    for (int i = idx; i >= 0; i--) {
        if (used[i]) continue;
        if (bucketSum + nums[i] > targetSum) continue;
        used[i] = true;
        if (dfs_v1(nums, n, bucketLeft, i - 1, bucketSum + nums[i], targetSum, used)) {
            return true;
        }
        used[i] = false;
        if (bucketSum == 0) return false; // 可行性剪枝
    }
    return false; // 穷举了所有数字，都无法装满当前桶
}

// 超时！
// 【荐】法2（面试写，对比分析复杂度）：枚举桶，对每个num执行选or不选【不可重复放桶, 去重used】 -- 推荐-O(k桶*(2^n))
    // 普通的组合总和 【+ 枚举桶】
bool dfs_v2(int* nums, int n, int bucketLeft, int idx, int bucketSum, int targetSum, bool* used);

bool canPartitionKSubsets_TLE(int* nums, int n, int k) {
    if (k > n) return false;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += nums[i];
    }
    if (sum % k != 0) return false;
    int targetSum = sum / k;
    bool used[n]; // 编译不报错， 不可批初始化为{false}，因为长度为变量！运行时再初始化如下
    for (int i = 0; i < n; i++) {
        used[i] = false;
    }

    return dfs_v2(nums, n, k, 0, 0, targetSum, used);
}

// bucketLeft: 剩余装桶数，idx：数组下标，targetSum：当前桶待装满的剩余和
bool dfs_v2(int* nums, int n, int bucketLeft, int idx, int bucketSum, int targetSum, bool* used) {
    if (bucketLeft == 0) return true;
    if (bucketSum == targetSum) {
        return dfs_v2(nums, n, bucketLeft - 1, 0, 0, targetSum, used);
        // ↑ 【【【勿忘return】】】!!!否则【WA，不应继续for】!!!
    }

    for (int i = idx; i < n; i++) {
        if (used[i]) continue;
        if (bucketSum + nums[i] > targetSum) continue;
        used[i] = true;
        if (dfs_v2(nums, n, bucketLeft, i + 1, bucketSum + nums[i], targetSum, used)) {
            return true;
        }
        used[i] = false;
    }
    return false; // 穷举了所有数字，都无法装满当前桶
}

int main() {

    int nums[] = { 4, 3, 2, 3, 5, 2, 1 }, k = 4;
    bool res = canPartitionKSubsets(nums, DIM(nums), k);
    printf("res=%d\n", res);

    int nums2[] = { 1,2,3,4 }, k2 = 3;
    bool res2 = canPartitionKSubsets(nums2, DIM(nums2), k2);
    printf("res=%d\n", res2);
}
