#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

/**
 * ֪ʶ��
   1������, q934
    �����пա�head(++,poll) == tail(++,offer)
    ������size��tail - head������+1������ҿ���������
   2) ��չ - ˫�˶��У�q862
     ��ջ��/��β�� [tail-1], ����ҿ�������
     ��ջ��/��ͷ�� [head]
     ���пա� head == tail
     ��pop/��ջ��tail--, pop/removeFirst
     �������С�head++, poll/removeLast
   3��C���� - ����
    ����2Dת1D��idx = i * col + j
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
    int head = 0, tail = 0; // head: ����/ջ��.Last, tail: ��β/ջ��.First

    for (int i = 0; i <= n; i++) {
        // ��֤����ջ��popջ����ջ�ڶ��ǵ�ǰ��δ�ﵽ sum[i]-sum[��ͷ/ջ��]>=k��Ԫ��
        //     ���������˸���С�ģ���ջ���Ľϴ�sum��ʧЧ��
        while (head != tail && sum[i] < sum[deque[tail - 1]]) {
            tail--; // ���ֵ���ջ, pop/removeFirst; ��ջ��tail--, ��ջ�������ᱻ����
        }
        while (head != tail && sum[i] - sum[deque[head]] >= k) {
            minLen = fmin(minLen, i - deque[head++]);
            // poll/removeLast����С���ڣ�left++ ��
        }

        deque[tail++] = i; // push/addFirst�����󴰿ڣ�right++
    }
    free(deque); deque = NULL;
    return minLen == INT_MAX ? -1 : minLen;
}