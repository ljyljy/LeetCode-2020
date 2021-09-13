package BFS.TopoSort;

import java.util.*;

public class q802_find_eventual_safe_states {
    private List<List<Integer>> newG = new ArrayList<>(); // 【反向图】<入度=原图出度>
    private int[] inDeg; // 每个点の【入度】<对应原图の出度>
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        inDeg = new int[n];
        // 反向图 & 入度の初始化
        setGraph(graph, n);
        // 拓扑排序
        List<Integer> res = topoSort(n);
        Collections.sort(res);
        return res;
    }

    private void setGraph(int[][] graph, int n) {
        for (int i = 0; i < n; i++) {
            newG.add(new ArrayList<>()); // 初始化
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                newG.get(graph[i][j]).add(i); // 原图<dst, <src>>
            }
            inDeg[i] = graph[i].length; // 反向图的入度 = 原图出度
        }
    }

    private List<Integer> topoSort(int n) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deq = new ArrayDeque<>();

        // 1. (反向图)入度为0，加入队列
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) deq.offer(i);
        }

        while (!deq.isEmpty()) {
            int dst0 = deq.poll();
            res.add(dst0); // 法1
            for (int src0 : newG.get(dst0)) { // 反图newG = 原图<dst, <src>>
                // 将以其为起点(dst0)的有向边删除，更新终点(src0)入度
                inDeg[src0]--;
                if (inDeg[src0] == 0) // 入度为0，加入队列
                    deq.offer(src0); // 法2：接着, res.add(src0);
            }
        }
        return res;
    }
}
