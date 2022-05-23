package DataStructure.Deque;

import java.util.LinkedList;

public class q239_sliding_window_maximum_3star {
    // 双端队列实现【单调栈】
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (nums == null || n * k == 0) return new int[0];
        if (k == 1) return nums;

        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[n - k + 1];// 下标为0~n-k
        int j = 0;
        for (int i = 0;  i < n; ++i) {
            // while!【维持窗口长度】移除k范围外的数 【peek队首】
            while (!queue.isEmpty() && queue.peek() <= i - k) {
                queue.poll(); // 或pollFirst(): poll出队头/oldest
            }
            // while!❤ 维护单调递减栈！（从队尾开始往前操作）
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast(); // 只保留【大数位于队头】（小的数全都poll出去啦！）
            }
            queue.offer(i); // 或offerLast(): 入队尾
            // 至少遍历完整的第一个窗口以后，才能记录res
            if (i >= k - 1) res[j++] = nums[queue.peek()];// keep大数位于队头
        }
        return res;
    }

    // 1.暴力 —— 时间 O(N*k), 其中N为数组中元素个数；空间O(N-k+1), 用于输出数组。
    public int[] maxSlidingWindow2(int[] nums, int k) {
        // 或：if(len*k == 0)
        if (nums.length == 0 || k == 0) return new int[0];
        if (k == 1) return nums;
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; ++i) {
            int maxNum = Integer.MIN_VALUE;
            for (int j = i; j < i + k; ++j) {
                maxNum = Math.max(maxNum, nums[j]);
            }
            res[i] = maxNum;
        }
        return res;
    }

}

