package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q862_shortest_subarray_with_sum_at_least_k {
    // ��1����ǰ׺�ͣ����ֵ�����+����ջ
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + (long) nums[i-1];
            // �Ż���������һԪ��>=k��ֱ�ӷ��أ���С����=1
            if(nums[i - 1] >= k) return 1;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;

        // ����ջ��sum[i]-sum[peek]>=k, ���¼���Ƚ�minLen��
        for (int i = 0; i < sum.length; i++) {
            // ��֤����ջ��popջ����ջ�ڶ��ǵ�ǰ��δ�ﵽ sum[i]-sum[��ͷ/ջ��]>=k��Ԫ��
            //     ���������˸���С�ģ���ջ���Ľϴ�sum��ʧЧ��
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

    // WA! case: [84,-37,32,40,95], 167 --> AK=3, ����WA=5����ĩβû������С���ڣ���
    // ���������1����ǰ׺�ͣ����ֵ�����+����ջ
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
