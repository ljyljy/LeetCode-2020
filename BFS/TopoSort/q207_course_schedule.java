package BFS.TopoSort;

import java.util.*;

public class q207_course_schedule {
    // 法1：BFS-拓扑排序
    public boolean canFinish(int n, int[][] prerequisites) {
        int[] indegrees = new int[n]; // 入度
        List<List<Integer>> adjList = new ArrayList<>(); // 领接表<pre, cur>
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair <cur, pre>
            int cur = pair[0], pre = pair[1];
            indegrees[cur]++; // 入度++
            adjList.get(pre).add(cur);
        }
        // BFS TopSort.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // queue初始化：类比q207，210，815
            if (indegrees[i] == 0) queue.offer(i); // 不是offer(0==ind[i])
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                n--; // ?区别于一般BFS维护的cnt，需在每一层BFS前后更新；拓扑排序：n需要在每一[层内]的结点处更新（标记剩余未遍历的[结点数]）
                for (int nxt: adjList.get(cur)) {
                    if (--indegrees[nxt] == 0) {
                        queue.offer(nxt);
                    }
                }
            }
        }
        return n == 0;
    }


    // 法2：DFS-判断成环 ?（不熟）
    int n;
    int[][] prerequisites;
    List<List<Integer>> adjList = new ArrayList<>(); // 领接表<pre, cur>
    public boolean canFinish_dfs(int n, int[][] prerequisites) {
        this.n = n; this.prerequisites = prerequisites;
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair <cur, pre>
            int cur = pair[0], pre = pair[1];
            adjList.get(pre).add(cur);
        }

        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dfs(i, visited)) return false; // 有向图内存在环
        }
        return true;
    }

    private boolean dfs(int i, int[] visited) {
        if (visited[i] == 1) return false; // 成环
        if (visited[i] == -1) return true; // 回溯过，已知i~end不成环
        visited[i] = 1;
        for (int nxt: adjList.get(i)) {
            if (!dfs(nxt, visited)) return false;
        }
        visited[i] = -1;

        return true;
    }
}
