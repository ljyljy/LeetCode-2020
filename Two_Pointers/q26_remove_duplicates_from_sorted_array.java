package Two_Pointers;

public class q26_remove_duplicates_from_sorted_array {
    // 法1：快慢指针 - 结合q26, 27, 80
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int write = 1, read = 1; // slow = write, fast = read
        while (read < n) {
            if (nums[read] != nums[write-1]) { // vs q27：nums[read] != val
                nums[write++] = nums[read];
            }
            read++;
        }
        return write;
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

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        q26_remove_duplicates_from_sorted_array sol = new q26_remove_duplicates_from_sorted_array();
        int newLen = sol.removeDuplicates1(nums);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
