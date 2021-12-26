package DataStructure.Heap;

import java.util.PriorityQueue;

public class qo_40_topK_Smallest {
    // 法2：大顶堆 - O(Nlogk)
    public int[] getLeastNumbers_2(int[] arr, int k) {
        if (k <= 0 || arr == null || arr.length == 0)
            return new int[0];
        // 保留k个小数 - 剔大数 - 大顶堆(重写compare, 降序-后减前)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                (o1, o2)->(o2-o1));
        for (int num: arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        int[] res = new int[k];
        while (!maxHeap.isEmpty()) {
            res[--k] = maxHeap.poll(); // ∵堆内降序(大顶堆)
        } // ∴ 升序(逆序)保存至res
        return res;
    }

////    // 法1：快排 - O(N)
//    public int[] getLeastNumbers_1(int[] buckets, int k) {
//        if (k <= 0 || buckets == null || buckets.length == 0)
//            return new int[0];
//
//    }
}
