package DataStructure.Heap;


import java.util.*;

public class q407_trapping_rain_water_ii {
    private int[] _x = {1,0,-1,0};
    private int[] _y = {0,1,0,-1};
    private int m, n;

    private PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] != o2[0]) return o1[0] - o2[0];
        else if (o1[1] != o2[1]) return o1[1] - o2[1];
        else return o1[2] - o2[2];
    }); // [高H, 坐标(i,j)] 【PQ<int[]>必须传入比较器，类比q786, 973】

    public int trapRainWater(int[][] heightMap) {
        m = heightMap.length; n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        int water = 0;
        // 1. 边界入堆，已访问
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isBorder(i, j)) {
                    minHeap.offer(new int[]{heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }
        // 2. 边界向内搜索BFS，入堆，计算盛水量
        while (!minHeap.isEmpty()) {
            int[] hij = minHeap.poll();
            int curH = hij[0], i = hij[1], j = hij[2];
//            System.out.println(curH + ", " + i + ", " + j);
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + _x[dir], newJ = j + _y[dir];
                if (isValid(newI, newJ) && !visited[newI][newJ]) {
                    // 比较候选min_h & 其周围的h，选较大者作为'新候选围墙柱'
                    int newH = heightMap[newI][newJ];
                    int maxH = Math.max(curH, newH);
                    water += maxH - newH; // 若四周curH高于当前newH，则盛水，否则水量不变
                    minHeap.offer(new int[]{maxH, newI, newJ}); // ?【盛水后】的高度maxH！而非newH本身!（易错?）
                    visited[newI][newJ] = true;
                }
            }
        }
        return water;
    }

    private boolean isValid(int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    private boolean isBorder(int i, int j) {
        return 0 == i || i == m-1 || 0 == j || j == n-1;
    }
}
