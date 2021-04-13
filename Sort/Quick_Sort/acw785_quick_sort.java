package Sort.Quick_Sort;
//# 快速排序
//"""
//(1)通过一趟排序把将要排序的数据分成两个独立的部分，其中一个部分必然小于另一个部分，
//然后再按照这个方法将两个部分快速排序，整个过程可以递归，最后变成最终有序的数据
//(2)怎么分成两部分?
//找到一个数字为基准 ，比它小的移到它左边，比其大的移到基准数右边
//"""

public class acw785_quick_sort {
    public void quickSort(int[] nums, int l, int r) {
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
        quickSort(nums, l, j);   // ②(list, l, i-1)
        quickSort(nums, j+1, r); // ②(list, i, r)
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    // 注意：why do-while？ - 避免死循环（i < j，且q[i] == q[j] == x时，死循环于swap）
}