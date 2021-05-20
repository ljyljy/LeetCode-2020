package DP;

public class hole_q1473_paint_house_iii {
    // 法1：memo + dfs
    private final int INF = 0x3f3f3f3f; // Integer.MAX_VALUE;
    private int[] hs; // 房子house[i]的颜色
    private int[][] cost;
    private int m, n, t; // 房子数(hs)、颜色数(color)、街区数(street)
    private int[][][] memo = new int[101][21][101];
    private boolean[][][] visit = new boolean[101][21][101];
    public int minCost_memo(int[] _hs, int[][] _cost, int _m, int _n, int _t) {
        hs = _hs; cost = _cost; m = _m; n = _n; t = _t; // 减少DFS传参
        int ans = dfs(0, 0, 0, 0);
        return ans == INF? -1 : ans;
    }

    // 当前遍历的房号idx，颜色last_c，街区数cntS，花费curCost
    private int dfs(int idx, int last_c, int last_cnt, int last_cost) {
        if (last_cnt > t) return INF; // 剪枝：街区数超出目标t
        if (visit[idx][last_c][last_cnt]) return memo[idx][last_c][last_cnt];
        if (idx == m) return last_cnt == t ? 0 : INF; // WHY 0？-说明找到可行解，该层花销0，返回
        int ans = INF, color = hs[idx];
        if (color == 0) { // 若颜色=0，可尝试涂其他颜色
            for (int c = 1; c <= n; c++) {
                // 1) idx=0:起始点，街区数nCnt置1；
                // 2)否则：若上层颜色last_c与本层颜色c相同，则同街区/否则街区数+1
                int cnt = idx == 0? 1: (last_c == c? last_cnt: last_cnt+1);
                int curCost = dfs(idx+1, c, cnt, last_cost + cost[idx][c-1]);
                ans = Math.min(ans, curCost + cost[idx][c-1]); // WHY 还加一次cost？
            }
        } else{
            int cnt = idx == 0? 1: (last_c == color? last_cnt: last_cnt+1);
            int curCost = dfs(idx+1, color, cnt, last_cost);
            ans = Math.min(ans, curCost);
        }
        visit[idx][last_c][last_cnt] = true;
        memo[idx][last_c][last_cnt] = ans;
        return ans;
    }

    // 法2：dp
    public int minCost(int[] hs, int[][] cost, int m, int n, int t) {
        final int INF = 0x3f3f3f3f;
        int[][][] dp = new int[m+1][n+1][t+1];
        // 1）不存在分区数量为 0 的状态
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = INF;
            }
        }
        for (int i = 1; i <= m; i++) { // 遍历房子
            int color = hs[i-1]; // 当前房子的初始颜色 0/ == j
            for (int j = 1; j <= n; j++) { // 遍历颜色
                for (int k = 1; k <= t; k++) { // 遍历街区
                    // 2）形成分区数量大于房子数量，状态无效
                    if (k > i){
                        dp[i][j][k] = INF;
                        continue;
                    }

                    // 分情况: I) 第i间房间已经上色
                    if (color != 0) {
//                        if (j != color) { // 3）状态无效，只有j=color才允许状态转移
//                            dp[i][j][k] = INF;
//                            continue;
//                        }
                        if (j == color){
                            int tmp = INF;
                            // I-1) 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                            for (int p = 1; p <= n; p++) {
                                if (p == j) continue;
                                tmp = Math.min(tmp, dp[i-1][p][k-1]);
                            }
                            // I-2) 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                            dp[i][j][k] = Math.min(tmp, dp[i-1][j][k]);
                        } else {
                            dp[i][j][k] = INF;
                        }

                    } else { // II) 第i间房间未上色 - 尝试上色，需+cost_ij
                        int cost_ij = cost[i-1][j-1];
                        int tmp = INF;
                        // II-1) 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                        for (int p = 1; p <= n; p++) {
                            if (p == j) continue;
                            tmp = Math.min(tmp, dp[i-1][p][k-1]);
                        }
                        // II-2) 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                        //                                  并累加 + [上色成本]
                        dp[i][j][k] = Math.min(tmp, dp[i-1][j][k] + cost_ij);
                    }
                }
            }
        }
        // 从「考虑所有房间m，并且形成分区数量为 t」的所有方案中找答案
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[m][i][t]);
        }
        return ans == INF ? -1 : ans;
    }
}
