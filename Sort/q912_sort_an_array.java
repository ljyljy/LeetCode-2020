package Sort;

public class q912_sort_an_array {
    // v1: ¿ìÅÅ
    public int[] sortArray_quickSort(int[] nums) {
        int n = nums.length;
        quickSort(nums, 0, n-1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid);
        quickSort(nums, mid+1, end);
    }

    private int partition(int[] nums, int start, int end) {
        if (start >= end) return start;
        int pivot = nums[start], i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && pivot < nums[j]) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++; j--;
            }
        }
        swap(nums, start, j);
        return j;
    }

    private void swap (int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // v2: ¹é²¢
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        mergeSort(nums, 0, n-1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = start + end >> 1;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int i = start, j = mid+1;
        int n = end - start + 1, k = 0;
        int[] tmp = new int[n];
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= end) tmp[k++] = nums[j++];

        System.arraycopy(tmp, 0, nums, start, n);
//        for (int kk = 0; kk < n; kk++) {
//            nums[start + kk] = tmp[kk];
//        }
    }
}
