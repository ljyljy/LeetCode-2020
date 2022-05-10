package DataStructure.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 类比q373, 378, 786, 719
public class q373_find_k_pairs_with_smallest_sums {
    // [荐] 法2：多路归并 & 最小堆 O((m+k)logk)
    // 考虑升序数组
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length;
        // nums1[i] + nums2[j]升序
        // 最小堆, pop小数，遍历到第k个小数【vs与常规topK小-使用最大堆 不同！】
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
        });

        for (int i = 0; i < Math.min(n1, k); i++) {
            pq.offer(new int[]{i, 0}); // {i=0:n1-1, j=0}
        }

        while (res.size() < k && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1];
            List<Integer> level = new ArrayList<>();
            level.add(nums1[i]); level.add(nums2[j]);
            res.add(level);

            if (j + 1 < n2) { // ?备选：{i+1, j}已在pq中，下一轮将poll出min者
                pq.offer(new int[]{i, j+1});
            }
        }
        return res;
    }
}
