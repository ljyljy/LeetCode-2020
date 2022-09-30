package BFS.ShortestPath;

import java.util.*;

// A����Amazon�����⣬4�ֽⷨ��˼·���Ż��� q9_1565_modern_ludo_i_SPFA_FacA.java

public class q9_1565_modern_ludo_i_FacA {
    // ��1��BFS & ���� SPFA(���·�������㷨)
    //      �Ż���q9_1565_modern_ludo_i_SPFA_FacA.java
    public int modernLudo_1(int len, int[][] conns) {
        int minDist = 0;
        Deque<Integer> deque = new ArrayDeque<>(); // todo���Ż�ΪPQ
        int[] dists = new int[len + 1]; // ������ΪNode<idx, minDist>
        for (int i = 0; i <= len; i++) {
            dists[i] = Integer.MAX_VALUE;
        }
        deque.offer(1);
        dists[1] = 0;
        while (!deque.isEmpty()) {
            int curIdx = deque.poll();
            for (int i = 1; i <= 6; i++) { // ����1�����ӵ�����[1, 6]
                int nxtIdx = curIdx + i;
                if (nxtIdx <= len && dists[nxtIdx] > dists[curIdx] + 1) {
                    dists[nxtIdx] = dists[curIdx] + 1; // ����nxtIdx(via����)����̾���
                    deque.offer(nxtIdx);
                }
            }
            // todo: ��ͼ��ֱ���ҵ���curIdx����ӱ�
            for (int[] conn : conns) { // ����2����ͨ��
                // SPFA�Ż�����������ڲ�BFS, ��Ϊ����̽ʱ�����������С�ģ�����и��¡�
                int start = conn[0], end = conn[1];
                if (curIdx == start && dists[curIdx] < dists[end]) {
                    deque.offer(end);
                    dists[end] = dists[curIdx]; // ����[ֱ��]ͨ·, [���벻��]
                }
            }
        }
        return dists[len];
    }



    // ��0��BFS+BFS
    //      ��� BFS �����·�����ڲ� BFS ����ͨ�顣
    //       �ڲ�BFS��ǿ�н��²����У������뱾��һ���Ľ�㣬�ϲ��뱾�㡿
    Map<Integer, Set<Integer>> graph = new HashMap<>(); // <idx, ��ӱ�>
    Map<Integer, Integer> distMap = new HashMap<>(); // <idx, minDist>
    
    public int modernLudo(int len, int[][] conns) {
        buildGraph(len, conns);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        distMap.put(1, 0);

        while (!deque.isEmpty()) { // BFS-1
            int curIdx = deque.poll();
            int farthest = Math.min(curIdx + 6, len);
            for (int neigh = curIdx+1; neigh <= farthest; neigh++) {
                // ����ͼ��·��Խ��������dist������
                //   �磺C-Eֱͨ����BFS��C��̽��Eʱ������++�������벻��
                //      Υ��BFS-��ͼ�£���������������Ĺ��ɣ�
                //      �����Ҫ�ڲ�BFS(��SPFA, ��̽ʱ�������·; �������н���)����������ͨ�����е��ҵ�
                //      �ڲ�BFS:��ǿ�н��²����У������뱾��һ���Ľ�㣬�ϲ��뱾�㡿
                //          �磺[2,5], [5,8], [8,10]: 2��5��8��10����ֱ��������Ϊ0������
                Set<Integer> nxtNodes = getNxtUnvisited(neigh); // BFS-2
                for (int nxt: nxtNodes) {
                    distMap.put(nxt, distMap.get(curIdx) + 1);
                    deque.offer(nxt);
                }
            }
        }
        return distMap.get(len);
    }

    // �ڲ�BFS: �����ڽӱ�graph����δ��������ֱͨ��/��ͨ��
    //   ���BFS�������������ֵһ�������·�������ϸ������һ��<=�±�������ֵ��������
    private Set<Integer> getNxtUnvisited(int node) {
        Set<Integer> nxtNodes = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(node);

        while (!deque.isEmpty()) {
            int cur = deque.poll();
            if (distMap.containsKey(cur)) continue; // BFS��α�������˵���ѵõ����·��
            nxtNodes.add(cur);
            for (int nxt: graph.get(cur)) {
                if (distMap.containsKey(nxt)) continue;
                deque.offer(nxt);
                nxtNodes.add(nxt);
            }
        }
        return nxtNodes;
    }

    private void buildGraph(int len, int[][] conns) {
        for (int i = 1; i <= len; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] conn: conns) {
            int from = conn[0], to = conn[1];
            graph.get(from).add(to);
        }
    }


    // [���Ž�]����2 - dp
    // dp�� ���е�࣬��Ϊ����connections������s -> t��Ŀ�ĵ�t��Ӧ��s�����Ƕ����
    //  ���ң����s�в���ʹ��̰���㷨ֱ��ȡ��С��s���õ���̲��������仰˵����������Ծ��ԽԶ�������ܼ��ٲ�����
    //  ����������һ��dic����t��Ӧ��sֵ���ж��sֵ�ľͷ���list���档
    // ת�Ʒ��̣�
    //      û��connectionsʱ�� dpi = min(dpi, dpi-j + 1) ����i - j > 0, jΪ1-6�����ӵ���
    //      ��connectionsʱ�� dpi = min(dpi, dpk) k -> i��Ӧ��������ʼ���λ�ã��ж��ʱ������ȡmin(dpk)

    //    Map<Integer, Set<Integer>> graph = new HashMap<>();
    public int modernLudo_dp(int len, int[][] conns) {
        buildGraph(len, conns);
        int[] dp = new int[len + 1]; // �±�idx��[1, len]
        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[len] = 0; // �յ㣬�Ӻ���ǰ����
        for (int cur = len-1; cur >= 1; cur--) {
            for (int nxt = cur+1; nxt <= cur+6 && nxt <= len; nxt++) {// ����1�����ӵ�����[1, 6]
                if (dp[nxt] == Integer.MAX_VALUE) {
                    continue; // ��ֹ����dp[nxt]+1���
                }
                dp[cur] = Math.min(dp[cur], dp[nxt]+1);
            }
            for (int nxt: graph.get(cur)) { // ����2����ͨ��
                dp[cur] = Math.min(dp[cur], dp[nxt]); // ����[ֱ��]ͨ·, [���벻��]
            }
        }
        return dp[1];
    }

    // todo: ��3 - �������н���
    //	ʹ�� BFS ʵ�����������н���ķ���
    //	��ͨ�������������ĵ������һ��������
    //	��ͨ��ֱ������ĵ���ڵ�ǰ������
    //	�����Ͳ����ƻ�ÿ������Ϊͬһ��ڵ������
    public int modernLudo_3(int length, int[][] connections) {
        buildGraph(length, connections);

        List<Integer> queue = new ArrayList<>();
        queue.add(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            List<Integer> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                for (int directNode: graph.get(node)) {
                    if (distance.containsKey(directNode)) {
                        continue;
                    }
                    distance.put(directNode, distance.get(node));
                    queue.add(directNode);
                }
            }
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                int limit = Math.min(node + 7, length + 1);
                for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                    if (distance.containsKey(nextNode)) {
                        continue;
                    }
                    distance.put(nextNode, distance.get(node) + 1);
                    nextQueue.add(nextNode);
                }
            }
            queue = nextQueue;
        }

        return distance.get(length);
    }
}
