package Sort.Quick_Sort.Quick_Select;

public class q9_31_partition_array {
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        // 无需判断start>=end, 此为'子数组排序'操作
        // 本题只需划分，无需排序
        int n = nums.length;
        int i = 0, j = n-1;
        int pivot = k;
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && nums[j] >= pivot) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }// 退出后[start, j], [i, end]
        return i; // 要求返回右区间的start
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
