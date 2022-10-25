package DP;


public class q915_partition_array_into_disjoint_intervals {
    // ��ȣ�q915���ϳ��ӡ�q9_42
    // ��ָ�㣬ʹ�÷ָ��ġ���ߵ�����������ֵ��С�ڵ��ڡ��ұߵ����������Сֵ����
    // {0,...,k}max <= {k+1,...,n}min
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int dp_minR[] = new int[n];
        int dp_maxL[] = new int[n];
        dp_minR[n-1] = nums[n-1];
        dp_maxL[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp_maxL[i] = Math.max(dp_maxL[i-1], nums[i]);
        }
        for (int i = n-2; i >= 0; i--) {
            dp_minR[i] = Math.min(dp_minR[i+1], nums[i]);
        }

        for (int i = 0; i + 1 < n; i++) {
            if (dp_maxL[i] <= dp_minR[i+1]) {
                return i + 1; // len = idx+1
            }
        }
        return -1;
    }
}
