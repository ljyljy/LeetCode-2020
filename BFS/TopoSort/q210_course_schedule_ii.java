package BFS.TopoSort;

import java.util.*;

public class q210_course_schedule_ii {
    // ��1��BFS - ����
    public int[] findOrder(int n, int[][] prerequisites) {
        int[] indegrees = new int[n];
        // List<Integer> path = new ArrayList<>();
        int[] path = new int[n]; int k = 0;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] pair: prerequisites) { // pair<cur, pre>
            int cur = pair[0], pre = pair[1];
            indegrees[cur]++; // ���
            adjList.get(pre).add(cur); // ��ӱ�pre->curs
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                n--;// ?������һ��BFSά����cnt������ÿһ��BFSǰ����£���������n��Ҫ��ÿһ[����]�Ľ�㴦���£����ʣ��δ������[�����]��
                // path.add(cur);
                path[k++] = cur;
                for (int nxt: adjList.get(cur)) {
                    if (--indegrees[nxt] == 0)
                        queue.offer(nxt);
                }
            }
        }
        if (n == 0) return path;
        else return new int[]{}; // ˵���пγ��䵥�����ɻ���
    }


    // ��2��DFS -- ʱ��O(V+E)
    int n, idx;// ջ�±�
    List<List<Integer>> adjList = new ArrayList<>();
    int[] path, visited;
    int[][] prerequisites;
    boolean valid = true; // �ж�����ͼ���Ƿ��л�
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
        // ??���л���������̽nxtʱ���У���ڴ���Ч��??��
        // if (visited[cur] == 1) { // �ɻ������ݹ�����֪i~�ڱ߳ɻ���
        //     valid = false;
        //     return;
        // } else if (visited[cur] == -1) {
        //     return; //??? �����ݹ�����֪i~�����ڱ߲��ɻ���
        // }

        visited[cur] = 1;
        for (int nxt: adjList.get(cur)) {
            if (visited[nxt] == 0) {
                dfs(nxt);
            } else if (visited[nxt] == 1) { // �����ڡ���̽nxtʱ�л���������dfs��ڴ��жϳɻ���Ч������
                // �ɻ������ݹ�����֪i~�ڱ߳ɻ���
                valid = false;
                return;
            }
        }
        visited[cur] = -1;
        path[idx--] = cur;// ?���ڵ���ջ��dfs-ջ-path�Ӻ���ǰ����
    }
}
