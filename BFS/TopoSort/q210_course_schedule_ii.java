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


    // 法2：DFS -- 时空O(V+E)
    int n, idx;// 栈下标
    List<List<Integer>> adjList = new ArrayList<>();
    int[] path, visited;
    int[][] prerequisites;
    boolean valid = true; // 判断有向图中是否有环
    public int[] findOrder_dfs(int n, int[][] prerequisites) {
        this.n = n; this.prerequisites = prerequisites;
        path = new int[n];  visited = new int[n];
        this.idx = n-1;

        for (int i = 0; i < n; i++) adjList.add(i, new ArrayList<>());
        for (int[] pair: prerequisites) {
            int pre = pair[1], nxt = pair[0];
            adjList.get(pre).add(nxt);
        }

        for (int i = 0; i < n && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid? path: new int[]{};
    }

    private void dfs(int cur) {
        // ??【判环必须在下探nxt时进行！入口处无效！??】
        // if (visited[cur] == 1) { // 成环【回溯过，已知i~邻边成环】
        //     valid = false;
        //     return;
        // } else if (visited[cur] == -1) {
        //     return; //??? 【回溯过，已知i~所有邻边不成环】
        // }

        visited[cur] = 1;
        for (int nxt: adjList.get(cur)) {
            if (visited[nxt] == 0) {
                dfs(nxt);
            } else if (visited[nxt] == 1) { // 必须在【下探nxt时判环】！【在dfs入口处判断成环无效】！！
                // 成环【回溯过，已知i~邻边成环】
                valid = false;
                return;
            }
        }
        visited[cur] = -1;
        path[idx--] = cur;// ?将节点入栈【dfs-栈-path从后往前】！
    }
}
