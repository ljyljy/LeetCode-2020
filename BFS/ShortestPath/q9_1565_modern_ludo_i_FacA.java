package BFS.ShortestPath;

import java.util.*;

// A厂（Amazon）真题，4种解法，思路、优化见 q9_1565_modern_ludo_i_SPFA_FacA.java

public class q9_1565_modern_ludo_i_FacA {
    // 法1：BFS & 简易 SPFA(最短路径快速算法)
    //      优化见q9_1565_modern_ludo_i_SPFA_FacA.java
    public int modernLudo_1(int len, int[][] conns) {
        int minDist = 0;
        Deque<Integer> deque = new ArrayDeque<>(); // todo：优化为PQ
        int[] dists = new int[len + 1]; // 无需设为Node<idx, minDist>
        for (int i = 0; i <= len; i++) {
            dists[i] = Integer.MAX_VALUE;
        }
        deque.offer(1);
        dists[1] = 0;
        while (!deque.isEmpty()) {
            int curIdx = deque.poll();
            for (int i = 1; i <= 6; i++) { // 遍历1）骰子点数∈[1, 6]
                int nxtIdx = curIdx + i;
                if (nxtIdx <= len && dists[nxtIdx] > dists[curIdx] + 1) {
                    dists[nxtIdx] = dists[curIdx] + 1; // 更新nxtIdx(via骰子)的最短距离
                    deque.offer(nxtIdx);
                }
            }
            // todo: 建图，直接找到与curIdx的领接表
            for (int[] conn : conns) { // 遍历2）连通域
                // SPFA优化：无需进行内层BFS, 因为在下探时若碰到距离更小的，则进行更新。
                int start = conn[0], end = conn[1];
                if (curIdx == start && dists[curIdx] < dists[end]) {
                    deque.offer(end);
                    dists[end] = dists[curIdx]; // 更新[直连]通路, [距离不变]
                }
            }
        }
        return dists[len];
    }



    // 法0：BFS+BFS
    //      外层 BFS 做最短路径，内层 BFS 找连通块。
    //       内层BFS【强行将下层结点中，距离与本层一样的结点，合并入本层】
    Map<Integer, Set<Integer>> graph = new HashMap<>(); // <idx, 领接表>
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
                // 复杂图：路径越长，可能dist不递增
                //   如：C-E直通，则BFS从C下探到E时，层数++，但距离不变
                //      违反BFS-简单图下，层数↑，距离↑的规律！
                //      因此需要内层BFS(或SPFA, 下探时更新最短路; 或两队列交替)，将本层连通的所有点找到
                //      内层BFS:【强行将下层结点中，距离与本层一样的结点，合并入本层】
                //          如：[2,5], [5,8], [8,10]: 2与5、8、10都是直连，距离为0！！！
                Set<Integer> nxtNodes = getNxtUnvisited(neigh); // BFS-2
                for (int nxt: nxtNodes) {
                    distMap.put(nxt, distMap.get(curIdx) + 1);
                    deque.offer(nxt);
                }
            }
        }
        return distMap.get(len);
    }

    // 内层BFS: 搜索邻接表graph所有未遍历过的直通域/连通域
    //   外层BFS若遍历过，则旧值一定是最短路径（非严格递增，一定<=新遍历到的值），跳过
    private Set<Integer> getNxtUnvisited(int node) {
        Set<Integer> nxtNodes = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(node);

        while (!deque.isEmpty()) {
            int cur = deque.poll();
            if (distMap.containsKey(cur)) continue; // BFS层次遍历过，说明已得到最短路径
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


    // [最优解]：法2 - dp
    // dp： 坑有点多，因为存在connections的两地s -> t的目的地t对应的s可能是多个的
    //  而且，多个s中不能使用贪心算法直接取最小的s来得到最短步数（换句话说，就是你跳跃的越远不见得能减少步数）
    //  所以这里用一个dic来存t对应的s值，有多个s值的就放在list里面。
    // 转移方程：
    //      没有connections时： dpi = min(dpi, dpi-j + 1) 其中i - j > 0, j为1-6的骰子点数
    //      有connections时： dpi = min(dpi, dpk) k -> i对应的连接起始点的位置，有多个时，遍历取min(dpk)

    //    Map<Integer, Set<Integer>> graph = new HashMap<>();
    public int modernLudo_dp(int len, int[][] conns) {
        buildGraph(len, conns);
        int[] dp = new int[len + 1]; // 下标idx∈[1, len]
        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[len] = 0; // 终点，从后往前倒推
        for (int cur = len-1; cur >= 1; cur--) {
            for (int nxt = cur+1; nxt <= cur+6 && nxt <= len; nxt++) {// 遍历1）骰子点数∈[1, 6]
                if (dp[nxt] == Integer.MAX_VALUE) {
                    continue; // 防止下面dp[nxt]+1溢出
                }
                dp[cur] = Math.min(dp[cur], dp[nxt]+1);
            }
            for (int nxt: graph.get(cur)) { // 遍历2）连通域
                dp[cur] = Math.min(dp[cur], dp[nxt]); // 更新[直连]通路, [距离不变]
            }
        }
        return dp[1];
    }

    // todo: 法3 - 两个队列交替
    //	使用 BFS 实现中两个队列交替的方法
    //	将通过掷骰子跳到的点放在下一个队列里
    //	将通过直连到达的点放在当前队列里
    //	这样就不会破坏每个队列为同一层节点的属性
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
