package BFS.TopoSort;

import java.util.*;

public class q210_course_schedule_ii {
    // 法1：BFS - 拓扑
    public int[] findOrder(int n, int[][] prerequisites) {
        int[] indegrees = new int[n];
        // List<Integer> path = new ArrayList<>();
        int[] path = new int[n]; int k = 0;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair<cur, pre>
            int cur = pair[0], pre = pair[1];
            indegrees[cur]++; // 入度
            adjList.get(pre).add(cur); // 领接表pre->curs
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                n--;// ?区别于一般BFS维护的cnt，需在每一层BFS前后更新；拓扑排序：n需要在每一[层内]的结点处更新（标记剩余未遍历的[结点数]）
                // path.add(cur);
                path[k++] = cur;
                for (int nxt: adjList.get(cur)) {
                    if (--indegrees[nxt] == 0)
                        queue.offer(nxt);
                }
            }
        }
        if (n == 0) return path;
        else return new int[]{}; // 说明有课程落单，【成环】
    }
}
