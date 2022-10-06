package BFS.ShortestPath;

import java.util.*;

/**
 ˼·��
     ����ҵ�һ�������������е㣿���� BFS!
     ?	��ֱͨ��ϵ����ͼ�еı�
     ?	���к� 2 ��ͨ�ĵ㶼�ǿ���ͨ�� 2 һ�������ġ��ڲ�BFS��
     ?	����ͨ��������� BFS �Ĺ���

     ����2�� BFS+BFS
     ?	��� BFS �����·��
     ?	�ڲ� BFS ����ͨ��
         ?	��ֱͨ��ϵ connections ��ͼ���ڽӱ�
             ?	Python: dict{node: set(neighbors)}
             ?	Java: HashMap<Node, HashSet<Node>>
         ?	�ڽӱ� (Adjacency List)
             ?	�������� List �洢 neighbors ��
             ?	����ʵս���� Set ������ȥ�غ� O(1) ��ѯ a, b �������Ƿ�������

     ����3�� �������н��棨todo��
     ?	ʹ�� BFS ʵ�����������н���ķ���
     ?	��ͨ�������������ĵ������һ��������
     ?	��ͨ��ֱ������ĵ���ڵ�ǰ������
     ?	�����Ͳ����ƻ�ÿ������Ϊͬһ��ڵ������
         ?	��˼�ǣ�BFS-��ͼ�£���������������Ĺ��ɣ�
         ?	�����
             ?	���BFS + �ڲ�BFS��ǿ�н��²����У������뱾��һ���Ľ�㣬�ϲ��뱾�㡿
             ?	SPFA, ��̽ʱ�������·����������ͨ�����е��ҵ�
             ?	�������н���

     ����3�� SPFA
     ?	SPFA = Shortest Path Fast Algorithm
     ?	����㷨ʵ�����ǻ��� BFS �㷨��һ����չ
     ?	����BFS �㷨�����·Ҫ���ͼ
     ?	SPFA ���Խ���Ǽ�ͼ�����·��
     ?	�ʣ� Ϊʲô BFS ���ܽ������ͼ��
         ?	��ͼ�У� �ڼ�����ʵ��ýڵ�=����ýڵ�����·��
         ?	����ͼ�У� ����ͨ��������ʵ���ͨ��������ʵ���·������

     ?	SPFA �Ľ����ʽ����ͼ�з��ʲ㼶�����·����ƥ��İ취��:
         ?	������ڵ������з���һ���ڶ����з��ʹ��Ľڵ�
         ?	���Ǵ�ʱ�ҵ���·������
         ?	�Ͷ��ض��С����ٴ�����ڵ����������չ
     ?	SPFA: һ���ڵ��Ƿ��ӽ����е��жϱ�׼�����仯��
         ?	��ͼ�� û�з��ʹ��ĵ���ӽ�����
         ?	����ͼ�� �������õ��·������˾��ӽ�����
             ?	�Ż���SPFA ���ʹ�� Heap(PriorityQueue) ���滻 Queue�������ܹ�������ҵ����·������ȣ�A*��Dijkstra�㷨��
     ?	SPFA С��
         ?	������Ȼ��һ�� BFS �㷨������
         ?	�仯1: һ��������ظ��������, ֻҪ���ָ��̵�·��
         ?	�仯2: Queue ʹ�� heapq/PriorityQueue ���

     ����4�� ��̬�滮(DP)
     ?	�����·��Ҳ�ڶ�̬�滮��ħצ��Χ��
     ?	���ڹ涨����Ծ�ķ����ԣ������ң�
     ?	����ʹ�������Ͷ�̬�滮�Ե����ϣ��������� ������
         ?	state: dp[i] ��ʾ�� i ���������յ���Ҫ���ٶ��ٲ�
         ?	function:
             ?	dp[i] = min(dp[i], dp[j] + 1(byͶ����) || dp[j](by��ͨ��))
         ?	j �� i ��������(����)����ֱͨ�ĵ�, ����ȥ�Ļ�����+1�� ֱͨ�Ļ����� + 0
         ?	initialization: dp[1..n-1] = INT_MAX, dp[n] = 0
         ?	answer: dp[1]

 **/
public class q9_1565_modern_ludo_i_SPFA_FacA {
    // ��������������BFS���� ��1��SPFA(���·�������㷨)
    Map<Integer, Set<Integer>> graph = new HashMap<>(); // Cʹ�ö�ά���飺int[���i][m]=�յ㼯j, j��[0,m-1]
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