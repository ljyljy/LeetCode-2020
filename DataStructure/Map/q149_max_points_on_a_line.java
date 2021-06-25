package DataStructure.Map;

import java.util.HashMap;
import java.util.Map;

public class q149_max_points_on_a_line {
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
