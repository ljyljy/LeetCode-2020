package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q503_next_greater_element_ii {
    // 法2：采用取余（代替复制数组&最后resize）
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n * 2; i++) {
            while (!deque.isEmpty() && nums[deque.peekFirst() % n] < nums[i % n]) {
                int prevIdx = deque.pop();
                res[prevIdx] = nums[i % n];
            }
            deque.push(i % n);
        }
        return res;
    }
}
