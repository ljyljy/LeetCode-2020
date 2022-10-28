package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q907_sum_of_subarray_minimums {
    /**
     * - �߼�����ջ�ס���Ҫ���桾min���������Ҫ������ջ��
     * -	C[i]/L[i]������Min(�±��[0,i]֮�����������������) = arr[i]���������и���
     * �߱���min��ջ�� �����ջ �C> �� pop: [peek]>[i]
     * ��i���Ҳ࿴��arr[i]����������������������С����
     * -	C[i]/R[i]������Min(�±��[i,n)֮�����������������) = arr[i]���������и���
     * ��i����࿴��arr[i]����������������������С����
     */
    public int sumSubarrayMins(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // ��[i]�Ҳ࿴��������������[0,i]����"[i]=��������С"�ĸ���
            while (!deque.isEmpty() && nums[deque.peek()] >= nums[i]) {
                deque.pop(); // ���ֵ���ջ��ջ��=max��ջ��=min
            }
            left[i] = i - (deque.isEmpty() ? -1 : deque.peek());
            deque.push(i);
        }
        deque.clear();
        for (int i = n - 1; i >= 0; i--) {
            // �˴�����ȡ�ȣ��ϸ���ڣ�[peek]>[i], ���������д����ظ�, ��[1]��[1]
            while (!deque.isEmpty() && nums[deque.peek()] > nums[i]) {
                deque.pop(); // ���ֵ���ջ��ջ��=max��ջ��=min
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
