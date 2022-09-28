package DataStructure.Heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 类比q264, q17_09
public class q264_nthUglyNumber {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        heap.add(1L);
        Set<Long> visited = new HashSet<Long>();
        visited.add(1L);

        int[] factors = {2, 3, 5};
        long curUgly = 1L, newUgly;
        for (int i = 0; i < n; i++) {
            curUgly = heap.poll(); //  heappop() n次 – 第n小
            for (int factor : factors) {
                newUgly = factor * curUgly;
                if (visited.contains(newUgly)) continue;
                heap.add(newUgly);
                visited.add(newUgly);
            }
        }
        return (int)curUgly;
    }
}
