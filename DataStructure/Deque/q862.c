#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

/**
 * 知识点
   1）队列, q934
    【队列空】head(++,poll) == tail(++,offer)
    【队列size】tail - head，无需+1（左闭右开）！！！
   2) 扩展 - 双端队列，q862
     【栈顶/队尾】 [tail-1], 左闭右开！！！
     【栈底/队头】 [head]
     【判空】 head == tail
     【pop/弹栈】tail--, pop/removeFirst
     【出队列】head++, poll/removeLast
   3）C语言 - 搜索
    坐标2D转1D：idx = i * col + j
    i = idx / col; j = idx % col;
 */
int shortestSubarray(int* nums, int n, int k) {
    long long sum[n + 1]; // maxSum = 10^10 = (10^3)^3*10=2^30*10 > INT_MAX
    sum[0] = 0;
    for (int i = 1; i <= n; i++) {
        sum[i] = sum[i - 1] + (long long)nums[i - 1];
        if (nums[i - 1] >= k) return 1;
        // printf("%d ", sum[i]);
    }
    int minLen = INT_MAX;
    int* deque = (int*)calloc(n + 1, sizeof(int));
    int head = 0, tail = 0; // head: 队首/栈底.Last, tail: 队尾/栈顶.First

    for (int i = 0; i <= n; i++) {
        // 保证递增栈：pop栈顶！栈内都是当前尚未达到 sum[i]-sum[队头/栈底]>=k的元素
        //     若现在来了个更小的，那栈顶的较大sum就失效了
        while (head != tail && sum[i] < sum[deque[tail - 1]]) {
            tail--; // 保持递增栈, pop/removeFirst; 弹栈：tail--, 老栈顶后续会被覆盖
        }
        while (head != tail && sum[i] - sum[deque[head]] >= k) {
            minLen = fmin(minLen, i - deque[head++]);
            // poll/removeLast！缩小窗口，left++ ↑
        }

        deque[tail++] = i; // push/addFirst，扩大窗口，right++
    }
    free(deque); deque = NULL;
    return minLen == INT_MAX ? -1 : minLen;
}