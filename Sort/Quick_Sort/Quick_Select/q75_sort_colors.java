package Sort.Quick_Sort.Quick_Select;

public class q75_sort_colors {
    // 法2：O(n) - 2次快选（只分区，不排序）
    public void sortColors(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        int cnt_0 = partition_pivot(nums, 0, n-1, 1); // 分区1：[<1, >=1], 【0, (1&2)】
        // ↓ 下一段分区startIdx = (左区间个数-1)+1 = cnt_0
        int cnt_1 = partition_pivot(nums, cnt_0, n-1, 2); // 分区2：[<2, >=2], [0, 【1, 2】]
    }

    private int partition_pivot(int[] nums, int i, int j, int pivot) {
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && nums[j] >= pivot) j--;
            if(i <= j) {
                swap(nums, i, j);
                i++; j--;
            }
        }
        return i; // 或 j+1
    }


    // 法1: O(nlogn) - 排序(快排)
    public void sortColors1(int[] nums) {
        quickSort1(nums, 0, nums.length-1);
    }

    private void quickSort1(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = partition1(nums, start, end);
        quickSort1(nums, start, mid);
        quickSort1(nums, mid+1, end);
    }

    private int partition1(int[] nums, int start, int end) {
        if (start >= end) return start;
        int pivot = nums[start];
        int i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && nums[j] > pivot) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++; j--;
            }
        }
        swap(nums, start, j);
        return j;
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
