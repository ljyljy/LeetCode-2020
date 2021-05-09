package Binary_Search.bin_Answer;

import java.util.Arrays;

public class q1482_min_days_to_make_m_bouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) return -1;
        int res = binSearch(bloomDay, m, k);
        return res;
    }

    // [L, mid], [mid+1, R]
    private int binSearch(int[] bloomDay, int m, int k) {
        int start = Arrays.stream(bloomDay).min().getAsInt();
        int end = Arrays.stream(bloomDay).max().getAsInt();
        while (start < end) {
            int mid = start + end >> 1;
            // 若可行，则缩小可行解(tryDays)至左边界(要找到最少天数)
            if (check(bloomDay, mid, m, k)) {
                end = mid;
            }else {// 如果无法制作花束,则尝试天数再增大一点
                start = mid + 1; // 增大允许绽开的天数，即连续绽开花朵的可能性
            }
        }
        return start;
    }

    private boolean check(int[] bloomDay, int tryDays, int m, int k) {
        int flower = 0; // cur_k: 形成一束花的进度(当前连续开的花朵数)
        int bouquet = 0; // cur_m: 可形成花束的数量
        for(int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= tryDays) { // 等待i开化的天数 <= 尝试天数(在上限内就已开花)
                flower++;
                if (flower == k) {
                    bouquet++;  // 形成一束花
                    flower = 0; // 重置下一束花的进度
                }
            } else flower = 0; // 花朵不连续，重置花束制作进度
            if (bouquet >= m) break;// 可制成的花束数量>=要求的m束
        }
        return bouquet >= m;
    }
}
