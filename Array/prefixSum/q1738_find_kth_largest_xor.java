package Array.prefixSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class q1738_find_kth_largest_xor {
    // 法1：Collections.sort -- 时间O(mnlog(mn)), 空间O(mn)
    public int kthLargestValue_sort1(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0 : matrix[0].length;
        int[][] preXOR = new int[n+1][m+1];
        List<Integer> XORs = new ArrayList<>(); // 取第k大
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preXOR[i][j] = preXOR[i-1][j] ^ preXOR[i][j-1] ^ preXOR[i-1][j-1] ^ matrix[i-1][j-1];
                XORs.add(preXOR[i][j]);
            }
        }
        // 第k大 - 升序排列第idx=n*m-k个，√逆序排列第idx=k-1个
        Collections.sort(XORs, (num1, num2) -> num2 - num1); // 降序更方便
        return XORs.get(k-1);
    }

    // 【次优】法2：优先队列heap（小顶堆！队首！） -- 时间O(mnlog(k)), 空间O(mn)
    public int kthLargestValue_PQ(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0 : matrix[0].length;
        int[][] preXOR = new int[n+1][m+1];
        // 小顶堆--pop小的，保留大的（前k大都保留）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k); // 保留前k大(小顶堆-pop小的)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preXOR[i][j] = preXOR[i-1][j] ^ preXOR[i][j-1] ^ preXOR[i-1][j-1] ^ matrix[i-1][j-1];
                maxHeap.offer(preXOR[i][j]);
                // System.out.println("preXOR[i][j]="+preXOR[i][j]);
                if (maxHeap.size() > k)
                    maxHeap.poll();
            }
        }
        return maxHeap.peek(); // 第k大 -- 前k大数(升序)的首个
    }


    // 【最优】法3：快速选择（快排变形） -- 时间、空间O(mn)
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0 : matrix[0].length;
        int[][] preXOR = new int[n+1][m+1];
        List<Integer> XORs = new ArrayList<>(); // 取第k大
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preXOR[i][j] = preXOR[i-1][j] ^ preXOR[i][j-1] ^ preXOR[i-1][j-1] ^ matrix[i-1][j-1];
                XORs.add(preXOR[i][j]);
            }
        }
        // 第k大 - 快选 - 1)升序排列第idx=N-k个，2)逆序排列第idx=k-1个
        int N = XORs.size();
//        return quickSelct1(XORs.toArray(new Integer[N]), 0, N - 1, N-k); // 升序排列第idx=N-k个
        return quickSelct2(XORs.toArray(new Integer[N]), 0, N - 1, k); // 降序排列第k个

    }

    // 快选v1 -- 1)升序排列第idx=N-k个
    private int quickSelct1(Integer[] nums, int L, int R, int k) {
        int pivot = nums[L + R >> 1];
        int i = L, j = R;
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && pivot < nums[j]) j--;
            if (i <= j) {  // ∵ 不是do-while，∴if内勿漏移动双指针！
                swap(nums, i, j); i++; j--;
            }
        }// 退出后，[L, j] pivot(k’) [i, R]
        if (k <= j) return quickSelct1(nums, L, j, k);
        else if (k >= i) return quickSelct1(nums, i, R, k);
        return nums[k];
    }

    private void swap(Integer[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 快选v2 -- 降序第k个
    private int quickSelct2(Integer[] nums, int L, int R, int k) {
        if (L == R) return nums[L];
        int pivot = nums[L + R >> 1];
        int i = L-1, j = R+1;
        while (i < j) {
            do i++; while (nums[i] > pivot);
            do j--; while (pivot > nums[j]);
            if (i < j) swap(nums, i, j);
        }// 退出后，[L, j-1] pivot(j) [j+1, R]
        int leftLen = j - L + 1;  // ↓ 只递归左边（包括k==leftlen！）
        if (k <= leftLen) return quickSelct2(nums, L, j, k);
        else return quickSelct2(nums, j+1, R, k-leftLen);
    }

}
