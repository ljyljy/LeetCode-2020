package Two_Pointers;

public class q26_remove_duplicates_from_sorted_array {
    // 法1：快慢指针 - 结合q80
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int slow = 1, fast = 1;
        while (fast < n) {
            if (nums[slow-1] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    // 法2：隐式双指针 - 结合q80
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int num: nums) {
            if (slow < 1 || nums[slow-1] != num)
                nums[slow++] = num;
        }
        return slow;
    }
}
