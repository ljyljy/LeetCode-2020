package Sort.Quick_Sort.Quick_Select;

import java.util.PriorityQueue;
import java.util.Scanner;

public class acw786_topKthSmallest {
    // 法1：堆
    public int top_k_min(int[] arr, int k) {
//        if (arr == null || k <= 0) return -1;
        // 保留前k小 - pop大数 - 大顶堆(需要重写compare)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                                        (o1, o2) -> (o2 - o1));

        for (int num: arr) { // 例如：5 4 3 2 1
            maxHeap.offer(num);
            if (maxHeap.size() > k)
                maxHeap.poll();
        } // 保留前k小（降序, 如: 3 2 1）

        return maxHeap.peek();
    }

    // 法2-1：快速选择 quickSelect - O(n) -- 升序第k小
    public int top_k_min2(int[] arr, int k) {
//        return quickSelect(arr, 0, arr.length-1, k); // 模板1
        return quickSelect2(arr, 0, arr.length-1, k-1); // idx为k-1
    }

    // 模板1（写法与快排类似，必会！）
    private int quickSelect(int[] arr, int start, int end, int k) {
        if (start == end) return arr[start];
        int x = arr[start + end >> 1];
        int i = start - 1, j = end + 1;
        while (i < j) {
            do i++; while (arr[i] < x);
            do j--; while (x < arr[j]);
            if (i < j) swap(arr, i, j);
        } // 退出：i == j, x = arr[mid | i | j]
        int leftLen = j - start + 1;  // 左半段长度/数的个数
        if (k <= leftLen)
            return quickSelect(arr, start, j, k);
        return quickSelect(arr, j+1, end, k - leftLen);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 法2-2：快速选择 quickSelect 【模板2-荐】          idx需k-1↓
    private int quickSelect2(int[] arr, int start, int end, int k) {
        if (start == end) return arr[start];
        int x = arr[start];
        int i = start+1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] < x) i++;
            while (i <= j && x < arr[j]) j--;
            if (i <= j) { // ∵ 非do-while ∴ if内勿漏双指针移动！
                swap(arr, i, j); i++; j--;
            }
        }
        swap(arr, start, j);
        // 退出后 [L, j] pivot_x(k) [i, R]
        if (k <= j) return quickSelect2(arr, start, j, k);
        else if (k >= i) return quickSelect2(arr, i, end, k);
        else return arr[k];
    }

    // 法2-3：快速选择 quickSelect 【模板3-荐】
    private int quickSelect3(int[] arr, int start, int end, int k) {
        if (start == end) return arr[start];
        int x = arr[start + end >> 1];// 最后无需swap【only合并可写】【单独partition()不可设pivot=[mid]，易错！】
        int i = start, j = end;
        while (i <= j) {
            while (i <= j && arr[i] < x) i++;
            while (i <= j && x < arr[j]) j--;
            if (i <= j) { // ∵ 非do-while ∴ if内勿漏双指针移动！
                swap(arr, i, j); i++; j--;
            }
        } // 退出后 [L, j] pivot_x(k) [i, R]
        if (k <= j) return quickSelect3(arr, start, j, k);
        else if (k >= i) return quickSelect3(arr, i, end, k);
        else return arr[k];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        acw786_topKthSmallest sol = new acw786_topKthSmallest();
//        int res = sol.top_k_min(nums, k);
        int res = sol.top_k_min2(nums, k);
        System.out.println(res);
    }
}
