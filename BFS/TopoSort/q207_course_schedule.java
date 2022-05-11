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
        for (int i = 0; i < n; i++) { // queue��ʼ�������q207��210��815
            if (indegrees[i] == 0) queue.offer(i); // ����offer(0==ind[i])
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                n--; // ?������һ��BFSά����cnt������ÿһ��BFSǰ����£���������n��Ҫ��ÿһ[����]�Ľ�㴦���£����ʣ��δ������[�����]��
                for (int nxt: adjList.get(cur)) {
                    if (--indegrees[nxt] == 0) {
                        queue.offer(nxt);
                    }
                }
            }
        }
        return n == 0;
    }


    // ��2��DFS-�жϳɻ� �������գ���
    // ʱ�գ�O(V+E)
    int n;
    int[] visited;
    int[][] prerequisites;
    List<List<Integer>> adjList = new ArrayList<>(); // ��ӱ�<pre, cur>
    public boolean canFinish_dfs(int n, int[][] prerequisites) {
        this.n = n; this.prerequisites = prerequisites;
        visited = new int[n];

        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair <cur, pre>
            int cur = pair[0], pre = pair[1];
            adjList.get(pre).add(cur);
        }

        for (int i = 0; i < n; i++) {
            if (!dfs(i))
                return false; // ����ͼ�ڴ��ڻ�
        }
        return true;
    }

    private boolean dfs(int i) {
        if (visited[i] == 1) return false; // �ɻ������ݹ�����֪i~�ڱ߳ɻ���
        if (visited[i] == -1) return true; //??? �����ݹ�����֪i~�����ڱ߲��ɻ���

        visited[i] = 1;
        for (int nxt: adjList.get(i)) {
            // д��1�� ���q210���ڡ���̽nxtʱ�л���
            // ���ɼ��٣�����д������q210��Ч������
            if (visited[nxt] == 1 || !dfs(nxt)) {
                return false;
            }
            // if (!dfs(nxt)) return false; // д��2��Ҳ��
        }
        visited[i] = -1;// ��Ϊ-1�����ɸĻ�Ϊ0����unvisited��������
        return true;
    }
}
