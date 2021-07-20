package Two_Pointers;

import java.util.*;

public class q18_4sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums); // ↓ 避免与nums[i], nums[j]重复
        int[] last_pair = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        // ∵升序 ∴去重[i,j]仅需保留last_pair ↑

        // 固定两个较小点i&j, 求两数之和[lf]+[rt] = target-[i]-[j]
        for (int i = 0; i < n-3; i++) {
            // 保证i至少跑过一轮：↓idx: i-1 >= i_min = 0; 去重i
            if (i >= 1 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < n-2; j++) {
                // 去重j
                int[] new_pair = {nums[i], nums[j]};
                if (!isEqual(last_pair, new_pair)) last_pair = new_pair.clone();
                else continue; // 说明[nums[i], nums[j]]重复, 跳过

                int lf = j+1, rt = n-1;
                int sum = target - nums[i] - nums[j];
                while (lf < rt) {
                    if (nums[lf] + nums[rt] == sum) {
                        res.add(Arrays.asList(nums[i], nums[j],
                                nums[lf], nums[rt]));
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

    public boolean isEqual(int[] pair1, int[] pair2) {
        return (pair1[0] == pair2[0]) && (pair1[1] == pair2[1]);
    }

}
