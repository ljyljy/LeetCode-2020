#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>

// https://leetcode.cn/problems/closest-subsequence-sum/solution/by-mountain-ocean-1s0v/

// 法2：二分 + 双向DFS - yxc
//    目标值不明确(要求minDiff，而非确切的sum)，背包容量不知，不能用背包，只能枚举子集的和
//    https://www.acwing.com/video/2356/

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

#define N ((int)1e7) // N=40时，(1e6+10)会越界(<2^20)！
// const int N = (int)1e6 + 10; // (10^3)^2 < 2^20


int minDiff;
int n, target, start_R, end_R;
int sumMap_L[N], curCnt; // 判题：必须在入口函数初始化！否则会被覆盖！

void dfs_L(int* nums, int idx, int sum_L);
void dfs_R(int* nums, int idx, int sum_R);

static inline int cmp(const void* pa, const void* pb)
{
    return *((int*)pa) - *((int*)pb); // 升序
}

int minAbsDifference(int* nums, int _n, int _target)
{
    memset(sumMap_L, 0, sizeof(int) * N);
    curCnt = 0;
    minDiff = INT_MAX;
    n = _n; target = _target;
    start_R = n / 2, end_R = n;
    dfs_L(nums, 0, 0);  // 爆搜左边，计算sumMap_L, idx∈[0,start_R)
    qsort(sumMap_L, curCnt, sizeof(int), cmp); // 【只对[0, curCnt)排序【左闭右开】，而非整体[0,n) ！】
    // printf("%s\n", "sumMap_L: ");
    // for (int i = 0; i < curCnt; i++)
    // {
    //     printf("%d ", sumMap_L[i]);
    // }
    // printf("\n");
    dfs_R(nums, start_R, 0);
    return minDiff;
}

void dfs_L(int* nums, int idx, int sum_L)
{
    if (idx == start_R)
    {
        sumMap_L[curCnt++] = sum_L;
        return;
    }
    dfs_L(nums, idx + 1, sum_L);
    dfs_L(nums, idx + 1, sum_L + nums[idx]);
}

void dfs_R(int* nums, int idx, int sum_R)
{
    if (idx == end_R)
    { // 固定sum_R, 二分搜索合适的sumMap_L, 求minDiff
        int start_L = 0, end_L = curCnt - 1;// curCnt是开区间！sumMap_L有效下标∈[0,curCnt-1]
        // ∵ 需要start、end都做计算，选minDiff∴ ↓使用九章模板！
        while (start_L + 1 < end_L)
        { // 非[L, mid-1] [mid, R]!
            int mid_L = start_L + end_L >> 1;
            if (sumMap_L[mid_L] + sum_R <= target)
            {
                start_L = mid_L;
            }
            else {
                end_L = mid_L;
            }
        }
        int curDiff1 = abs(sumMap_L[start_L] + sum_R - target);
        minDiff = fmin(minDiff, curDiff1);
        int curDiff2 = abs(sumMap_L[end_L] + sum_R - target);
        minDiff = fmin(minDiff, curDiff2);
        return;
    }
    dfs_R(nums, idx + 1, sum_R);
    dfs_R(nums, idx + 1, sum_R + nums[idx]);
}


int main()
{
    int nums[] = { 5,-7,3,5 }, goal = 6;
    printf("minDiff = %d\n", minAbsDifference(nums, DIM(nums), goal));

    int nums2[] = { 7,-9,15,-2 }, goal2 = -5;
    printf("minDiff = %d\n", minAbsDifference(nums2, DIM(nums2), goal2));

    int nums3[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, \
        16384, 32768, 65536, 131072, 262144, 524288, -1, -2, -4, -8, -16, -32, -64, \
        - 128, -256, -512, -1024, -2048, -4096, -8192, -16384, -32768, \
        - 65536, -131072, -262144, -524288 }, goal3 = -1048574;
    printf("minDiff = %d\n", minAbsDifference(nums3, DIM(nums3), goal3));

}