package DP;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LCP_07_transfer_msg {
    private int n;
    private int cnt;
    private List<List<Integer>> edges;
    // 法1：dfs - 时间O(n^k) -- 深度k，每层最多n结点
    // 空间 O(n+m+k)
    public int numWays_dfs(int n, int[][] relation, int k) {
        cnt = 0;
        this.n = n;
        // idx=src, List.get(src)=list(dst)
        setEdges(relation);
        // Map<String, Integer> map = new HashMap<>(); // <"idx_step", cnt方案数>
        // cnt = dfs_memo(0, k, map);
        dfs(0, k);
        return cnt;
    }

    private void dfs(int idx, int steps) {
        if (steps == 0) { // 路径长度为k
            if (idx == n-1) cnt++; // 末结点为n-1
            return; // 不可放入内嵌if！
        }

        for (int nxtIdx: edges.get(idx)) {
            dfs(nxtIdx, steps-1);
        }
    }

    private void setEdges(int[][] relation) {
        this.edges = new ArrayList<>();
        for (int i = 0; i < n; i++) // edges的每个src初始化
            edges.add(new ArrayList<>());
        for (int[] edge: relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }
    }

    // 法2：bfs - 时间O(n^k)
    public int numWays_bfs(int n, int[][] relation, int k) {
        cnt = 0;
        this.n = n;
        // idx=src, List.get(src)=list(dst)
        setEdges(relation);
        bfs_helper(k);
        return cnt;
    }

    private void bfs_helper(int step) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty() && step > 0) {
            step--; // 下探一层（路径长度++ || 剩余长度k--）
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int src = queue.poll();
                List<Integer> nxtIdxList = edges.get(src);
                for (int nxtIdx : nxtIdxList) {
                    queue.offer(nxtIdx); // 下一层结点
                }
            }
        }// 第k-1层遍历结束, 退出时k=0, 且第k层结点已添加完毕。
        if (step == 0) {
            while (!queue.isEmpty()) {
                // 方案数cnt为BFS第k层结点中，所有idx=终点n-1的个数
                if (queue.poll() == n-1)
                    cnt++;
            }
        }
    }

    // 法3：二维dp - 时间O(n*k)
    public int numWays_dp2(int n, int[][] relation, int k) {
        setEdges(relation);

        int[][] dp = new int[k+1][n]; // <step/0~k轮, idx/0~n-1点>
        dp[0][0] = 1; // dp[0][1:]=0
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int nxt : edges.get(j))
                    dp[i][nxt] += dp[i-1][j];
            }
        }
        return dp[k][n-1];
    }

    // 法4：一维dp - 时间O(k*len(relation))
    public int numWays(int n, int[][] relation, int k) {
        setEdges(relation);

        int[] dp = new int[n]; // <step/0~k轮, idx/0~n-1点>
        dp[0] = 1; // dp[0][1:]=0
        for (int i = 1; i <= k; i++) {
            int[] next = new int[n];
            for (int j = 0; j < n; j++) {
                for (int nxt : edges.get(j))
                    next[nxt] += dp[j];
            }
            dp = next;
        }
        return dp[n-1];
    }

}
