package Sort.Quick_Sort;


import Sort.Util_algo;

public class algo_QuickSort {
    public void quickSort_v1(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition2(arr, start, end); // pivot_idx

        quickSort_v1(arr, start, mid); // 递归(分区+排序)
        quickSort_v1(arr, mid+1, end);
    }

    // 【推荐-模板1】法0：每次同时移动2根指针 i++ && j--
    // 返回pivot主元的下标
    private int partition_v0(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        // 空出arr[start], 在最后做交换（得到交换后的pivot下标：mid）
        int i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) i++;
            while (i <= j && pivot < arr[j]) j--;
            if (i < j) {
                swap(arr, i, j); // i++; j--; // ←指针移动与否 都行！
            }
        }// 退出后 (start) [start+1, (j)]  [i, end]
        swap(arr, start, j); // [start, j(pivot)][i, end]
        return j;
    }

    // 模板1的合并 ↑【推荐】
    private void quickSort_partition_v0(int[] nums, int start, int end) {
        if (start >= end)  {return;}
        int left = start, right = end;
        int pivot = nums[(left + right) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot)  left++;
            while (left <= right && nums[right] > pivot) right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;  right--;
            } // 退出后，[start, right(pivot)] [left, end]
        }
        quickSort_partition_v0(nums, start, right);
        quickSort_partition_v0(nums, left, end);
    }

    //【模板1-优化】在L,R,mid之间，选一个中间值作为主元
    public int partition2(int[] A, int L, int R) {
        int mid = L + ((R - L) >> 1);//中间下标
        int mid_ = -1;//中值的下标
        if ((A[R] <= A[L] && A[L] <= A[mid] ) || (A[mid] <= A[L] && A[L] <= A[R] )) {
            mid_ = L; // p是中位数
        } else if ((A[R] <= A[mid] && A[R] >= A[L]) || A[R] >= A[mid] && A[R] <= A[L]) {
            mid_ = R;  // r是中位数
        } else mid_ = mid;
        swap(A, L, mid_); // 将最左元素L与mid交换, 使得pivot=arr[start]
        // 常规模板1【推荐】↓
        int pivot = A[L];
        int i = L + 1, j = R;
        while (i <= j) {
            while (i <= j && A[i] <= pivot) i++;
            while (i <= j && A[j] > pivot) j--;
            if (i < j) swap(A, i, j);
        } // 退出后，pivot(L) [L+1, (j)] [i, R]
        swap(A, L, j); // [L, j-1] pivot(j) [i, R]
        return j;
    }

    // 【模板2】法1：一遍扫描：一次只移动一根指针 i 或 j
    private int partition_v1(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        int i = start + 1, j = end;

        while (i <= j) {
            if (arr[i] <= pivot) i++;
            else { // i==j && arr[i] > pivot时：与自己swap，j--
                swap(arr, i, j);
                j--;
            }// 退出后：i > j && 【pivot(start')】 [start+1, 【j'】] [i, end]
        } // ↓ 将pivot从最左边挪至mid： [【start(j')】, j-1] 【pivot(j)】 [i, end]
        swap(arr, start, j); // 保证pivot左侧都比自己小，右侧都大于自己
        return j;
    }


    // 【模板0】法0-2：一遍扫描：一次只移动一根指针 i 或 j
    public void quickSort_v2(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition_v2(arr, start, end); // pivot_idx
        quickSort_v2(arr, start, mid); // 递归(分区+排序)
        quickSort_v2(arr, mid+1, end);
    }

    private int partition_v2(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start + end >> 1];
        int i = start - 1, j = end + 1;
        while (i < j) {
            do i++; while (arr[i] < pivot);
            do j--; while (pivot < arr[j]);
            if (i < j) swap(arr, i, j);
        }
        return j; // i == j == pivot_idx
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = Util_algo.getRandomArr(10, 1, 20);
        Util_algo.print(arr);
        algo_QuickSort sol = new algo_QuickSort();
//        sol.quickSort_v2(arr, 0, arr.length-1);
        sol.quickSort_partition_v0(arr, 0, arr.length-1);
        Util_algo.print(arr);

    }
}
