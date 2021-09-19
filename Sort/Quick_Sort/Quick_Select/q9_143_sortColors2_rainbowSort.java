package Sort.Quick_Sort.Quick_Select;



public class q9_143_sortColors2_rainbowSort {
    // 彩虹排序 - 快选O(n)*归并O(logk)：O(nlogk), quickMergeSort
    public void sortColors2(int[] colors, int k) {
        rainbowSort(colors, 1, k, 0, colors.length-1);
    }

    private void rainbowSort(int[] colors, int color_from, int color_to, int idx_from, int idx_to) {
        if (color_from >= color_to) return;
        // 1. 二分归并 - O(logK), k为颜色种类
        int color_mid = color_from + color_to >> 1;

        // 2. 快选 - O(n), n为颜色总数
        int i = idx_from, j = idx_to;
        while (i <= j) {
            while (i <= j && colors[i] <= color_mid) i++;
            while (i <= j && colors[j] > color_mid) j--;
            if (i <= j) {
                swap(colors, i, j);
                i++; j--;
            }
        }

        // 3. 归并-递归
        rainbowSort(colors, color_from, color_mid, idx_from, j);
        rainbowSort(colors, color_mid+1, color_to, i, idx_to);
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
