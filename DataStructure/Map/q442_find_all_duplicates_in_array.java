package DataStructure.Map;

import java.util.ArrayList;
import java.util.List;

public class q442_find_all_duplicates_in_array {
    /*
   法1) 原地哈希: 时间复杂度O(n) 空间复杂度O(1)
   遍历一遍数组nums ,将其中nums[i]值 对应的位置 i-1 加 n
   注意 取余是还原其本来的位置 (nums[i] - 1) % n
   数组nums中每个整数出现一次 或 两次。
   所以出现两次的整数nums[i], 其对应的i会累加两次，即大于2*n
   其余位置i 上的数 均小于2*n

   */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int n = nums.length;
        for (int num: nums) {
            int idx = (num - 1) % n; // num∈[1,n], -1后取余，保证<hashIdx,num>有效
            nums[idx] += n;
        }
        for (int idx = 0; idx < n; idx++) {
            if (nums[idx] > 2*n) {
                res.add(idx + 1); // num == idx+1
            }
        }
        return res;
    }
}
