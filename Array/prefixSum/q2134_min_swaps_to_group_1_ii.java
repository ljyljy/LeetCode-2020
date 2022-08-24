package Array.prefixSum;

import java.util.Arrays;

// ���q1151��2134
public class q2134_min_swaps_to_group_1_ii {
    // д��0: ǰ׺��
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
        // д��1��ö����߽硾����
        while (left < n) { // ö����߽�
            right = left + totalCnt_1; // �̶����ڴ�С = totalCnt_1
            int curCnt_1;
            if (right <= n) {
                curCnt_1 = sum[right] - sum[left];
            } else { // �磺[..R, ..., L..], �������Σ�[L, n] + [0, R]
                curCnt_1 = (sum[n] - sum[left]) + sum[right % n];
            }
            int curSwap = totalCnt_1 - curCnt_1;
            minSwap = Math.min(minSwap, curSwap);
            left++;
        }

        // д��2��ö���ұ߽� right ��[totalCnt_1, n+totalCnt_1]
        // for (right = totalCnt_1; right <= n + totalCnt_1; right++) {
        //     left = right - totalCnt_1; // L �� [0, n]
        //     int curCnt_1;
        //     if (right <= n) {
        //         curCnt_1 = sum[right] - sum[left]; // ��Ҫmod������R=nʱ��WA!
        //     } else { // �������Σ�[0~R], [L, N]
        //         curCnt_1 = sum[right % n] + (sum[n] - sum[left]);
        //     }
        //     int curSwap = totalCnt_1 - curCnt_1;
        //     minSwap = Math.min(minSwap, curSwap);
        // }
        return minSwap;
    }

    // д��1���������ڹ̶�=1����������ʱ���㣺������0�ĸ���=swap��
    // д����ȣ�q1151,2134��������ͬ����һ��mod����
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
                int num2Del = nums[left % n]; // ��Ҫmod��
                if (num2Del == 0) cnt_0--;
            }
            // ��������󴰿�+1��ֱ�����ڴ�С����fixedLen=totalCnt_1
            int num2Add = nums[right % n];
            if (num2Add == 0) cnt_0++;
            right++;
        }
        return minSwap;
    }


}
