package Sort.Bucket_Sort;

import java.util.*;

public class q164_maximum_gap {
    // ����BF - O(nlogn)
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


    // ��2��Ͱ�������գ��� - ʱ���գ�O(N)
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        int k = Math.max(1, (max-min) / (n-1));// // ?�����ƽ��Ͱ��������>=1, ʵ�ʿ���Ϊ0��
        int cnt = (max-min) / k + 1;// ?Ͱ������minmax/k����������������ȡ����
        List<List<Integer>> buckets = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; i++) buckets.add(i, new ArrayList<>());
        // ��Ͱ
        for (int num: nums) {
            int idx = (num-min) / k; // Ͱidx=����ȡ��������⣺����maxIdx=cnt-1
            buckets.get(idx).add(num);
        }
        int prevMax = Integer.MIN_VALUE, maxDiff = 0;
        for (List<Integer> bucket: buckets) {
            if (bucket.isEmpty()) continue; // ��Ͱ����
            if (prevMax != Integer.MIN_VALUE) { // ��Ͱmin - ǰͰmax�������Ͽ϶����ڣ�
                maxDiff = Math.max(maxDiff, Collections.min(bucket) - prevMax);
            }
            prevMax = Collections.max(bucket);
        }

        // // TEST: Ͱ��Ԫ��
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
