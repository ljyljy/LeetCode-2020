package DataStructure.Deque;

import java.util.*;

public class q496_next_greater_element_i {
    public int[] nextGreaterElement(int[] sub, int[] nums2) {
        int k = sub.length, n = nums2.length;
        int[] res = new int[k];
        Arrays.fill(res, -1); // 初始化为-1（最终驻留栈中的：右边无更大元素，则为-1）
        // 加速sub对nums2的元素下标的查找（hash映射）
        Map<Integer, Integer> map = new HashMap<>(); // <num, idxInSub>
        for (int i = 0; i < k; i++) {
            map.put(sub[i], i);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums2[deque.peekFirst()] < nums2[i]) {
                int prevIdx = deque.pop(); // 保持deque始终为单调栈

                if (map.containsKey(nums2[prevIdx])) {
                    int idxInSub = map.get(nums2[prevIdx]);
                    res[idxInSub] = nums2[i];// i - prevIdx;(Q739: 右边比自己大的元素的个数)
                }
            }
            deque.push(i);
        }
        return res;
    }
}
