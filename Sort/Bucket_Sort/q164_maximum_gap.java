package Sort.Bucket_Sort;

import java.util.*;

public class q164_maximum_gap {
    // 暴力BF - O(nlogn)
    public int maximumGap_BF(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        Arrays.sort(nums);
        int maxDiff = 0;
        for (int i = 1; i < n; i++) {
            maxDiff = Math.max(maxDiff, nums[i] - nums[i-1]);
        }
        return maxDiff;
    }


    // 法2：桶排序【掌握！】 - 时、空：O(N)
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        int k = Math.max(1, (max-min) / (n-1));// // ?假设的平均桶内容量（>=1, 实际可以为0）
        int cnt = (max-min) / k + 1;// ?桶总数（minmax/k可能有余数，向上取整）
        List<List<Integer>> buckets = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; i++) buckets.add(i, new ArrayList<>());
        // 分桶
        for (int num: nums) {
            int idx = (num-min) / k; // 桶idx=向下取整，助理解：带入maxIdx=cnt-1
            buckets.get(idx).add(num);
        }
        int prevMax = Integer.MIN_VALUE, maxDiff = 0;
        for (List<Integer> bucket: buckets) {
            if (bucket.isEmpty()) continue; // 空桶跳过
            if (prevMax != Integer.MIN_VALUE) { // 本桶min - 前桶max（物理上肯定相邻）
                maxDiff = Math.max(maxDiff, Collections.min(bucket) - prevMax);
            }
            prevMax = Collections.max(bucket);
        }

        // // TEST: 桶内元素
        // System.out.println("min: " + min + ", max: " + max + ";   k=" + k + ", cnt=" + cnt);
        // int bucket_i = 0;
        // for (List<Integer> bucket: buckets) {
        //     System.out.print(++bucket_i + ":\t");
        //     for (int num: bucket) {
        //         System.out.print(num + ", ");
        //     }
        //     System.out.println();
        // }
        return maxDiff;
    }
}
