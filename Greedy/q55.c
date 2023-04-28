#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q55.c，类比q45.c


// 1-2. 贪心（正向查找，写法2）
//   - 「贪心」地选择每轮跳跃最远的位置，能否跳到n-1 && 每轮maxPos是否严格递增
bool canJump2(int* nums, int n) {
    int start = 0, end = 0; // 左闭右闭
    int maxPos = 0;
    // 起点∈[0, n-2]。n-1为终点，不起跳
    for (start = 0; start < n - 1; start++) {
        maxPos = fmax(maxPos, start + nums[start]); // 下一轮最远距离（左闭）
        if (maxPos <= start) {
            return false; // 如: [0,2,3]
        } // ↑ 注意不是每个点都一定能达到！须比较【下一轮maxPos是否真的增加】了！
        if (start == end) { // endPos: 本轮最远距离
            end = maxPos; // 更新为下一轮最远距离
        }
    }
    return maxPos >= n - 1;
}


// 1. 贪心（正向查找，写法1）
//   - 「贪心」地选择每轮跳跃最远的位置，能否跳到n-1 && 每轮maxPos是否严格递增
bool canJump1(int* nums, int n) {
    int start = 0, end = 1;
    int maxPos = 0, lastMaxPos = 0;
    for (start = 0; start < n; ) { // 起始点∈[0, n-1] 遍历
        maxPos = 0;
        while (start < n && start < end) {
            maxPos = fmax(maxPos, start + nums[start]); // 下一轮最远距离（左闭）
            start++;
        }
        if (maxPos == lastMaxPos && maxPos < n - 1) {
            return false; // 若【本轮maxPos没有增长】，且永远达不到末尾n-1处
        }
        // printf("start=%d, end=%d, maxPos=%d\n", start, end, maxPos);
        lastMaxPos = maxPos;
        start = end;
        end = maxPos + 1; // 【易错】右开！
    }
    return maxPos >= n - 1;
}




// 1-2. 贪心（逆向查找，写法1）
//   - 「贪心」地选择每轮距离本轮起点curPos最远/最接近0的位置，能否跳到0 && 每轮curPos是否严格递减
bool canJump(int* nums, int n) {
    int curPos = n - 1, lastPos = n - 1;
    int cnt = n; // 由于curPos可能一直>0造成死循环，故限制最多遍历n次
    // 终点curPos逆序找起跳点，因此curPos不可以包括起跳点0
    while (curPos > 0 && cnt-- > 0) {
        for (int j = 0; j < curPos; j++) {
            if (j + nums[j] >= curPos) {
                lastPos = curPos;
                curPos = j; // 贪心地找能跳到curPos的最远起点j
                // printf("lastPos = %d, curPos=%d\n", lastPos, curPos);
                if (curPos == 0) return true; // 可以从0起跳，有解
                break;
            }
            // if (j == curPos - 1 && j + nums[j] < curPos) return false; // j即将遍历结束（前提curPos未到达0），仍未找到可以跳到curPos的起点。无解。
        }
        // 若无解，则进入下面的if
        if (lastPos == curPos && curPos > 0) {
            return false; // curPos不再递减 且 无法走到0.
        }
    }
    return curPos <= 0;
}

int main() {
    int nums[] = { 2,3,1,1,4 };
    int n = sizeof(nums) / sizeof(int);
    printf("%d\n", canJump(nums, n)); // true
    printf("%d\n", canJump1(nums, n)); // true
    printf("%d\n", canJump2(nums, n)); // true

    int nums2[] = { 3,2,1,0,4 };
    int n2 = sizeof(nums2) / sizeof(int);
    printf("%d\n", canJump(nums2, n2)); // false
    printf("%d\n", canJump1(nums2, n2)); // false
    printf("%d\n", canJump2(nums2, n2)); // false

    int nums3[] = { 0,2,3 };
    int n3 = sizeof(nums3) / sizeof(int);
    printf("%d\n", canJump(nums3, n3)); // false
    printf("%d\n", canJump1(nums3, n3)); // false
    printf("%d\n", canJump2(nums3, n3)); // false

    int nums4[] = { 2,0,0 };
    int n4 = sizeof(nums4) / sizeof(int);
    printf("%d\n", canJump(nums4, n4)); // true
    printf("%d\n", canJump1(nums4, n4)); // true
    printf("%d\n", canJump2(nums4, n4)); // true

    int nums5[] = { 2,0,1,0,1 };
    int n5 = sizeof(nums5) / sizeof(int);
    printf("%d\n", canJump(nums5, n5)); // false
    printf("%d\n", canJump1(nums5, n5)); // false
    printf("%d\n", canJump2(nums5, n5)); // false

    int nums6[] = { 2,0,1,0,4 };
    int n6 = sizeof(nums6) / sizeof(int);
    printf("%d\n", canJump(nums6, n6)); // false
    printf("%d\n", canJump1(nums6, n6)); // false
    printf("%d\n", canJump2(nums6, n6)); // false

    return 0;
}