package BFS.ShortestPath;

import java.util.*;

/**
 思路：
     如何找到一步能跳到的所有点？—— BFS!
     ?	将直通关系看做图中的边
     ?	所有和 2 连通的点都是可以通过 2 一步跳到的【内层BFS】
     ?	找连通块问题就是 BFS 的功能

     方法2： BFS+BFS
     ?	外层 BFS 做最短路径
     ?	内层 BFS 找连通块
         ?	将直通关系 connections 构图（邻接表）
             ?	Python: dict{node: set(neighbors)}
             ?	Java: HashMap<Node, HashSet<Node>>
         ?	邻接表 (Adjacency List)
             ?	本意是用 List 存储 neighbors 的
             ?	但是实战中用 Set 还可以去重和 O(1) 查询 a, b 两个点是否有连边

     方法3： 两个队列交替（todo）
     ?	使用 BFS 实现中两个队列交替的方法
     ?	将通过掷骰子跳到的点放在下一个队列里
     ?	将通过直连到达的点放在当前队列里
     ?	这样就不会破坏每个队列为同一层节点的属性
         ?	意思是：BFS-简单图下，层数↑，距离↑的规律！
         ?	解决：
             ?	外层BFS + 内层BFS【强行将下层结点中，距离与本层一样的结点，合并入本层】
             ?	SPFA, 下探时更新最短路，将本层连通的所有点找到
             ?	两个队列交替

     方法3： SPFA
     ?	SPFA = Shortest Path Fast Algorithm
     ?	这个算法实际上是基于 BFS 算法的一个拓展
     ?	基础BFS 算法求最短路要求简单图
     ?	SPFA 可以解决非简单图的最短路径
     ?	问： 为什么 BFS 不能解决复杂图？
         ?	简单图中： 第几层访问到该节点=到达该节点的最短路径
         ?	复杂图中： 可能通过三层访问到比通过两层访问到的路径更短

     ?	SPFA 的解决方式复杂图中访问层级和最短路径不匹配的办法是:
         ?	如果我在第三层中发现一个第二层中访问过的节点
         ?	但是此时找到的路径更短
         ?	就丢回队列——再从这个节点出发往下拓展
     ?	SPFA: 一个节点是否被扔进队列的判断标准发生变化：
         ?	简单图： 没有访问过的点就扔进队列
         ?	复杂图： 如果到达该点的路径变短了就扔进队列
             ?	优化：SPFA 里会使用 Heap(PriorityQueue) 来替换 Queue，这样能够更快的找到最短路径（类比：A*、Dijkstra算法）
     ?	SPFA 小结
         ?	整体依然是一个 BFS 算法的轮廓
         ?	变化1: 一个点可以重复进入队列, 只要发现更短的路径
         ?	变化2: Queue 使用 heapq/PriorityQueue 替代

     方法4： 动态规划(DP)
     ?	找最短路径也在动态规划的魔爪范围内
     ?	由于规定了跳跃的方向性（从左到右）
     ?	可以使用坐标型动态规划自底向上（从右至左） 来计算
         ?	state: dp[i] 表示从 i 出发跳到终点需要最少多少步
         ?	function:
             ?	dp[i] = min(dp[i], dp[j] + 1(by投骰子) || dp[j](by连通域))
         ?	j 是 i 可以跳到(骰子)或者直通的点, 跳过去的话步数+1， 直通的话步数 + 0
         ?	initialization: dp[1..n-1] = INT_MAX, dp[n] = 0
         ?	answer: dp[1]

 **/
public class q9_1565_modern_ludo_i_SPFA_FacA {
    // 【荐，避免两层BFS！】 法1：SPFA(最短路径快速算法)
    Map<Integer, Set<Integer>> graph = new HashMap<>(); // C使用二维数组：int[起点i][m]=终点集j, j∈[0,m-1]
    Map<Integer, Integer> distMap = new HashMap<>();

    public int modernLudo_SPFA1(int len, int[][] conns) {
        buildGraph(len, conns);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1); // 起点
        for (int i = 1; i <= len; i++) {
            distMap.put(i, Integer.MAX_VALUE);
        }
        distMap.put(1, 0);

        while (!deque.isEmpty()) {
            int cur = deque.poll();
            for (int i = 1; i <= 6; i++) { // 遍历1）骰子点数∈[1, 6]
                int nxtIdx = cur + i;
                if (nxtIdx > len) break;
                if (distMap.get(nxtIdx) > distMap.get(cur) + 1) {
                    deque.offer(nxtIdx);
                    distMap.put(nxtIdx, distMap.get(cur) + 1); // 更新nxtIdx更短距离(curDist+1)
                }
            }
            for (int nxtIdx: graph.get(cur)) { // 遍历2）连通域
                // SPFA优化：无需进行内层BFS, 因为在下探时若碰到距离更小的，则进行更新。
                if (distMap.get(nxtIdx) > distMap.get(cur)) {
                    deque.offer(nxtIdx);
                    distMap.put(nxtIdx, distMap.get(cur)); // 更新[直连]通路, [距离不变]
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

    // 法1-2：SPFAv2，堆优化
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
         // 类比：A*、Dijkstra中，堆的应用
         Queue<Node> minHeap = new PriorityQueue<>(((o1, o2) -> (o1.dist - o2.dist)));
         minHeap.offer(new Node(1, 0));
        for (int i = 1; i <= len; i++) {
            distMap.put(i, Integer.MAX_VALUE);
        }
        distMap.put(1, 0);

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int curIdx = node.idx, curDist = node.dist;
            for (int i = 1; i <= 6; i++) {// 遍历1）骰子点数∈[1, 6]
                int nxtIdx = curIdx + i;
                if (nxtIdx > len) break;
                if (distMap.get(nxtIdx) > curDist + 1) {
                    distMap.put(nxtIdx, curDist + 1);
                    minHeap.offer(new Node(nxtIdx, curDist + 1));
                }// 更新nxtIdx更短距离(curDist+1) ↑
            }

            for (int nxtIdx: graph.get(curIdx)) { // 遍历2）连通域
                // SPFA优化：无需进行内层BFS, 因为在下探时若碰到距离更小的，则进行更新。
                if (distMap.get(nxtIdx) > curDist) {
                    distMap.put(nxtIdx, curDist);
                    minHeap.offer(new Node(nxtIdx, curDist)); // 更新[直连]通路, [距离不变]
                }
            }
        }
        return distMap.get(len);
    }
}