package Array.prefixSum;

import java.util.Arrays;

// ���q1151��2134
public class q1151_min_swaps_to_group_all_1s_together {
    // д��0: ǰ׺��
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
            // ���ڴ�С=totalCnt_1
            int lf = i - totalCnt_1, rt = i;
            if (lf >= 0) {
                int curCnt_1 = sum[rt] - sum[lf];
                int curSwap = totalCnt_1 - curCnt_1;
                minSwap = Math.min(minSwap, curSwap);
            }
        }
        return minSwap;
    }


    // д��1���������ڹ̶�=1����������ʱ���㣺������0�ĸ���=swap��
    // д����ȣ�q1151,2134��������ͬ����һ��mod����
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
                // ��С����-1, ���ڴ��ڹ̶���ÿ��ֻ����һ��[left++, right++]
                //      ʹ��if������while
                int num2Del = data[left];// ����left++��ÿ�ִ��ڹ̶���left����right++�Զ�����
                if (num2Del == 0) cnt_0--;
            }
            // ��������󴰿�+1��ֱ�����ڴ�С����fixedLen=totalCnt_1
            int num2Add = data[right++];
            if (num2Add == 0) cnt_0++;
        }
        // ���right=nʱ��δ����
        // case:[1,0,0,1,1,1], AK=1, ����������WA=2
        if (right == n) {
            minSwap = Math.min(minSwap, cnt_0);
        }

        return minSwap;
    }

    // д��2��˼·һ������print����
    public int minSwaps2(int[] data) {
        int n = data.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + data[i-1];
        }
        int totalCnt_1 = sum[n];
        if (totalCnt_1 <= 1) return 0;

        int minCnt = Integer.MAX_VALUE;
        // ���ڴ�С�̶� = totalCnt_1
        for (int i = totalCnt_1; i <= n; i++) {
            int curCnt_1 = sum[i] - sum[i - totalCnt_1]; // �����ڵ�'1'������
            int swapCnt_F = totalCnt_1 - curCnt_1; // �����ϣ����轻���ĸ���
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
