package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q256_paint_house_i {
    // 法3: 记忆化dfs+memo
    private int[][] costs; // 设置类成员变量，减少dp传参
    private Map<String, Integer> memo; // <“i号房_0/1/2(油漆颜色)”, 当前minCost>
    public int minCost3(int[][] costs) {
        if(costs.length == 0) return 0;
        memo = new HashMap<>();
        this.costs = costs;
        return Math.min(dfs(0, 0),
                Math.min(dfs(0, 1), dfs(0, 2)));
    }

    private int dfs(int idx, int color) { // 房号idx, 颜色0/1/2
        String key = idx + "_" + color;
        if (memo.containsKey(key)) return memo.get(key);
        int cost = costs[idx][color];
        if (idx != costs.length - 1) {
            // 下探一层(idx+1): 相邻房子不可同色
            if (color == 0)
                cost += Math.min(dfs(idx+1, 1), dfs(idx+1, 2));
            else if (color == 1)
                cost += Math.min(dfs(idx+1, 0), dfs(idx+1, 2));
            else if (color == 2)
                cost += Math.min(dfs(idx+1, 0), dfs(idx+1, 1));
        }
        memo.put(key, cost);
        return memo.get(key); // cost
    }

    // 法4-1: 动归dp -- 时间O(n), 空间O(n*m) - n:房子数，m：颜色数
    public int minCost4_1(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length;
        for (int i = n-2; i >= 0; i--) {
            // 从倒数第二排开始赋值、向前计算(利用倒数第一排)
            costs[i][0] += Math.min(costs[i+1][1], costs[i+1][2]);
            costs[i][1] += Math.min(costs[i+1][0], costs[i+1][2]);
            costs[i][2] += Math.min(costs[i+1][0], costs[i+1][1]);
        }
        return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
    }

    // 法4-2: 动归dp【滚动数组优化】-- 时间O(n), 空间O(mod*m) - m：颜色数
    public int minCost4_2(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length, m = costs[0].length;
        int mod = 2;
        int[][] dp = new int[mod][m];
        dp[(n-1) % mod] = costs[n-1];

        for (int i = n-2; i >= 0; i--) {
            dp[i % mod] = costs[i]; // 初始化，首先赋值为第i号房子的成本(本层cost), ↓再加上之前计算的minCost
            dp[i % mod][0] += Math.min(dp[(i+1) % mod][1], dp[(i+1) % mod][2]);
            dp[i % mod][1] += Math.min(dp[(i+1) % mod][0], dp[(i+1) % mod][2]);
            dp[i % mod][2] += Math.min(dp[(i+1) % mod][0], dp[(i+1) % mod][1]);
        }
        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }

    // 法4-3【推荐，与q265相同】: 动归dp【滚动数组优化】 -- 时间O(n), 空间O(2*m) - m：颜色数
    public int minCost(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length, m = costs[0].length;
        int mod = 2;
        int[][] dp = new int[mod][m];
        dp[0] = costs[0];

        for (int i = 1; i < n; i++) { // 当前房子i
            Arrays.fill(dp[i % mod], Integer.MAX_VALUE); ; // 初始化dp：一整行为INF
            for (int j = 0; j < m; j++) { // 当前颜色j
                for (int k = 0; k < m; k++) { // 前一个相邻颜色 (k ≠ j)
                    if (j == k) continue;
                    dp[i % mod][j] = Math.min(dp[i % mod][j], dp[(i-1) % mod][k] + costs[i][j]);
                }
            }
        }
        return Arrays.stream(dp[(n-1) % mod]).min().getAsInt();
    }

    public static void main(String[] args) {
        q256_paint_house_i sol = new q256_paint_house_i();
        int[][] costs = {{17,2,17}, {16,16,5}, {14,3,19}};
        int res = sol.minCost3(costs);
        System.out.println(res);
    }
}
