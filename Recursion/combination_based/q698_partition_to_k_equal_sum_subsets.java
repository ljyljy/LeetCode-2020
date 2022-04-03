package Recursion.combination_based;

import java.util.Arrays;

public class q698_partition_to_k_equal_sum_subsets {
    // ��������2������д���Աȷ������Ӷȣ���ö��Ͱ����ÿ��numִ��ѡor��ѡ�������ظ���Ͱ�� -- �Ƽ�-O(kͰ*(2^n))
    // ��ͨ������ܺ� ��+ ö��Ͱ��
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        if (k > n) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        int targetSum = sum / k;
        boolean[] used = new boolean[n];
        return dfs_v2(nums, k, 0, used, 0, targetSum);
    }

    private boolean dfs_v2(int[] nums, int k, int idx, boolean[] used, int curSum, int targetSum) {
        // ����Ͱ����װ���ˣ����� nums һ��ȫ��������
        if (k == 0) return true;

        // if (idx == nums.length) { // �� ��
        if (curSum == targetSum) {
            // ��0��װ���˵�ǰͰ���ݹ������һ��Ͱ��ѡ��[(����)�ݹ��³�drill down?(��ѡ)]
            //   �ݹ�1������һ��Ͱ�� nums[0] ��ʼѡ����
            return dfs_v2(nums, k-1, 0, used, 0, targetSum);
        }   // �� ����������return������!!!����WA����Ӧ����for��!!!

        // ��1����Ե�ǰͰ[��ǰ����]�������������ÿ���������ѡ�񣿡���ѡ��
        for (int i = idx; i < nums.length; i++) {
            // ��֦��
            if (used[i]) continue;// 1. nums[i] �Ѿ���װ����Ͱ��
            if (curSum + nums[i] > targetSum) continue; // 2. ��ǰͰװ���� nums[i]

            used[i] = true;
            // ��2��(С��) �ݹ������һ������(i��1)���Ƿ�װ�롿��ǰͰ��k����1��
            if (dfs_v2(nums, k, i+1, used, curSum + nums[i], targetSum))
                return true;
            used[i] = false;
        }
        // ������������֣����޷�װ����ǰͰ
        return false;
    }

    // ��1�����棬�ػᣩ��ö��num������ִ��kѡ1��ѡͰ�� -- ��-O(k^n)
    public boolean canPartitionKSubsets_v1_slow(int[] nums, int k) {
        // �ų�һЩ�������
        int n = nums.length;
        if (k > n) return false;
        int sum = Arrays.stream(nums).sum();
        // �޷��ָ�Ϊ�Ⱥ͵��Ӽ���������
        if (sum % k != 0) return false;
        int targetSum = sum / k;
        // k ��Ͱ�����ϣ�����¼ÿ��Ͱװ������֮��
        int[] buckets = new int[k];


        // // �Ż�V1����������nums���Ա�����׳�����֦???
        // // ?int[]�޷��Զ�������Ҫ��int[]תΪInteger[]����תΪint[]
        // Integer[] numsBox = Arrays.stream(nums).boxed().toArray(Integer[]::new);
         // Arrays.sort(numsBox, Collections.reverseOrder()); // ����1
        // Arrays.sort(numsBox, (o1, o2)->(o2-o1));  // ����2
        // // Integer[]תΪint[]
        // int[] sortedNums = Arrays.stream(numsBox).mapToInt(Integer::valueOf).toArray();

        // �Ż�V2������nums��Ȼ��ת
        Arrays.sort(nums);
        for (int i = 0, j = n-1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        return dfs_v1(nums, buckets, 0, targetSum);
    }

    private boolean dfs_v1(int[] nums, int[] buckets, int idx, int targetSum) {
        if (idx == nums.length) {
            // �������Ͱ������֮���Ƿ��� target
            for (int bucket: buckets) {
                if (bucket != targetSum)
                    return false;
            }
            return true;
        }

        // ��: for (int i = idx; i < nums.length; i++) {
        //     // ����������Ե�ǰ���֣�ĳһ�У���ѡͰ��
        //     //          ��Ե�ǰnums[idx], ö��ÿ��Ͱ
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[idx] > targetSum)
                continue; // ����һ��Ͱ

            buckets[i] += nums[idx];
            // �ݹ������һ�����ֵ�ѡ��
            if (dfs_v1(nums, buckets, idx+1, targetSum))
                return true;
            buckets[i] -= nums[idx];
        }
        // nums[index] װ���ĸ�Ͱ������
        return false;
    }
}
