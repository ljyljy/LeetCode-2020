package DataStructure.Heap;

import java.util.PriorityQueue;

public class q1337_k_weakest_rows_in_a_matrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;

        // ∵保留k个小数/战力弱 ∴大顶堆(pop大的)
        PriorityQueue<int[]> heap = new PriorityQueue<>(k,
                (o1, o2) -> (o1[0] == o2[0]? o2[1]-o1[1] :o2[0]-o1[0])); // pop--<[0]val降序(弃大), 1:row_i降序(弃大)>

        for (int i = 0; i < m; i++) {
            int cnt = 0, j = 0;
            // for (int j = 0; j < n; j++) {
            while (j < n && mat[i][j] == 1) {
                cnt++;
                j++; // 防止j越界，前提：j < n
            } // 退出while：当前行遇到0或j==n
            heap.offer(new int[]{cnt, i}); // mat[i][0~j-1]共j个士兵
            if (heap.size() > k)  {
                heap.poll();
            }
            // }

        }

        int[] res = new int[k];
        int ii = k-1; // pop顺序：从小(尾)到大(头)
        while (!heap.isEmpty()) {
            res[ii--] = heap.poll()[1]; // 保存战力值val[1]
        }
        return res;

    }
}

