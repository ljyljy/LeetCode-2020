package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class hole_q1787_make_xor_all_segments_equal_to_zero {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        final int INF = 0x3f3f3f3f; // 避免最大值溢出：MAX_VALUE >> 1
        int max = 1024; // nums[i] <= 2^10
        int[][] f = new int[k][max]; // k行(组)，每行(组)最多max个元素
        int[] g = new int[k]; // 前一列(组)的最小状态值
        for (int i = 0; i < k; i++) {
            Arrays.fill(f[i], INF); // 对每一行都赋值INF
            g[i] = INF;
        }
        for (int i = 0, cnt_j = 0; i < k; i++, cnt_j=0) { // i：第i列(组)，共k列/组
            // 使用 map 和 cnt_j 分别统计第i列/组 的「每个数的出现次数<某数字，频数>」和「有多少个数(该列有几个/行元素)」
            Map<Integer, Integer> map = new HashMap<>();
            // 统计【当前列(组)】的<数字, 频次>
            for (int j = i; j < n; j+=k) { // j+=k：每次下跳一"行"/该列(组)的下一个元素
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt_j++; // 当前列(组)的元素个数
            }
            if (i == 0) { // (初始化)第 0 列：只需要考虑如何将该列变为 xor 即可
                // (计算改为xor的最小修改次数，即统计第0列 非xor的元素个数)
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt_j - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]); // 第0列的最小修改次数
                }
            } else { // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    f[i][xor] = g[i-1] + cnt_j; // 整列替换，cnt_j：该列数字总数
                    for (int cur : map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor],
                                             f[i-1][xor ^ cur] + cnt_j - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }

        }
        return f[k-1][0];
    }
}
