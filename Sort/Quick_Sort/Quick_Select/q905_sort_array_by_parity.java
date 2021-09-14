package Sort.Quick_Sort.Quick_Select;

public class q905_sort_array_by_parity {
    // 法1：O(n) - 快选
    private int n;
    public int[] sortArrayByParity(int[] nums) {
        n = nums.length;
        int cnt_even = partition(nums, 0, n-1); // 分区：[偶数 | 奇数]
        return nums;
    }

    private int partition(int[] nums, int start, int end) {
        int i = start, j = end;
        while (i <= j) {
            while (i <= j && !isOdd(nums[i])) i++; // 偶数在前
            while (i <= j && isOdd(nums[j])) j--; // 奇数在后
            if (i <= j) {
                swap(nums, i, j);
                i++; j--;
            }
        }
        return j; // 或 i+1
    }

    // ❤位运算判断奇偶：(num & 1) == 1? odd奇数: even偶数
    private boolean isOdd(int num) {
        return (num & 1) == 1; // 一定要加括号❤！
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
