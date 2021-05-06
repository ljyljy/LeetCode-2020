package DP;

import java.util.HashMap;
import java.util.Map;

public class q256_paint_house_i {
    private int[][] costs; // 设置类成员变量，减少dp传参
    private Map<String, Integer> memo; // <“i号房_0/1/2(油漆颜色)”, 当前minCost>
    public int minCost(int[][] costs) {
        if(costs.length == 0) return 0;
        memo = new HashMap<>();
        this.costs = costs;
        return Math.min(dp(0, 0),
                Math.min(dp(0, 1),dp(0, 2)));
    }

    private int dp(int idx, int color) { // 房号idx, 颜色0/1/2
        String key = idx + "_" + color;
        if (memo.containsKey(key)) return memo.get(key);
        int cost = costs[idx][color];
        if (idx != costs.length - 1) {
            // 下探一层(idx+1): 相邻房子不可同色
            if (color == 0)
                cost += Math.min(dp(idx+1, 1), dp(idx+1, 2));
            else if (color == 1)
                cost += Math.min(dp(idx+1, 0), dp(idx+1, 2));
            else if (color == 2)
                cost += Math.min(dp(idx+1, 0), dp(idx+1, 1));
        }
        memo.put(key, cost);
        return memo.get(key); // cost
    }
}
