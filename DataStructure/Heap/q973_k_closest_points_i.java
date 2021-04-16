package DataStructure.Heap;

import java.util.PriorityQueue;

public class q973_k_closest_points_i {
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0) return null;
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (p1, p2) -> {
            // 大顶堆 - 降序排列（poll-优先踢出大数）
            long dist = Long.compare(getDist(p2), getDist(p1)); // 距离
            if (dist == 0) dist = p2[0] - p1[0]; // x降序
            if (dist == 0) dist = p2[1] - p1[1]; // y降序
            return (int)dist;
        });
        for(int[] point: points) {
            heap.offer(point);
            if (heap.size() > k)
                heap.poll(); // 踢出大数, 保留小者【but距离降序】
        }
        int[][] res = new int[k][2];
        while (!heap.isEmpty()) {
            res[--k] = heap.poll();
        }
        return res;
    }
    private long getDist(int[] p1) {
        return p1[0]*p1[0] + p1[1]*p1[1];
    }
}
