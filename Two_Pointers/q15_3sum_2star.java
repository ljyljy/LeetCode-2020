package Two_Pointers;

import java.lang.reflect.Array;
import java.util.*;

// 1.暴力求解 O(n^3)
// 2.哈希表map记录
// 3.双指针左右下表，向中间收敛【前提：有序】

//leetcode submit region begin(Prohibit modification and deletion)
class q15_3sum_2star {
    /**
     * line8：对nums[i]的判重必须是if-continue，因为for中有i++，若while&i++会导致多次i后移❤️
     * line10：while（lf＜rt），不可取等！因为必须要保证有3个数
     */
    // 最新版
    public List<List<Integer>> threeSum0(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n-2; i++) { // 理应i < n-2，但i<n也可以AC
            if (i-1>=0 && nums[i] == nums[i-1]) continue;
            int lf = i+1, rt = n-1;
            while (lf < rt) {
                int sum = nums[lf] + nums[rt] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[lf], nums[rt]));
                    while (lf < rt && nums[lf] == nums[lf+1]) lf++;
                    while (lf < rt && nums[rt] == nums[rt-1]) rt--;
                    lf++; rt--;
                } else if (sum > 0) rt--;
                else lf++;
            }
        }
        return res;
    }
    // 3. 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // 双指针大前提： 有序
        List<List<Integer>> res = new LinkedList<>();
        // 因为lo、hi在最右分别为nums.length-1，nums.length-2，而i下标应左于lo
        for (int i = 0; i < n-2; ++i) {
            // tips: num[i-1] (而非i+1)
            //                      ↓去重！！！避免target一样。
            if (i >= 1 && nums[i] == nums[i-1]) continue; // 去重1
            // lf = i+1(而非1)
            int lf = i+1, rt = n-1, sum = 0 - nums[i]; // ❤❤❤
            while (lf < rt) {
                if (nums[lf] + nums[rt] == sum) {
                    res.add(Arrays.asList(nums[i], nums[lf], nums[rt]));
                    while (lf < rt && nums[lf] == nums[lf+1]) lf++; // 去重2
                    while (lf < rt && nums[rt] == nums[rt-1]) rt--; // 去重3
                    lf++; rt--; // 摆脱重复
                }else if (nums[lf] + nums[rt] < sum) lf++;
                else rt--;
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
