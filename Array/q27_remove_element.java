package Array;

public class q27_remove_element {
    // 法1
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
    // 法2：优化版（避免了需要保留的元素的重复赋值）
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i = 0, j = n-1;
        while (i <= j) {
            if (nums[i] == val) {
                nums[i] = nums[j--];
            } else i++;
        }
        return i;
    }
}
