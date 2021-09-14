package Sort.Quick_Sort.Quick_Select;

public class q922_sort_array_by_parity_ii {
    // 类似q9_144, but 奇偶个数相等
    // ret结果：偶奇，偶奇
    // O(n) - 快选分区 + 交叉互换(skip=2)
    private int n;
    public int[] sortArrayByParityII(int[] nums) {
        n = nums.length;
        int cnt_odd = partition(nums, 0, n-1); // 分区[奇,奇 | 偶,偶]
//        for (int num: nums) System.out.print(num + " "); // 如[1 1 1 | 0 4 0 ]
        return swapArr(nums, cnt_odd); // 例：0, 1, 0,1, 4, 1
    }

    // [奇*k | 偶*k] -> [偶(L=0)奇, 偶奇, 偶奇(R=n-1)]
    private int[] swapArr(int[] nums, int cnt_odd) {
        int i = 0, j = n-1;
        while (i < cnt_odd && cnt_odd <= j) {
            swap(nums, i, j);
            i += 2;
            j -= 2;
        }
        return nums;
    }

    private int partition(int[] nums, int start, int end) {
        int i = start, j = end;
        while (i <= j) {
            while (i <= j && isOdd(nums[i])) i++; // 奇数在前
            while (i <= j && !isOdd(nums[j])) j--;// 偶数在后
            if (i <= j) {
                swap(nums, i, j);
                i++; j--;
            }
        } // 退出后, i > j(idx_L)
        return i; // 或j+1(ret右侧，即 cnt_L = idx_L + 1)
    }

    // ❤ 位运算判断奇偶：(num & 1) == 1奇数/0偶数
    private boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,1,1,0,1,0};
        q922_sort_array_by_parity_ii sol = new q922_sort_array_by_parity_ii();
        int[] nums2 = sol.sortArrayByParityII(nums);
        System.out.println("\nres: ");
        for (int num: nums2) System.out.print(num + " ");
    }
}
