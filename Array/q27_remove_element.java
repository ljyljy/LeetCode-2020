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
    // 法1-2：结合q26
    public int removeElement3(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) return 0;
        int write = 0, read = 0;
        while (read < n) {
            if (nums[read] != val) {// vs q26：nums[read] != nums[write-1]
                nums[write++] = nums[read];
            }
            read++;
        }
        return write;
    }

    // 法2：优化版（避免了需要保留的元素的重复赋值）
    // 【但nums[j--]若仍为val，无法避免重复赋值1次】
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
