package DataStructure.Heap;

import java.util.PriorityQueue;

public class q215_kth_largest_element_in_an_array {
    // 法1：小顶堆(剔除升序的前k-1个数，堆顶即为第k大-升序) - O(nlogk)
    public int findKthLargest_1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for(int num : nums) {
            heap.offer(num);
            if (heap.size() > k)
                heap.poll();
        }
        return heap.peek();
    }

    // 法2_1：写法1-快速选择QuickSelect - O(n) 【模板1】
    //  【降序】！第k个数(第k大)  -  【L, i(pivot)】【i+1, R】
    public int findKthLargest_2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        return quickSelect(nums, 0, nums.length-1, k); // ← 若升序，则传参n-k
    }

    private int quickSelect(int[] nums, int L, int R, int k) {
        if (L == R) return nums[L];
        int pivot = nums[L + R >> 1];
        int i = L-1, j = R+1;
        while (i < j) {
            // 降序！ nums[i] > pivot > nums[j]
            do i++; while (nums[i] > pivot);
            do j--; while (pivot > nums[j]);
            if (i < j) swap(nums, i, j);
        }
        int leftLen = j - L + 1;
        if (k <= leftLen) // 只递归左边（包括k==leftlen！）
            return quickSelect(nums, L, j, k);
        else
            return quickSelect(nums, j+1, R, k - leftLen);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 法2_2：写法2-快速选择QuickSelect - O(n) 【模板2】
    //  升序！第k大 = 第n-k小  - 【L, j】(pivot)【i, R】
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        return quickSelect_2(nums, 0, n-1, n-k);
    }

    private int quickSelect_2(int[] nums, int L, int R, int k) {
        if (L == R) return nums[L];
        int pivot = nums[L + R >> 1];
        int i = L, j = R;
        while (i <= j) {
            // 升序！ nums[i] < pivot < nums[j]
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && pivot < nums[j]) j--;
            if (i <= j) { // ∵ 不是do-while，∴if内勿漏移动双指针！
                swap(nums, i, j); i++; j--;
            }
        } // 退出后，[L, j] pivot(k) [i, R]
        if (k <= j) return quickSelect_2(nums, L, j, k); // 递归左区间
        else if (k >= i) return quickSelect_2(nums, i, R, k);
        else return nums[k]; // j < k < i
    }
}
