package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q907_sum_of_subarray_minimums {
    /**
     * - 逻辑：【栈底】需要保存【min】，因此需要【递增栈】
     * -	C[i]/L[i]：满足Min(下标∈[0,i]之间的任意连续子序列) = arr[i]，的子序列个数
     * ∵保留min于栈底 ∴递增栈 –> ∴ pop: [peek]>[i]
     * 向i的右侧看，arr[i]是连续子序列中最左且最小的数
     * -	C[i]/R[i]：满足Min(下标∈[i,n)之间的任意连续子序列) = arr[i]，的子序列个数
     * 向i的左侧看，arr[i]是连续子序列中最右且最小的数
     */
    public int sumSubarrayMins(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // 向[i]右侧看，找连续子序列[0,i]满足"[i]=最左且最小"的个数
            while (!deque.isEmpty() && nums[deque.peek()] >= nums[i]) {
                deque.pop(); // 保持递增栈，栈顶=max，栈底=min
            }
            left[i] = i - (deque.isEmpty() ? -1 : deque.peek());
            deque.push(i);
        }
        deque.clear();
        for (int i = n - 1; i >= 0; i--) {
            // 此处不可取等！严格大于：[peek]>[i], 否则子序列存在重复, 如[1]、[1]
            while (!deque.isEmpty() && nums[deque.peek()] > nums[i]) {
                deque.pop(); // 保持递增栈，栈顶=max，栈底=min
            }
            right[i] = (deque.isEmpty() ? n : deque.peek()) - i;
            deque.push(i);
        }

        long sum = 0;
        final int MOD = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            sum = (sum + (long) nums[i] * left[i] * right[i]) % MOD;
        }
        return (int) sum;
    }
}
