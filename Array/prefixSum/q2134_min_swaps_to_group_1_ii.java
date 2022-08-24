package Array.prefixSum;

import java.util.Arrays;

// 类比q1151，2134
public class q2134_min_swaps_to_group_1_ii {
    // 写法0: 前缀和
    public int minSwaps0(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        int totalCnt_1 = sum[n];
        if (totalCnt_1 <= 1) return 0;
        int minSwap = Integer.MAX_VALUE;

        int left = 0, right = 0;
        // 写法1：枚举左边界【荐】
        while (left < n) { // 枚举左边界
            right = left + totalCnt_1; // 固定窗口大小 = totalCnt_1
            int curCnt_1;
            if (right <= n) {
                curCnt_1 = sum[right] - sum[left];
            } else { // 如：[..R, ..., L..], ↓分两段：[L, n] + [0, R]
                curCnt_1 = (sum[n] - sum[left]) + sum[right % n];
            }
            int curSwap = totalCnt_1 - curCnt_1;
            minSwap = Math.min(minSwap, curSwap);
            left++;
        }

        // 写法2：枚举右边界 right ∈[totalCnt_1, n+totalCnt_1]
        // for (right = totalCnt_1; right <= n + totalCnt_1; right++) {
        //     left = right - totalCnt_1; // L ∈ [0, n]
        //     int curCnt_1;
        //     if (right <= n) {
        //         curCnt_1 = sum[right] - sum[left]; // 不要mod，否则R=n时，WA!
        //     } else { // ↓分两段：[0~R], [L, N]
        //         curCnt_1 = sum[right % n] + (sum[n] - sum[left]);
        //     }
        //     int curSwap = totalCnt_1 - curCnt_1;
        //     minSwap = Math.min(minSwap, curSwap);
        // }
        return minSwap;
    }

    // 写法1：滑动窗口固定=1总数，滑动时计算：窗口内0的个数=swap数
    // 写法类比：q1151,2134（几乎相同，多一个mod！）
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int totalCnt_1 = Arrays.stream(nums).sum();
        if (totalCnt_1 <= 1) return 0;
        int minSwap = Integer.MAX_VALUE;

        int cnt_0 = 0;
        int left = 0, right = 0;
        while (right <= n + totalCnt_1) {
            left = right - totalCnt_1;
            if (left >= 0) {
                minSwap = Math.min(minSwap, cnt_0);
                int num2Del = nums[left % n]; // 需要mod！
                if (num2Del == 0) cnt_0--;
            }
            // 最初：增大窗口+1，直到窗口大小增大到fixedLen=totalCnt_1
            int num2Add = nums[right % n];
            if (num2Add == 0) cnt_0++;
            right++;
        }
        return minSwap;
    }


}
