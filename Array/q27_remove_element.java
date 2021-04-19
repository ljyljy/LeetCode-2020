package Array;

public class q27_remove_element {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // Arrays.sort(nums); // 无需排序！
        int i = 0, j = 0;
        for (j = 0; j < n; j++) {
            if (nums[j] != val)
                nums[i++] = nums[j];
        }
        return i;
    }
}
