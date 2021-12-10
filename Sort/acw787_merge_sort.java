package Sort;

import java.util.Scanner;

public class acw787_merge_sort {
    // 稳定算法；非原地排序；好坏均复杂度均为O(nlogn); 自下而上
    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = start + end >> 1;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int i = start, j = mid+1, k = 0;
        int n = end - start + 1;
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
        System.arraycopy(tmp, 0, nums, start, n); // ∴?非原地排序，耗内存;
//        for (int p = 0; p < n; p++) {
//            nums[start+p] = tmp[p];
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        acw787_merge_sort sol = new acw787_merge_sort();
        sol.mergeSort(nums, 0, n-1);
    }
}
