//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the cnt of ze
//ro.
//
// Note:
//
// The solution set must not contain duplicate triplets.
//
// Example:
//
//
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
// Related Topics 数组 双指针

// 两数之和的升级版
// a + b == -c

import java.lang.reflect.Array;
import java.util.*;

// 1.暴力求解 O(n^3)
// 2.哈希表map记录
// 3.双指针左右下表，向中间收敛【前提：有序】

//leetcode submit region begin(Prohibit modification and deletion)
class q15_3sum_2star {

    // 3. 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 双指针大前提： 有序
        List<List<Integer>> res = new LinkedList<>();
        // 因为lo、hi在最右分别为nums.length-1，nums.length-2，而i下标应左于lo
        for (int i = 0; i < nums.length-2; ++i) {
            // tips: num[i-1] (而非i+1)
            //                      ↓去重！！！避免target一样。
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                // lo = i+1(而非1)
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++; // 去重
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--; // 去重
                        lo++; hi--; // 很重要！勿忘！
                    }else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }


// 1. 三重循环 & 未去重！ 错误！
//    public List<List<Integer>> threeSum(int[] nums) {
//        int numSize = nums.length;
//        List<List<Integer>> res = new LinkedList<>();
//        for (int i = 0; i < numSize; ++i) { // c (两数之和中的-target)
//            for (int j = 0; j < numSize-1; ++j) {
//                for (int k = j+1; k < numSize; ++k) {
//                    if (nums[j] + nums[k] == -nums[i]) {
//                        if ( ! res.contains(Arrays.asList(nums[i], nums[j], nums[k])) )
//                            res.add(Arrays.asList(nums[i], nums[j], nums[k]));
//                    }
//                }
//            }
//        }
//        return res;
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
