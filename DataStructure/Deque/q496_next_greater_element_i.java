package DataStructure.Deque;

import java.util.*;

public class q496_next_greater_element_i {
    public int[] nextGreaterElement(int[] sub, int[] nums2) {
        int k = sub.length, n = nums2.length;
        int[] res = new int[k];
        Arrays.fill(res, -1); // ��ʼ��Ϊ-1������פ��ջ�еģ��ұ��޸���Ԫ�أ���Ϊ-1��
        // ����sub��nums2��Ԫ���±�Ĳ��ң�hashӳ�䣩
        Map<Integer, Integer> map = new HashMap<>(); // <num, idxInSub>
        for (int i = 0; i < k; i++) {
            map.put(sub[i], i);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums2[deque.peekFirst()] < nums2[i]) {
                int prevIdx = deque.pop(); // ����dequeʼ��Ϊ����ջ

                if (map.containsKey(nums2[prevIdx])) {
                    int idxInSub = map.get(nums2[prevIdx]);
                    res[idxInSub] = nums2[i];// i - prevIdx;(Q739: �ұ߱��Լ����Ԫ�صĸ���)
                }
            }
            deque.push(i);
        }
        return res;
    }
}
