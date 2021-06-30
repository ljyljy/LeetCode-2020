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
        merge_v2(nums, L, mid, R); // 2.治（两段-合并+升序排列）
    }

    // 法2：“前有序数组(左小)”元素出列时计算逆序数
    private void merge_v2(int[] nums, int L, int mid, int R) {
        int i = L, j = mid+1; // 左右两段的起始点/指针
        int k = L;
        if (nums[mid] <= nums[mid+1]) return; // 整体有序 直接返回

        while (i <= mid && j <= R) { // [8,12,29,50, 100] & [7,9,9,9,15]
            if (nums[i] <= nums[j]) { // 右[(mid+1) ~ (j-1)] < 左[i] <= 右[j]
                temp[k] = nums[i];
                tempIndex[k] = index[i];
                // 进阶-逆序对(q315)在【左区间小】时计算-法2
                // 即一次性将【右[R_start(mid+1)~(j-1)]】<左[i]<右[j]加入结果
                ans[index[i]] += (j-1-mid); // (j-1)-(mid+1)+1
                k++; // ↑ 右侧比自己[nums[i]]小的元素个数
                i++;
            } else{ // 左[i] > 右[j]
                temp[k] = nums[j];
                tempIndex[k] = index[j];
                k++;
                j++;
            }
        }
        while (i <= mid) {
            temp[k] = nums[i];
            tempIndex[k] = index[i];
            ans[index[i]] += (j-1-mid);// 此处+=正数（因为之前未计算过）
            k++; // ↑ 右侧比自己[nums[i]]小的元素个数
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

    // 【【与q315不同！】】
    // 逆序对 法1：计算【左侧】比自己大的元素个数
    // 例：输入：nums = [5,2,6,1]  输出：[0,1,0,3]
    private void merge_v1 (int[] nums, int L, int mid, int R) {
        int i = L, j = mid+1, k = L;
        if (nums[mid] <= nums[mid+1]) return; // 整体有序 直接返回

        while (i <= mid && j <= R) {
            if (nums[i] > nums[j]) {// 左 > 右 (逆序对数+= [i]~[mid]区间长度)
                temp[k] = nums[j];
                tempIndex[k] = index[j];
                ans[index[j]] += (mid - i + 1);
                k++; j++; // ↑ 左侧([i]~[mid])比自己[nums[j]]大的元素个数
            } else {
                temp[k] = nums[i];
                tempIndex[k] = index[i];
                k++; i++;
            }
        }
        while (i <= mid) {
            temp[k] = nums[i];
            tempIndex[k] = index[i];
            k++; i++;
        }
        while (j <= R) { // 左 > 右(无元素) (逆序对数+= [i]~[mid]区间长度)
            temp[k] = nums[j];
            tempIndex[k] = index[j];
            ans[index[j]] += (mid - i + 1); // 此处+=0（因为之前已经计算过）
            k++; j++; // ↑ 左侧([i]~[mid])比自己[nums[j]]大的元素个数
        }
        for (int p = L; p <= R; p++) {
            nums[p] = temp[p];
            index[p] = tempIndex[p];
        }
    }

}
