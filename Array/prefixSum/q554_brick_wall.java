package Array.prefixSum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q554_brick_wall {
    public int leastBricks(List<List<Integer>> wall) {
        int n_row = wall.size();
        Map<Integer, Integer> map = new HashMap<>(); // <间隙idx, 次数cnt>
        for (int i = 0; i < n_row; i++) {
            int prefix_sum = 0;
            // 写法2：for (int j = 0; j < wall.get(i).size(); j++)
            for (int curBrickLen: wall.get(i)) { // 第i行的砖头
                prefix_sum += curBrickLen;
                map.put(prefix_sum, map.getOrDefault(prefix_sum, 0) + 1);
                // 写法2：
                // if (map.containsKey(prefix_sum)) {
                //     map.put(prefix_sum, map.get(prefix_sum)+1);
                // }else map.put(prefix_sum, 1);
            }
            map.remove(prefix_sum); // 由题意, 首尾缝隙不能算（去除尾缝隙）
        }
        int maxgapCnt = 0, ans = n_row;
        // 遍历map，找到最高频的缝隙idx
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > maxgapCnt) {
                maxgapCnt = entry.getValue();
                ans = n_row - maxgapCnt;
            }
        }
        return ans;
    }
}
