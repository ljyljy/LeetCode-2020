package BFS.ShortestPath;

import java.util.*;

// A厂（Amazon）真题
public class q9_1565_modern_ludo_i_FacA {
    // 法0：BFS+BFS
    //      外层 BFS 做最短路径，内层 BFS 找连通块。
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
    //   外层BFS若遍历过，则旧值一定是最短路径，跳过
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

    // 法1：普通BFS
    public int modernLudo_1(int len, int[][] conns) {
        int minDist = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] dists = new int[len + 1]; // 不要设为Node<idx, minDist>, 否则还要遍历minDist
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
                    dists[nxtIdx] = dists[curIdx] + 1;
                    deque.offer(nxtIdx);
                }
            }
            // todo: 建图，直接找到与curIdx的领接表
            for (int k = 0; k < conns.length; k++) { // 遍历2）连通域
                int start = conns[k][0], end = conns[k][1];
                if (curIdx == start && dists[curIdx] < dists[end]) {
                    deque.offer(end);
                    dists[end] = dists[curIdx]; // 更新直连通路
                }
            }
        }
        return dists[len];
    }

    // todo：法2 - dp
    // dp： 坑有点多，因为存在connections的两地s -> t的目的地t对应的s可能是多个的
    //  而且，多个s中不能使用贪心算法直接取最小的s来得到最短步数（换句话说，就是你跳跃的越远不见得能减少步数）
    //  所以这里用一个dic来存t对应的s值，有多个s值的就放在list里面。
    // 转移方程：
    //      没有connections时： dpi = min(dpi, dpi-j + 1) 其中i - j > 0, j为1-6的骰子点数
    //      有connections时： dpi = min(dpi, dpk) k -> i对应的连接起始点的位置，有多个时，遍历取min(dpk)
    public int modernLudo_2(int length, int[][] connections) {
        // Write your code here
        if (length <= 7) {
            return 1;
        }
        int []f = new int[length + 1];
        int []dp = new int[length + 1];
        for (int i = 1; i <= length; ++i) {
            f[i] = i;
            dp[i] = Integer.MAX_VALUE;
        }
        dp[1] = 0;
        for (int i = 0; i < connections.length; ++i) {
            f[connections[i][0]] = connections[i][1];
        }
        for (int i = 2; i <= length; ++i) {
            if (i - 6 < 1) {
                dp[i] = 1;
            } else {
                for (int j = i-1; j > i-7; j--) {
                    dp[i] = Math.min(dp[j]+1, dp[i]);
                }
            }
            dp[f[i]] = Math.min(dp[i], dp[f[i]]);
        }
        return dp[length];
    }

    // todo: 法3 - 两个队列交替
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
