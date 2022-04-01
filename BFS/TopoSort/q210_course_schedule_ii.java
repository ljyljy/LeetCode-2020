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
}
