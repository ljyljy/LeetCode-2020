package Array;

public class q189_rotate_array {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        swap(nums, 0, n-1); // 1. ���巴ת
        swap(nums, 0, k-1); // 2. ��ת[0, k-1]
        swap(nums, k, n-1); //    & [k, n-1]
    }
    private void swap(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++; j--;
        }
    }
}
