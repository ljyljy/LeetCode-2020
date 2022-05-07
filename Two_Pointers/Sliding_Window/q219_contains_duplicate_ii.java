package Two_Pointers.Sliding_Window;

import java.util.HashSet;
import java.util.Set;

public class q219_contains_duplicate_ii {
    // 滑动窗口：若在窗口(<=k)内部，存在重复元素，则true
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            int num = nums[right];
            if (set.contains(num)) { // 滑窗大小<=k
                return true;
            }
            set.add(num);
            right++;

            while (right - left > k) {
                set.remove(nums[left++]);
            }
        }
        return false;
    }
}
