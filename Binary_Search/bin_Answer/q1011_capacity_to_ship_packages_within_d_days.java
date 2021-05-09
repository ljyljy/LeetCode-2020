package Binary_Search.bin_Answer;

import javax.print.DocFlavor;
import java.util.Arrays;

public class q1011_capacity_to_ship_packages_within_d_days {
    public int shipWithinDays0(int[] weights, int D_days) {
        int start = Arrays.stream(weights).max().getAsInt(); // 最重货物不可切割
        int end = Arrays.stream(weights).sum();
        while (start < end) { // [start, mid], [mid+1, end]
            int mid = start + end >> 1;// min{单日运输最大载重(上限)}(所求)
            int tryDays = 1, // max{可运送的天数} <= D, 与mid反比
                cur_w = 0; // 当天已运送的重量和 <= mid
            for (int weight: weights) {
                if (cur_w + weight > mid) {
                    // 超过单日最大运载能力(mid),
                    tryDays++; // 天数++，新启一天
                    cur_w = 0; // 重置0
                }
                cur_w += weight;
            }
            // ❤未超过D，可增大尝试天数tryDays↑ -- 减小船载重mid↓(左区间)
            if (tryDays <= D_days) end = mid;
            else start = mid + 1; // 尝试天数>上限D, 需tryDays↓ -- 船载重mid↑(右区间)
        }
        return start; // 退出时，start == end == mid(所求)
    }

    // 写法2
    public int shipWithinDays(int[] weights, int D) {
        int start = Arrays.stream(weights).max().getAsInt();
        int end = Arrays.stream(weights).sum();
        while (start < end) {
            int mid = start + end >> 1;
            if (getTryDays(weights, D, mid) <= D)
                end = mid;
            else start = mid + 1;
        }
        return start;
    }

    private int getTryDays(int[] weights, int D, int mid) {
        int tryDays = 1, curW = 0;
        for (int weight : weights) {
            if (curW + weight > mid) {
                tryDays++;
                curW = 0;
            }
            curW += weight;
        }
        return tryDays;
    }
}
