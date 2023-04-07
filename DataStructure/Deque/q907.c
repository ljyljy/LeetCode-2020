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
  - 【pop/弹栈】
    - --top，老栈顶后续会被覆盖
    - stk[--top]: 获取栈顶并弹栈
  - 【push/入栈】top++
- 例题
  - q907, 20, 84, 32， 496
*/

#define PEEK (stk[top - 1])
int sumSubarrayMins(int* nums, int n) {
    int cnt_R[n], cnt_L[n];
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;  // 栈顶为[top-1], 左闭右开！

    /**
    - 1）自左向右遍历[i]，查找子数组，满足条件"[i]在子数组中【最右且最小】"（计算&出栈）
      - 当前元素[PEEK]，若遇到更小的[i]：
        - 则找到了 子数组[PEEK,i] 中的最小元素 即[i]，持续出栈 查找满足条件的其他子数组（探索子数组左边界PEEK'），以计算连续子数组(PEEK',i]的个数
      - 直到 A)栈空 或 B) [PEEK']比[i]小(保持递增栈), 此时栈顶元素[PEEK']即为【左边界】
        - B) 子数组[PEEK'+1,i]中的最小元素为[i]，这样的子数组有[PEEK'+1 ~ i, i]个数为i-(PEEK'+1)+1, 即(i-PEEK')个
        - A) 递增栈为空，说明左边界PEEK'=-1，子数组[0,i]中的最小元素为[i], 有(i+1)个
    */
    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[PEEK] >= nums[i]) {
          --top; /* 出栈 */  // 保持递增栈
        }
        cnt_R[i] = i - (top == 0 ? -1 : PEEK); // R: 指代[i]是子数组中的【最右且最小】元素
        stk[top++] = i;
    }
    // memset(stk, 0, sizeof(stk)); 不可！！stk是int*指针，64位系统下 所占大小为8.
    top = 0; // stk.clear() - 勿漏！

    /**
    - 2）逆向/自右向左遍历[i]，查找子数组，满足条件"[i]在子数组中【最左且最小】"（计算&出栈）
      - 当前元素[PEEK]，若遇到更小的[i]：
        - 则找到了 子数组[i,PEEK] 中的最小元素 即[i]，持续出栈 查找满足条件的其他子数组（探索子数组右边界PEEK'），以计算连续子数组[i,PEEK')的个数
      - 直到 A)栈空 或 B) [PEEK']比[i]小(保持递增栈), 此时栈顶元素[PEEK']即为【右边界】
        - B) 子数组[i,PEEK'-1]中的最小元素为[i]，这样的子数组有[i, i ~ PEEK'-1]个数为(PEEK'-1)-i+1, 即(PEEK'-i)个
        - A) 递增栈为空，说明右边界PEEK'=n，子数组[i,n-1]中的最小元素为[i], 有(n-i)个
    */
    // 向[i]左侧看，计算连续子序列[i,n-1]满足"[i]=最左且最小"的个数（出栈）
    for (int i = n - 1; i >= 0; i--) {
        // 【易错】 此处不可取等！严格大于：[peek]>[i], 否则重复计算了1）的情况, 如[1]、[1]
        while (top != 0 && nums[PEEK] > nums[i]) {
            --top;
        }
        cnt_L[i] = (top == 0 ? n : PEEK) - i;
        stk[top++] = i;
    }
    long long sum = 0;
    const long long MOD = 1e9 + 7;
    for (int i = 0; i < n; i++) {
        sum = (sum + (long long)nums[i] * cnt_R[i] * cnt_L[i]) % MOD;
    }
    return (int)sum;
}

int main() {
    int nums[] = { 3, 1, 2, 4 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int res = sumSubarrayMins(nums, n);
    printf("%d ", res);
    return 0;
}