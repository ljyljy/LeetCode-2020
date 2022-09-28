package DataStructure.Heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 类比q264, q17_09
public class q17_09_get_kth_magic_number_lcci {
    public int getKthMagicNumber(int k) {  // 第k大 - 最小堆
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        heap.offer(1L);
        set.add(1L);

        long curNum = 1L;
        int[] factors = { 3, 5, 7 }; // 初始素因子
        for (int i = 0; i < k; i++) {
            curNum = heap.poll(); // 被poll出k次，第k次即为所求
            for (int fac: factors) {
                // nxtNum的因子只能由factors中的元素构成
                long nxtNum = curNum * fac;
                if (!set.contains(nxtNum)) {
                    set.add(nxtNum);
                    heap.offer(nxtNum);
                }
            }
        }
        return (int)curNum;
    }
}
