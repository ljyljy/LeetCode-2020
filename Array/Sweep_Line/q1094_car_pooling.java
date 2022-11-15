package Array.Sweep_Line;

import java.util.Map;
import java.util.TreeMap;

public class q1094_car_pooling {
    // 类比：扫描线q218，1109
    // 法1：差分数组
    public boolean carPooling(int[][] trips, int capacity) {
        int[] sum = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
            sum[t_start] += cnt;
            sum[t_end] -= cnt;
        }
        int totalWeight = 0;
        for (int i = 0; i < 1001; i++) {
            totalWeight += sum[i];
            if (totalWeight > capacity) {
                return false;
            }
        }
        return true;
    }


    // 法2：前缀和 + 小根堆/TreeMap - TreeMap<time(升序), cnt乘客数>
    //      trips[cnt, time_start(+), time_end(-)]
    public boolean carPooling2(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>(); // 默认：以key升序排序
        for (int[] trip : trips) {
            int cnt = trip[0], t_start = trip[1], t_end = trip[2];
            tmap.put(t_start, tmap.getOrDefault(t_start, 0) + cnt);
            tmap.put(t_end, tmap.getOrDefault(t_end, 0) - cnt);
        }
        int totalCnt = 0;
        for (Map.Entry<Integer, Integer> entry : tmap.entrySet()) {
            totalCnt += entry.getValue(); // 求前缀和
            if (totalCnt > capacity) {
                return false;
            }
        }
        return true;
    }

    // c-法1：差分数组！
    // C-法2：扫描线 = TreeMap / 小根堆 + 前缀和【类比：q218扫描线】
    //      构造map、对key升序（HASH_SORT--q451）等操作
}
