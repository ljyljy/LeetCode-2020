package Array.prefixSum;

import java.util.Arrays;

// 类比q1151，2134
public class q1151_min_swaps_to_group_all_1s_together {
    // 写法0: 前缀和
    public int minSwaps0(int[] data) {
        int n = data.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + data[i-1];
        }
        int totalCnt_1 = sum[n];
        if (totalCnt_1 <= 1) return 0;
        int minSwap = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            // 窗口大小=totalCnt_1
            int lf = i - totalCnt_1, rt = i;
            if (lf >= 0) {
                int curCnt_1 = sum[rt] - sum[lf];
                int curSwap = totalCnt_1 - curCnt_1;
                minSwap = Math.min(minSwap, curSwap);
            }
        }
        return minSwap;
    }


    // 写法1：滑动窗口固定=1总数，滑动时计算：窗口内0的个数=swap数
    // 写法类比：q1151,2134（几乎相同，多一个mod！）
    public int minSwaps(int[] data) {
        int n = data.length;
        int cnt_0 = 0, totalCnt_1 = Arrays.stream(data).sum();
        int minSwap = Integer.MAX_VALUE;

        int left = 0, right = 0;
        while (right < n) {
            left = right - totalCnt_1;
            if (left >= 0) {
                // System.out.println(cnt_0);
                minSwap = Math.min(minSwap, cnt_0);
                // 缩小窗口-1, 由于窗口固定，每轮只运行一对[left++, right++]
                //      使用if！不用while
                int num2Del = data[left];// 无需left++，每轮窗口固定，left会随right++自动递增
                if (num2Del == 0) cnt_0--;
            }
            // 最初：增大窗口+1，直到窗口大小增大到fixedLen=totalCnt_1
            int num2Add = data[right++];
            if (num2Add == 0) cnt_0++;
        }
        // 最后right=n时，未计算
        // case:[1,0,0,1,1,1], AK=1, 若不↓，则WA=2
        if (right == n) {
            minSwap = Math.min(minSwap, cnt_0);
        }

        return minSwap;
    }

    // 写法2，思路一样，有print过程
    public int minSwaps2(int[] data) {
        int n = data.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + data[i-1];
        }
        int totalCnt_1 = sum[n];
        if (totalCnt_1 <= 1) return 0;

        int minCnt = Integer.MAX_VALUE;
        // 窗口大小固定 = totalCnt_1
        for (int i = totalCnt_1; i <= n; i++) {
            int curCnt_1 = sum[i] - sum[i - totalCnt_1]; // 窗口内的'1'的数量
            int swapCnt_F = totalCnt_1 - curCnt_1; // 理论上，还需交换的个数
//            if (swapCnt_T == swapCnt_F) {
                minCnt = Math.min(minCnt, swapCnt_F);
//            }
        }

        // ===================================
        // print: i & data & preSum
        for (int i = 0; i <= n; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("\t" + data[i]);
        }
        System.out.println();
        for (int i = 0; i <= n; i++) {
            System.out.print(sum[i] + "\t");
        }
        System.out.println();
        System.out.println("totalCnt_1 = " + totalCnt_1);
        // ===================================

        return minCnt == Integer.MAX_VALUE? 0: minCnt;
    }

    public static void main(String[] args) {
//        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
        int[] data = {1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1};
        q1151_min_swaps_to_group_all_1s_together sol = new q1151_min_swaps_to_group_all_1s_together();
        int ans = sol.minSwaps(data);
        System.out.println(ans);
    }
}
