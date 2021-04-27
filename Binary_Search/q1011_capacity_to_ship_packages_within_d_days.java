package Binary_Search;

import javax.print.DocFlavor;
import java.util.Arrays;

public class q1011_capacity_to_ship_packages_within_d_days {
    public int shipWithinDays(int[] weights, int D) {
        int start = Arrays.stream(weights).max().getAsInt(),
                end = Arrays.stream(weights).sum();
        while (start < end) { // [start, mid], [mid+1, end]
            int mid = start + end >> 1;// 单日运输总重【下限】(所求)
            int D0 = 1, // max{可运送的天数} <= D, 与mid反比
                cur_w = 0; // 当天已运送的重量和 <= mid
            for (int weight: weights) {
                if (cur_w + weight > mid) {
                    // 超过单日最低运载能力(mid),
                    D0++; // 天数++，新启一天
                    cur_w = 0; // 重置0
                }
                cur_w += weight;
            }
            if (D0 <= D) end = mid;// ❤未超过D，说明D0↑ -- mid↓(左区间)
            else start = mid + 1;
        }
        return start; // 退出时，start == end == mid(所求)
    }
}
