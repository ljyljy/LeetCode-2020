package Binary_Search;

public class q81_search_in_rotated_sorted_array_ii {
    // v1-1: 两次二分v1
    public boolean search_1v1(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        // 二分1st - 找旋转点pivot 【面试时需多跑一些test case，调试】
        // ❤ 预处理：恢复二段性（vs nums[0] / nums[-1]）
        //（旋转后，首尾元素可能重复），否则无法二分！
        int j = n-1; // ↓ 使j指向最后一个(不等于nums[0])的元素。
        while (j>0 && nums[0] == nums[j]) j--; // 保证退出j>=0
        int pivot = getPivot1(nums, nums[0], j) + 1; // 旋转序列pivot=返回值+1
        // 二分2nd - 找目标值
        if (pivot == n) // 或 if(nums[0] <= nums[j]) // 升序序列
            return binSearch(nums, 0, n-1, target) != -1;
        int res = -1; // 也可以无脑左右区间共搜2次，不预判
        if (target < nums[0]) // 在右半边(包括pivot==target)
            res = binSearch(nums, pivot, n-1, target);  // j会出错
        else res = binSearch(nums, 0, pivot-1, target); // 在左半边(包括target == nums[0])

        return res != -1;
    }

    private int getPivot1(int[] nums, int target, int end) {
        // [3,3,3,4,5,6(ret), 0(pivot), 0, 0,1,2]
        //  ↑ - [l, mid-1(ret不会越界)], [mid(ret+1 升序则越界), r]
        // [3,3,3,4,5,6(ret), null(pivot)] -- 升序序列的pivot在末尾(越界)
        int l = 0, r = end;
        while (l < r) {
            int mid = l + r + 1 >> 1; // mid' == ret+1(向上取整)
            if (nums[mid] >= target) { // pivot在右区间
                l = mid;
            }else r = mid - 1;
        } // ↓∵ mid'=ret+1, ret∈[L,R] ∴ pivot=ret+1=mid'可能越界❤
        return l; // 或r (+1=旋转.pivot= mid = l/2 + 1 向上取整！)
    }

    private int binSearch(int[] nums, int l, int r, int target) {
        while (l <= r) { // [l, mid-1], mid, [mid+1, r]
            int mid = l + r >> 1;
            if (target == nums[mid]) return mid;
            else if (target > nums[mid]) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    // v1-2: 两次二分v2, 区别在于getPivot()使用的二分模板不同❤
    public boolean search_1v2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        // 二分1st - 找旋转点pivot 【面试时需多跑一些test case，调试】
        // ❤ 预处理：恢复二段性（vs nums[0]）
        //（旋转后，首尾元素可能重复），否则无法二分！
        int j = n-1; // ↓ 使j指向最后一个(不等于nums[0])的元素。
        while (j>0 && nums[0] == nums[j]) j--;
        // 二分2nd - 找目标值
        int pivot = getPivot2(nums, nums[0], j); // 旋转序列pivot=返回值
        System.out.println("idx=" + pivot + ", num="+ nums[pivot]);
        if (nums[0] <= nums[j]) // 升序序列
            return binSearch2(nums, target, 0, j);
        else if (target < nums[0])// (nums[pivot] <= target && target <= nums[j])
            return binSearch2(nums, target, pivot, j);// target在右半段(小数区间)
        else return binSearch2(nums, target, 0, pivot-1);
    }

    private int getPivot2(int[] nums, int target, int end) {
        // [3,3,3,4,5,6, 0(ret|pivot), 0, 0,1,2] -- [l, mid-1], [mid(ret永不越界), r]
        // [3,3,3,4,5,6(ret|pivot) ] -- 升序序列的pivot=n-1(但旋转后pivot也有可能为n-1，如[3,1(pivot)])
        int start = 0; // [l, mid], [mid+1, r]
        while (start < end) {
            int mid = start + end >> 1; // mid'==ret(∵向下取整,无需+1)
            if (nums[mid] >= target)
                start = mid+1;
            else end = mid;
        }
        return start; // ∵mid' == ret∈[L,R] ∴永不越界
    }

    private boolean binSearch2(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + end >> 1;
            if (nums[mid] == target) return true;
            else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }

    // v3 - 一次二分 【推荐 不易错】
    public boolean search_2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        int j = n-1; // ❤ 预处理：恢复二段性（vs nums[0]）
        while (j>0 && nums[0] == nums[j]) j--;

        return binSearch_onceKO(nums, target, 0, j) != -1;
    }

    private int binSearch_onceKO(int[] nums, int target, int l, int r) {
        while (l + 1 < r) {
            int mid = l + r >> 1;

            if (nums[mid] == target) return mid;
            // 分别在左右半段【L/R vs mid】中，找出包含target的升序的子数列
            if (nums[l] == nums[mid]) { // 该判断不可少！针对左半段的重复元素
                l++; continue;
            }else if (nums[l] < nums[mid]) {// 左半段
                if (nums[l] <= target && target <= nums[mid])
                    r = mid; // target∈[l, mid]，且子序列严格升序
                else l = mid;
            }else { // 右半段
                if (nums[mid] <= target && target <= nums[r])
                    l = mid; // target∈[mid, r] 严格升序
                else r = mid;
            }
        }
        if (nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }

    public static void main(String[] args) {
        q81_search_in_rotated_sorted_array_ii sol = new q81_search_in_rotated_sorted_array_ii();
        int[] nums = {2,2,3,3,3,4,5,6,
                      0,0,0,1,2,2,2,2};
        System.out.println(sol.search_2(nums, 7));
    }
}
