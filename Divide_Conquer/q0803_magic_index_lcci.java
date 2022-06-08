package Divide_Conquer;

public class q0803_magic_index_lcci {
    public int findMagicIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return divConq(nums, 0, n-1);
    }

    private int divConq(int[] nums, int start, int end) {
        if (start > end) return -1;
        int mid = start + end >> 1;
        int left = divConq(nums, start, mid-1);
        if (left != -1) {
            return left;
        } else if (nums[mid] == mid) {
            return mid;
        } else {
            return divConq(nums, mid+1, end);
        }
    }
}
