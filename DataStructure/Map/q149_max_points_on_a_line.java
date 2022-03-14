package DataStructure.Map;

import java.util.HashMap;
import java.util.Map;

public class q149_max_points_on_a_line {
    // 哈希+优化最简斜率String -- GCD最大公约数，化简斜率！
    public int maxPoints1(int[][] points) {
        int n = points.length;
        if (n < 3) return n;
        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>(); // <最简斜率，cnt>
            int curMax = 0;// 经过点i的最多"相同斜率数"

            int x0 = points[i][0], y0 = points[i][1];
            for (int j = i+1; j < n; j++) {
                int x1 = points[j][0], y1 = points[j][1];
                int delta_x = x0 - x1, delta_y = y0 - y1;
                int gcd0 = gcd(delta_y, delta_x); // 斜率=△y/△x, 但精度不准
                String slope = (delta_y / gcd0) + "_" + (delta_x / gcd0);

                int curCnt = map.getOrDefault(slope, 0)+1;
                map.put(slope, map.getOrDefault(slope, 0)+1);
                curMax = Math.max(curMax, curCnt); // 相同斜率数量
            }
            maxCnt = Math.max(maxCnt, curMax + 1); // 直线上的点数 = 相同斜率数+1(点i)
        }

        return maxCnt;
    }

    // 如：18, 24 -> 24, 18 -> 18, 6 -> 6, 18%6=0 -> 0, 6%0
    private int gcd(int x, int y) {
        return y == 0? x: gcd(y, x % y); // x%y: y不可为0!
    }

    // 法2：未优化斜率精度
    private final double MAX = Double.MAX_VALUE;
    public int maxPoints(int[][] points) {
        if (points.length < 3) return points.length;
        int res = 1; // i点自身

        for (int i = 0; i < points.length; i++) {
            // 记录除了当前i点（via外层遍历）之外，每个点的斜率
            Map<Double, Integer> slopeCntMap = new HashMap<>(); // <斜率，点的数量>
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                // 记录与i斜率无穷大的点数（横坐标相同）
                if (points[j][0] == points[i][0]){
                    slopeCntMap.put(MAX, slopeCntMap.getOrDefault(MAX, 0)+1);
                    continue;
                }
                // 记录其他斜率（非无穷大）的点
                double slope =  (double)(points[i][1] - points[j][1]) /
                        (double)(points[i][0] - points[j][0]);
                slopeCntMap.put(slope, slopeCntMap.getOrDefault(slope, 0)+1);
            }

            int maxCnt = 0;
            for (Map.Entry<Double, Integer> entry : slopeCntMap.entrySet()) {
                maxCnt = Math.max(maxCnt, entry.getValue());
            }
            res = Math.max(res, maxCnt + 1); // 勿忘i点自身！还需加1！
        }
        return res;
    }
}
