package Binary_Search.bin_Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// ���顢���ִ𰸡�DFS
// �����⣺�ָ�Ϊm�����������飨���⣺Ҫ������, ���˳�򲻱䣩
public class q410_split_array_largest_sum {
    // ��������3�����ִ�
    public int splitArray(int[] nums, int m) {
        // ���㡸��������Եĺ͵����ֵ�������½�
        int max = Arrays.stream(nums).max().getAsInt();
        int sum = Arrays.stream(nums).sum();
        // �����ִ𰸡�: ȷ��һ��ǡ���ġ���������Եĺ͵����ֵ����
        // ʹ������Ӧ�ġ�������ķָ�����ǡ�õ��� m
        int start = max, end = sum;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            int splitCnt = trySplit(nums, mid);

            if (splitCnt > m) {
                // ����ָ���̫�࣬˵������������Եĺ͵����ֵ��̫С����ʱ��Ҫ������������Եĺ͵����ֵmid������
                start = mid + 1;
            } else { // �����ָ�Ϊm��ʱ����Ҫ�������ҡ������䡿����min
                end = mid;
            }
        }
        return start;
    }

    private int trySplit(int[] nums, int trySum) {
        int splitCnt = 1, rangeSum = 0;
        for (int num: nums) {
            if (rangeSum + num > trySum) {
                rangeSum = 0;
                splitCnt++;
            }
            rangeSum += num;
        }
        return splitCnt;
    }


    // ��2������(TODO: �ʼ�)
//    TODO: https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/
    // ���Ϸ�1-https://leetcode-cn.com/problems/split-array-largest-sum/solution/ji-yi-hua-sou-suo-dong-tai-gui-hua-by-fe-0kzf/
    public int splitArray_DP(int[] nums, int m) {
        int len = nums.length;
        // ǰ׺�ͣ�preSum[i] = sum[0..i)
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // ���� [i..j] �ĺ� preSum(j + 1) - preSum(i)
        int[][] dp = new int[len][m + 1];
        // ��ʼ��������Ҫ����Сֵ����ֵ��ֵ��Ϊһ�������ܴﵽ�ĺܴ��ֵ
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // �ָ���Ϊ 1 �������ָ����������е�ǰ׺�;������ε�״ֵ̬
        for (int i = 0; i < len; i++) {
            dp[i][1] = preSum[i + 1];
        }

        // �ӷָ���Ϊ 2 ��ʼ����
        for (int k = 2; k <= m; k++) {
            // ��δ������� i �Ǵ� j ����Сֵ����һλ��ʼ������� k - 1
            for (int i = k - 1; i < len; i++) {
                // j ��ʾ�� k - 1 ����������һ��Ԫ�ض��±꣬��СֵΪ k - 2�����ֵΪ len - 2�����һ������������ 1 ��Ԫ�أ�
                for (int j = k - 2; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], preSum[i + 1] - preSum[j + 1]));
                }
            }
        }
        return dp[len - 1][m];
    }

    // ��1.DFS��memo��Ϊint[][]������TLE��
    private int n; // res = Integer.MAX_VALUE; // �� maxSubSum_min
    private int[] nums, preSum;
    private int[][] memo;

    public int splitArray_DFS(int[] nums, int m) {
        this.nums = nums;
        n = nums.length;
        memo = new int[1001][51];
        for (int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);

        preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        return dfs( 1, m); // ��preSum[1, n]�ָ�m��--������maxSum����Сֵ
    }

    private int dfs(int idx, int splitCnt) { // ��ʼidx����1��~ n���ָ����
        // ?��֦��ʣ��ָ����(1) <= ʣ���ѡ����(n-idx+1)
        if (splitCnt > n - idx + 1) return Integer.MAX_VALUE;

        if (memo[idx][splitCnt] != -1) return memo[idx][splitCnt];

        if (splitCnt == 1) {
            int curSum = preSum[n] - preSum[idx-1]; // �� nums[idx~n]
            memo[idx][splitCnt] = curSum;
            return curSum;
        }

        // ��nums[idx~n]�ָ�splitCnt�ε�maxSubSum_curMin
        int cur = Integer.MAX_VALUE; // maxSubSum_curMin
        for (int i = idx; i <= n; i++) {
            cur = Math.min(cur,
                    // ����(��̽��nums[i+1,n]�ָ�splitCnt-1��) || ǰ���(sum=��nums[idx, i])
                    Math.max(dfs(i+1, splitCnt-1),
                            preSum[i] - preSum[idx-1]));
        }
        memo[idx][splitCnt] = cur;
        return cur;
    }

    // ��1.DFS - TLE��TODO: memo_[1001][51]��
//    private int n; // res = Integer.MAX_VALUE; // �� maxSubSum_min
//    private int[] nums, preSum;
    private Map<String, Integer> memo_ = new HashMap<>();

    public int splitArray_DFS_TLE(int[] nums, int m) {
        this.nums = nums;
        n = nums.length;
        preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        return dfs0(1, m); // ��preSum[1, n]�ָ�m��--������maxSum����Сֵ
//        return res;
    }

    private int dfs0(int idx, int splitCnt) { // ��ʼidx����1��~ n���ָ����
        // ?ʣ��ָ����(1) <= ʣ���ѡ����(n-idx+1)
        if (splitCnt > n - idx + 1) return Integer.MAX_VALUE;

        String key = idx + "_" + splitCnt;
        if (memo_.containsKey(key)) return memo_.get(key);

        if (splitCnt == 1) {
            int curSum = preSum[n] - preSum[idx-1]; // �� nums[idx~n]
//            res = Math.min(res, curSum);
            memo_.put(key, curSum);
            return curSum;
        }

        // ��nums[idx~n]�ָ�splitCnt�ε�maxSubSum_curMin
        int cur = Integer.MAX_VALUE; // maxSubSum_curMin
        for (int i = idx; i <= n; i++) {
            cur = Math.min(cur,
            // ����(��̽��nums[i+1,n]�ָ�splitCnt-1��) || ǰ���(sum=��nums[idx, i])
                    Math.max(dfs0(i+1, splitCnt-1),
                            preSum[i] - preSum[idx-1]));
        }
        memo_.put(key, cur);
        return cur;
    }

}
