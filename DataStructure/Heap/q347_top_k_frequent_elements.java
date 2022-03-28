package DataStructure.Heap;

import java.util.*;

public class q347_top_k_frequent_elements {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        // Ç°k´ó - Ğ¡¸ù¶Ñ(´ÊÆµÉıĞò¡¢Êı×ÖÉıĞò)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (o1, o2) ->
                map.get(o1) == map.get(o2)?  o1-o2 : map.get(o1) - map.get(o2)
        );
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            minHeap.offer(entry.getKey());
            if (minHeap.size() > k)
                minHeap.poll();
        }
        int[] res = new int[k];
        int i = k-1;
        while (!minHeap.isEmpty()) {
            res[i--] = minHeap.poll(); // pollÎªÉıĞò£¬ÄæĞò±£´æ£¨resÎª´ÊÆµ½µĞò£©
        }
        return res;
    }
}
