package DataStructure.SegmentTree;

import java.util.*;

public class q315_count_of_smaller_numbers_after_self {
    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int[] ans;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        this.index = new int[n];
        this.temp = new int[n];
        this.tempIndex = new int[n];
        this.ans = new int[n];
        for (int i = 0; i < n; i++)  index[i] = i;
        mergeSort(nums, 0, n-1);
        List<Integer> res = new ArrayList<>();
        for (int num: ans) res.add(num);
        return res;
    }

    private void mergeSort(int[] nums, int L, int R) {
        if (L >= R) return;
        int mid = L + R >> 1;
        mergeSort(nums, L, mid); // 1.分（两段-升序子序列）
        mergeSort(nums, mid+1, R);
        merge(nums, L, mid, R); // 2.治（两段-合并+升序排列）
    }

    private void merge(int[] nums, int L, int mid, int R) {
        int i = L, j = mid+1; // 左右两段的起始点/指针
        int k = L;
        while (i <= mid && j <= R) { // [8,12,29,50, 100] & [7,9,9,9,15]
            if (nums[i] <= nums[j]) { // 右[(mid+1) ~ (j-1)] < 左[i] <= 右[j]
                temp[k] = nums[i];
                tempIndex[k] = index[i];
                // 进阶-逆序对(q315)在【左区间小】时计算-
                // 即一次性将【右[R_start(mid+1)~(j-1)]】<左[i]<右[j]加入结果
                ans[index[i]] += (j-1-mid); // (j-1)-(mid+1)+1
                k++;
                i++;
            } else{ // 左[i] > 右[j]
                temp[k] = nums[j];
                tempIndex[k] = index[j];
                k++;// 普通(qo_51)-逆序对在【左区间大】时计算
                j++;
            }
        }
        while (i <= mid) {
            temp[k] = nums[i];
            tempIndex[k] = index[i];
            ans[index[i]] += (j-1-mid);
            k++;
            i++;
        }
        while (j <= R) {
            temp[k] = nums[j];
            tempIndex[k] = index[j];
            k++;
            j++;
        }

        for (int p = L; p <= R; p++) {
            index[p] = tempIndex[p];
            nums[p] = temp[p];
        }
    }

}
