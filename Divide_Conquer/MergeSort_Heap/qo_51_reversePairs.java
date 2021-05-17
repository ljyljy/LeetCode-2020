package Divide_Conquer.MergeSort_Heap;

public class qo_51_reversePairs {
    // 逆序对 - 法1：归并排序
    private int total_cnt = 0;
    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length-1);
        return total_cnt;
    }

    // 分
    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return; // 不写会栈溢出！注意>=!!
        int mid = start + end >> 1;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, start, mid, end);
    }

    // 合
    private void merge(int[] nums, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0; // 左/右区间都升序
        int n = end - start + 1;
        int[] tmp = new int[n];
        // ↓ 加速优化：全局有序√，此时不用计算横跨两个区间的逆序对，直接返回 reverse_pairs
        if (nums[mid] <= nums[mid + 1]) return;  // 不写不影响结果

        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else { // nums[i]（左区间） > nums[j]（右区间）
                // 右区间有较小数, 与左区间每个数都形成一组逆序对(当前逆序对个数+=左子区间个数)
                total_cnt += mid - i + 1; // 左子区间[i, mid] -- 与归并的不同（加一句话即可）
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= end) tmp[k++] = nums[j++];
        // 将排序完毕的tmp 替换入nums[start:end]
        for (int ii = 0; ii < n; ii++) {
            nums[start + ii] = tmp[ii];
        }
    }
}
