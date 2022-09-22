package Recursion.combination_based;

import java.util.Arrays;

public class q698_partition_to_k_equal_sum_subsets {
    // 【DFS + 剪枝】
    //   如果我们提前对 nums 数组排序，把大的数字排在前面/后面，那么【大数优先分配到桶中】，
    //   对于之后的数字，bucket[i] + nums[index] 会【更大，更容易触发剪枝】的 if 条件。
    int n;
    public boolean canPartitionKSubsets_new(int[] nums, int k) {
        n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false; // 可行性剪枝
        Arrays.sort(nums);
        int targetSum = sum / k;
        return dfs(nums, n - 1, k, 0, targetSum, new boolean[n]);
    }

    boolean dfs(int[] nums, int idx, int bucketCnt, int bucketSum, int targetSum, boolean[] used) {
        if (bucketCnt == 0) return true;
        if (bucketSum == targetSum) {
            return dfs(nums,n - 1, bucketCnt - 1, 0, targetSum, used);
        }
        for (int i = idx; i >= 0; i--) {  // 倒序剪枝：∵nums升序，需【优先大数入桶】，争取提前剪枝
            if (used[i] || bucketSum + nums[i] > targetSum) continue;  // 可行性剪枝
            used[i] = true;
            if (dfs(nums,i - 1, bucketCnt, bucketSum + nums[i], targetSum, used)) {
                return true; // 当前桶，选择nums[i]是否放入
            }
            used[i] = false;
            // 若本次连第一个值都无法搜到（即bucketSum=0 <=> 剩余元素的最大值(∵逆序遍历，大数优先)不能作为当前集合的元素），必然无解。
            if (bucketSum == 0) return false; // 可行性剪枝，可不加
        }
        return false;
    }

    // 【未优化！】法2（面试写，对比分析复杂度）：枚举桶，对每个num执行选or不选【不可重复放桶, 去重used】 -- 推荐-O(k桶*(2^n))
    // 普通的组合总和 【+ 枚举桶】
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
        // 所有桶都被装满了，而且 nums 一定全部用完了
        if (k == 0) return true;

        // if (idx == nums.length) { // ← 错！
        if (curSum == targetSum) {
            // 【0】装满了当前桶，递归穷举下一个桶的选择[(大树)递归下沉drill down?(多选)]
            //   递归1：让下一个桶从 nums[0] 开始选数字
            return dfs_v2(nums, k-1, 0, used, 0, targetSum);
        }   // ↑ 【【【勿忘return】】】!!!否则【WA，不应继续for】!!!

        // 【1】针对当前桶[当前树深]，横向遍历：对每个数字如何选择？【多选】
        for (int i = idx; i < nums.length; i++) {
            // 剪枝：
            if (used[i]) continue;// 1. nums[i] 已经被装入别的桶中
            if (curSum + nums[i] > targetSum) continue; // 2. 当前桶装不下 nums[i]

            used[i] = true;
            // 【2】(小树) 递归穷举下一个数字(i加1)【是否装入】当前桶（k不减1）
            if (dfs_v2(nums, k, i+1, used, curSum + nums[i], targetSum))
                return true;
            used[i] = false;
        }
        // 穷举了所有数字，都无法装满当前桶
        return false;
    }

    // 法1（常规，必会）：枚举num，对其执行k选1（选桶） -- 慢-O(k^n)
    public boolean canPartitionKSubsets_v1_slow(int[] nums, int k) {
        // 排除一些基本情况
        int n = nums.length;
        if (k > n) return false;
        int sum = Arrays.stream(nums).sum();
        // 无法分割为等和的子集，有余数
        if (sum % k != 0) return false;
        int targetSum = sum / k;
        // k 个桶（集合），记录每个桶装的数字之和
        int[] buckets = new int[k];


        // // 优化V1：降序排序nums，以便更容易出发剪枝???
        // // ?int[]无法自动降序，需要将int[]转为Integer[]，再转为int[]
        // Integer[] numsBox = Arrays.stream(nums).boxed().toArray(Integer[]::new);
         // Arrays.sort(numsBox, Collections.reverseOrder()); // 降序1
        // Arrays.sort(numsBox, (o1, o2)->(o2-o1));  // 降序2
        // // Integer[]转为int[]
        // int[] sortedNums = Arrays.stream(numsBox).mapToInt(Integer::valueOf).toArray();

        // 优化V2：升序nums，然后反转
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
            // 检查所有桶的数字之和是否都是 target
            for (int bucket: buckets) {
                if (bucket != targetSum)
                    return false;
            }
            return true;
        }

        // 错: for (int i = idx; i < nums.length; i++) {
        //     // 横向遍历：对当前数字（某一行），选桶：
        //     //          针对当前nums[idx], 枚举每个桶
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[idx] > targetSum)
                continue; // 换下一个桶

            buckets[i] += nums[idx];
            // 递归穷举下一个数字的选择
            if (dfs_v1(nums, buckets, idx+1, targetSum))
                return true;
            buckets[i] -= nums[idx];
        }
        // nums[index] 装入哪个桶都不行
        return false;
    }
}
