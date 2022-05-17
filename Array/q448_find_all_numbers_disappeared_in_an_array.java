package Array;

import java.util.ArrayList;
import java.util.List;

// Àà±È qo_03¡¢q41¡¢q448
public class q448_find_all_numbers_disappeared_in_an_array {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i]-1 >= 0 && nums[i]-1 < n
                    && nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < n; i++) {
            // System.out.print(nums[i] + ", ");
            if (i+1 != nums[i]) {
                res.add(i+1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
