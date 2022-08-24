package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q862_shortest_subarray_with_sum_at_least_k {
    // 法1：用前缀和（保持递增）+单调栈
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + (long) nums[i-1];
            // 优化：遇到单一元素>=k的直接返回：最小长度=1
            if(nums[i - 1] >= k) return 1;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;

        // 递增栈（sum[i]-sum[peek]>=k, 则记录并比较minLen）
        for (int i = 0; i < sum.length; i++) {
            // 保证递增栈：pop栈顶！栈内都是当前尚未达到 sum[i]-sum[队头/栈底]>=k的元素
            //     若现在来了个更小的，那栈顶的较大sum就失效了
            while (!deque.isEmpty() && sum[i] <= sum[deque.peekFirst()]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && sum[i] - sum[deque.peekLast()] >= k) {
                minLen = Math.min(minLen, i - deque.removeLast());
            }
            deque.addFirst(i); // addFirst
        }
        return minLen == Integer.MAX_VALUE? -1 : minLen;
    }

    // WA! case: [84,-37,32,40,95], 167 --> AK=3, 滑窗WA=5（到末尾没有再缩小窗口！）
    // 解决：看法1，用前缀和（保持递增）+单调栈
    public int shortestSubarray_WA(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE, sum = 0;
        while (right < n) {
            sum += nums[right++];

            while (sum >= k) {
                int curLen = right - left;
                // System.out.println(sum + ", minLen:" + minLen);
                minLen = Math.min(minLen, curLen);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE? -1: minLen;
    }
}
