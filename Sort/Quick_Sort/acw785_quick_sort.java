package Sort.Quick_Sort;
//# 快速排序
//"""
//(1)通过一趟排序把将要排序的数据分成两个独立的部分，其中一个部分必然小于另一个部分，
//然后再按照这个方法将两个部分快速排序，整个过程可以递归，最后变成最终有序的数据
//(2)怎么分成两部分?
//找到一个数字为基准 ，比它小的移到它左边，比其大的移到基准数右边
//"""

import java.util.Scanner;

public class acw785_quick_sort {
    // 【推荐】-模板1：每次同时移动2根指针 i++ && j--
    // 返回pivot主元的下标
    public void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition_v1(arr, start, end);

        quickSort(arr, start, mid);
        quickSort(arr, mid+1, end);
    }

    private int partition_v1(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        // 空出arr[start], 在最后做交换（得到交换后的pivot下标：mid）
        int i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] < pivot) i++; // ❤3:都严格≠pivot
            while (i <= j && pivot < arr[j]) j--;
            if (i <= j) { // ❤1:必须严格i<=j
                swap(arr, i, j); i++; j--; // ❤2: 指针必须移动
            }
        }// 退出后 (start) [start+1, (j)]  [i, end]
        swap(arr, start, j); // [start, j(pivot)][i, end]
        return j; // 或 i-1, 即分区mid下标（右闭）
    }

    private void swap (int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 模板2（隐式partition，合并）：有重复元素可用
    private void quickSort2(int[] arr, int start, int end) {
        if (start >= end) return;
        int i = start, j = end;
        int pivot = arr[(start + end) / 2];
        while (i <= j) {
            while (i <= j && arr[i] < pivot) i++;
            while (i <= j && arr[j] > pivot) j--;
            if (i <= j) {
                swap(arr, i, j); i++; j--;
            }
        }
        // swap(arr, start, j);
        quickSort2(arr, start, j); //隐式partition[start, R], [L/R+1, end]
        quickSort2(arr, i, end);
    }

    // 老模板[不推荐]
    public void quickSort00(int[] nums, int l, int r) {
        if (l >= r) return;
        // 初始化(i, j作为哨兵，位于nums之外的两侧)
        int i = l - 1, j = r + 1;
        // 取中间值, idx = l + r >> 1
        int x = nums[l + r >> 1];
        while (i < j) {  // 而非l<r！
            do i++; while (nums[i] < x); // 退出[i]>=x
            do j--; while (nums[j] > x); // 退出[j]<=x
            if (i < j) swap(nums, i, j);
        }
        quickSort00(nums, l, j);   // ②(list, l, i-1)
        quickSort00(nums, j+1, r); // ②(list, i, r)
    }
    // 注意：why do-while？ - 避免死循环（i < j，且q[i] == q1310_xor_queries_of_a_subarray[j] == x时，死循环于swap）


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            // System.out.println(arr[i]);
        }

        acw785_quick_sort sol = new acw785_quick_sort();
        sol.quickSort2(arr, 0, n-1);
        for (int num: arr)
            System.out.print(num+" ");
    }
}