package Greedy;

import java.util.Arrays;

public class q280_wiggle_sort {
    // ��2��O(n)
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        boolean flag = true;
        for (int i = 0; i+1 < n; i++) {
            if (flag) { // �ڶ� - ÿ�ֱ任T/F
                if (nums[i] > nums[i+1]) {
                    swap(nums, i, i+1);
                }
            } else {
                if (nums[i] < nums[i+1]) {
                    swap(nums, i, i+1);
                }
            }
            flag = !flag;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // ��1��������������� - O(nlogn)
    public void wiggleSort1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i+1 < n; i+=2) {
            swap(nums, i, i+1);
        }
    }
}
