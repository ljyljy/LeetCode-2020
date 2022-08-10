package Two_Pointers;

import java.util.*;

public class q18_4sum {
    /**
    * 因为官方增加了一个新的用例
    * {1000000000，1000000000，1000000000，1000000000} 0
    * 导致了代码出现溢出错误，是因为int的只能到表示[-2147483648,2147483647]，所以在判断
    * 解决：全部改为【long】！
     *
     * 坑：
     * 1）j从i+1起！
     * 2）j无需单独去重（无需和i一样去重），但pair<i,j>需去重
     * 3）sum--》全部改为long
     * 4）找到可行解后，仍需对lf、rt去重，最后移动一次指针！
    */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        long[] lastPair = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < n - 3; i++) {
            if (i-1>=0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < n - 2; j++) { // j从i+1起，而非0！
                // if (j-1>=0 && nums[j] == nums[j-1]) continue;  // WA! 【单独的j不需要去重】
                long[] curPair = {nums[i], nums[j]}; // pair<i,j>需要去重
                if (isEquals(curPair, lastPair)) continue;
                lastPair = curPair;

                int lf = j+1, rt = n-1;
                while (lf < rt) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[lf] + (long)nums[rt];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lf], nums[rt]));
                        while (lf < rt && nums[lf] == nums[lf+1]) lf++;
                        while (lf < rt && nums[rt] == nums[rt-1]) rt--;
                        lf++; rt--;
                    } else if (sum < target) {
                        lf++;
                    } else rt--;
                }
            }
        }
        return res;
    }

    private boolean isEquals(long[] pair1, long[] pair2) {
        return pair1[0] == pair2[0] && pair1[1] == pair2[1];
    }

    // old
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums); // ↓ 避免与nums[i], nums[j]重复
        long[] last_pair = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        // ∵升序 ∴去重[i,j]仅需保留last_pair ↑

        // 固定两个较小点i&j, 求两数之和[lf]+[rt] = target-[i]-[j]
        for (int i = 0; i < n-3; i++) {
            // 保证i至少跑过一轮：↓idx: i-1 >= i_min = 0; 去重i
            if (i >= 1 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < n-2; j++) {// j从i+1起，而非0！
                // if (j-1>=0 && nums[j] == nums[j-1]) continue; // WA! 【单独的j不需要去重】
                // pair<i,j>需要去重
                long[] new_pair = {nums[i], nums[j]};
                if (!isEqual(last_pair, new_pair)) last_pair = new_pair;
                else continue; // 说明[nums[i], nums[j]]重复, 跳过

                int lf = j+1, rt = n-1;
                long sum = (long) target - nums[i] - nums[j];
                while (lf < rt) {
                    if (nums[lf] + nums[rt] == sum) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lf], nums[rt]));
                        while (lf < rt && nums[lf] == nums[lf+1]) lf++; // 去重lf
                        while (lf < rt && nums[rt] == nums[rt-1]) rt--; // 去重rt
                        lf++; rt--;
                    } else if (nums[lf] + nums[rt] > sum) {
                        rt--;// ∑↓
                    } else lf++;
                }
            }
        }
        return res;
    }

    public boolean isEqual(long[] pair1, long[] pair2) {
        return (pair1[0] == pair2[0]) && (pair1[1] == pair2[1]);
    }

}
