package Array.prefixSum;

// ��ȣ�q53 -> �ϳ��ӡ�q9_42
/*
��������DP��һ���������Ϊk�����ֻ�k����������������Ʊ��
ʹ�þֲ����ֵ��ȫ�����ֵ
localMax[n][k]�������ͣ�n�����е�n��Ԫ�غͷֳ�k�����֡�
globalMax[n][k] ��������ܺͣ�n ����û�е� n ��Ԫ�ز�����Ϊ k �����֡�

localMax[n][k]->��Ȼ��n��һ��Ҫ��������ô��һ����ô�ܰ�����n���������������
Ҫô��n��Ԫ��Ϊһ�飬���ʾ����n-1���������k -1 �飬�������� globalMax[n-1][k-1]
���� n-1 ��ͬһ���еĵ� n ��Ԫ�أ�����ζ�� n-1 ��Ԫ�ر����Ѿ��γ� k ����
���� (n- 1)��һ��Ԫ�ر����ڷ����У���������localMax[n-1][k]
localMax[n][k] = max(globalMax[n-1][k-1], local[n-1][k]) + ����[n-1];

globalMax[n][k] -> ��Ϊ��n�����ܰ���Ҳ���ܲ�������������������������������n������ΪlocalMax[n][k]��
��������������ʾ����n-1����Ϊk���ȫ�����ֵ�����ΪglobalMax[n-1][k]��
globalMax[n][k] = max(globalMax[n-1][k], localMax[n][k])
 */
public class q9_43_max_subarray_iii {
    // ��1��dfs+memo
    public int maxSubArray(int[] nums, int k) {
        Integer[][] memo = new Integer[nums.length][k + 1]; // Ĭ��Ϊnull
        return dfs(nums, k, 0, memo);
    }

    private int dfs(int[] nums, int groupCnt, int idx, Integer[][] memo) {
        if (groupCnt == 0 || idx >= nums.length) return 0;
        if (memo[idx][groupCnt] != null) return memo[idx][groupCnt];

        int prevSum = 0, min = 0, max = Integer.MIN_VALUE, global = Integer.MIN_VALUE;

        for (int i = idx; i < nums.length - groupCnt + 1; i++) {
            prevSum += nums[i];
            if (max > (prevSum - min)) {
                min = Math.min(min, prevSum);
                continue;
            }
            max = Math.max(max, prevSum - min);
            min = Math.min(min, prevSum);

            int rest = dfs(nums, groupCnt - 1, i + 1, memo);
            global = Math.max(global, max + rest);
        }

        memo[idx][groupCnt] = global;
        return global;
    }

    // ��2��dp
    public int maxSubArray_dp(int[] nums, int k) {
        int n = nums.length;
        if (n < k) return 0;

        int[][] dp_local = new int[n + 1][k + 1]; // dp_local[i][j]: ��i��������j�鲻����������ͣ�i>=j,ÿ������1������--
        int[][] dp_global = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i < j) break;
//                 д��1
                // ��ֹi == jʱ��dp��ʽ��[j-1][j](�Ƿ�)����
                dp_local[j - 1][j] = Integer.MIN_VALUE;
                dp_global[j - 1][j] = Integer.MIN_VALUE;

                dp_local[i][j] = Math.max(dp_global[i - 1][j - 1], dp_local[i - 1][j]) + nums[i - 1]; // ѡ��i������
                dp_global[i][j] = Math.max(dp_global[i - 1][j], dp_local[i][j]); // ��ѡ�����ã� or ѡ
//                // д��2
//                if (i == j) {
//                    dp_local[i][j] = dp_global[i-1][j-1] + nums[i-1];
//                    dp_global[i][j] = dp_local[i][j];
//                } else {
//                    dp_local[i][j] = Math.max(dp_global[i-1][j-1], dp_local[i-1][j]) + nums[i-1];
//                    dp_global[i][j] = Math.max(dp_local[i][j], dp_global[i-1][j]);
//                }
            }
        }
        return dp_global[n][k];
    }


    public static void main(String[] args) {
        System.out.println("exp01:");
        int[] nums = new int[]{1, 3, -1, 2, -1, 2};
        q9_43_max_subarray_iii sol = new q9_43_max_subarray_iii();
        System.out.println(sol.maxSubArray(nums, 2));

        System.out.println("exp02:");
        int[] nums2 = new int[]{-1, -2, -3, -100, -1, -50};
        System.out.println(sol.maxSubArray(nums2, 2));
    }
}
