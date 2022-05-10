package DataStructure.Heap;

import java.util.PriorityQueue;

public class q378_kth_smallest_element_in_a_sorted_matrix {
    // 法1：最大堆 O(mnlogk) - 未考虑升序矩阵
    public int kthSmallest1(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        if (m * n < k) return -1;
        int curK = 0;
        // 最大堆, pop大数，保留k个小数
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2)-> o2-o1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(matrix[i][j]);
                if (pq.size() > k)
                    pq.poll();
            }
        }
        return pq.peek();
    }

    // [荐] 法2：多路归并 & 最小堆 O((m+k)logk)
    // 考虑升序矩阵
    // 类比q373, 378, 786, 719
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        if (m * n < k) return -1;
        int curK = 0;
        // 最小堆, pop小数，遍历到第k个小数【vs与常规topK小-使用最大堆 不同！】
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)-> {
            return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
        });

        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0}); // {i=0:n1-1, j=0}
        }

        while (curK++ < k && !pq.isEmpty()){
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1];
            if (curK == k) return matrix[i][j];
            if (j + 1 < n) {
                pq.offer(new int[]{i, j+1});
            }
        }

        int[] idx = pq.peek();
        return matrix[idx[0]][idx[1]];
    }
}
