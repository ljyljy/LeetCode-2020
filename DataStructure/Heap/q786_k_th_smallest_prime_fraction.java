package DataStructure.Heap;

import java.util.PriorityQueue;

// 类比q23, 373, 786, 719（PQ超时 -> 二分+双指针）
public class q786_k_th_smallest_prime_fraction {
//    （第k小）大根堆 – 堆内k个数，第k小.poll()（堆内max）∴大根堆
// 时间复杂度：扫描所有的点对复杂度为 O(n^2)；将二元组入堆和出堆的复杂度为O(logk)。整体复杂度为 O(n^2∗logk)
// 空间复杂度：O(k)
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        // 最大堆，元素a/b形如: <buckets[0], buckets[1]>
        PriorityQueue<int[]> maxQ = new PriorityQueue<>(k,
                (a, b)->Double.compare(1.0*b[0]/b[1], 1.0*a[0]/a[1])); // 大根堆 - 逆序
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double tmp = 1.0 * arr[i] / arr[j];
                maxQ.offer(new int[]{arr[i], arr[j]});
                if (maxQ.size() > k) {
                    maxQ.poll();
                }
            }
        }
        return maxQ.poll();
    }
}
