package BFS.TopoSort;

import java.util.*;

public class q207_course_schedule {
    // ��1��BFS-��������
    public boolean canFinish(int n, int[][] prerequisites) {
        int[] indegrees = new int[n]; // ���
        List<List<Integer>> adjList = new ArrayList<>(); // ��ӱ�<pre, cur>
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair <cur, pre>
            int cur = pair[0], pre = pair[1];
            indegrees[cur]++; // ���++
            adjList.get(pre).add(cur);
        }
        // BFS TopSort.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) queue.offer(i); // ����offer(0==ind[i])
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                n--; // ?
                for (int nxt: adjList.get(cur)) {
                    if (--indegrees[nxt] == 0) {
                        queue.offer(nxt);
                    }
                }
            }
        }
        return n == 0;
    }


    // ��2��DFS-�жϳɻ� ?�����죩
    int n;
    int[][] prerequisites;
    List<List<Integer>> adjList = new ArrayList<>(); // ��ӱ�<pre, cur>
    public boolean canFinish_dfs(int n, int[][] prerequisites) {
        this.n = n; this.prerequisites = prerequisites;
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair <cur, pre>
            int cur = pair[0], pre = pair[1];
            adjList.get(pre).add(cur);
        }

        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dfs(i, visited)) return false;
        }
        return true;
    }

    private boolean dfs(int i, int[] visited) {
        if (visited[i] == 1) return false; // �ɻ�
        if (visited[i] == -1) return true; // ���ݹ�����֪i~end���ɻ�
        visited[i] = 1;
        for (int nxt: adjList.get(i)) {
            if (!dfs(nxt, visited)) return false;
        }
        visited[i] = -1;

        return true;
    }
}
