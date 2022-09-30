package BFS.ShortestPath;

import java.util.*;

public class q9_1565_modern_ludo_i_SPFA_FacA {
    // ��������������BFS���� ��1��SPFA(���·�������㷨)
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> distMap = new HashMap<>();

    public int modernLudo_SPFA1(int len, int[][] conns) {
        buildGraph(len, conns);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1); // ���
        for (int i = 1; i <= len; i++) {
            distMap.put(i, Integer.MAX_VALUE);
        }
        distMap.put(1, 0);

        while (!deque.isEmpty()) {
            int cur = deque.poll();
            for (int i = 1; i <= 6; i++) { // ����1�����ӵ�����[1, 6]
                int nxtIdx = cur + i;
                if (nxtIdx > len) break;
                if (distMap.get(nxtIdx) > distMap.get(cur) + 1) {
                    deque.offer(nxtIdx);
                    distMap.put(nxtIdx, distMap.get(cur) + 1); // ����nxtIdx���̾���(curDist+1)
                }
            }
            for (int nxtIdx: graph.get(cur)) { // ����2����ͨ��
                // SPFA�Ż�����������ڲ�BFS, ��Ϊ����̽ʱ�����������С�ģ�����и��¡�
                if (distMap.get(nxtIdx) > distMap.get(cur)) {
                    deque.offer(nxtIdx);
                    distMap.put(nxtIdx, distMap.get(cur)); // ����[ֱ��]ͨ·, [���벻��]
                }
            }
        }
        return distMap.get(len);
    }

    private void buildGraph(int len, int[][] conns) {
        for (int i = 1; i <= len; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] conn: conns) {
            int start = conn[0], end = conn[1];
            graph.get(start).add(end);
        }
    }

    // ��1-2��SPFAv2�����Ż�
    class Node {
        int idx, dist;
        public Node (int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

//    Map<Integer, Set<Integer>> graph = new HashMap<>();
//    Map<Integer, Integer> distMap = new HashMap<>();
    public int modernLudo(int len, int[][] conns) {
         buildGraph(len, conns);
         // ��ȣ�A*��Dijkstra�У��ѵ�Ӧ��
         Queue<Node> minHeap = new PriorityQueue<>(((o1, o2) -> (o1.dist - o2.dist)));
         minHeap.offer(new Node(1, 0));
        for (int i = 1; i <= len; i++) {
            distMap.put(i, Integer.MAX_VALUE);
        }
        distMap.put(1, 0);

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int curIdx = node.idx, curDist = node.dist;
            for (int i = 1; i <= 6; i++) {// ����1�����ӵ�����[1, 6]
                int nxtIdx = curIdx + i;
                if (nxtIdx > len) break;
                if (distMap.get(nxtIdx) > curDist + 1) {
                    distMap.put(nxtIdx, curDist + 1);
                    minHeap.offer(new Node(nxtIdx, curDist + 1));
                }// ����nxtIdx���̾���(curDist+1) ��
            }

            for (int nxtIdx: graph.get(curIdx)) { // ����2����ͨ��
                // SPFA�Ż�����������ڲ�BFS, ��Ϊ����̽ʱ�����������С�ģ�����и��¡�
                if (distMap.get(nxtIdx) > curDist) {
                    distMap.put(nxtIdx, curDist);
                    minHeap.offer(new Node(nxtIdx, curDist)); // ����[ֱ��]ͨ·, [���벻��]
                }
            }
        }
        return distMap.get(len);
    }
}