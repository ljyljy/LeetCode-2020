package Sort.Quick_Sort;


import Sort.Util_algo;

public class algo_QuickSort {
    /*
    ==================== 典 型 错 误 ↓  ==============================
     */
    // ❤典型错误↓ 有重复元素将陷入死循环!!!!
    private int partition_WA(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        int i = start+1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) i++; // ❤典型错误:都严格不等于pivt!
            while (i <= j && arr[j] > pivot) j--;
            if (i < j)  // ❤典型错误1: 严格i<=j
                swap(arr, i, j); // ❤典型错误2: 遗漏i++; j--;

        }
        swap(arr, start, j);
        return j;
    }
    /*
    =============================================================
     */

    // test pass
    public void quickSort_v1(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition2(arr, start, end); // pivot_idx

        quickSort_v1(arr, start, mid); // 递归(分区+排序)
        quickSort_v1(arr, mid+1, end);
    }

    // 【推荐-模板1】法0：每次同时移动2根指针 i++ && j--
    // 返回pivot主元的下标【// test pass】
    private int partition_v0(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        // 空出arr[start], 在最后做交换（得到交换后的pivot下标：mid）
        int i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] < pivot) i++; // ❤3: 严格<（不可<=）
            while (i <= j && pivot < arr[j]) j--; // ❤3: 严格>
            if (i <= j) { // ❤1:必须严格i<=j
                swap(arr, i, j); i++; j--; // ❤2: 指针必须移动
            }
        }// 退出后 (start) [start+1, (j)]  [i, end]
        swap(arr, start, j); // [start, j(pivot)][i, end]
        return j;
    }

    // 【【【推荐】】】模板1的合并 ↑
    private void quickSort_partition_v1(int[] nums, int start, int end) {
        if (start >= end)  return;
        int i = start+1, j = end;
        int pivot = nums[start];
        while (i <= j) {
            while (i <= j && nums[i] < pivot)  i++;// ❤ 必须严格≠pivot
            while (i <= j && nums[j] > pivot) j--;
            if (i <= j) {// ❤必须<=!
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;  j--;// ❤ 必须指针移动！
            } // 退出后，[start, j(pivot)] [i, end]
        }
        swap(nums, start, j);
        quickSort_partition_v1(nums, start, j);
        quickSort_partition_v1(nums, i, end);
    }

    // 【【【推荐】】】模板1的合并2
    private void quickSort_partition_v0(int[] nums, int start, int end) {
        if (start >= end)  {return;}
        int i = start, j = end;
        int pivot = nums[(i + j) / 2];// 最后无需swap❤【only合并可写】【单独partition()不可设pivot=[mid]，易错！】
        while (i <= j) {
            while (i <= j && nums[i] < pivot)  i++; // ❤ 必须严格≠pivot
            while (i <= j && nums[j] > pivot) j--;
            if (i <= j) { // ❤必须<=!
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;  j--; // ❤ 必须指针移动！
            } // 退出后，[start, j(pivot)] [i, end]
        }
        quickSort_partition_v0(nums, start, j);
        quickSort_partition_v0(nums, i, end);
    }

    /**
     * ============================= ↓ 20210723 未测试 ====================================================
     */
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
            while (i <= j && A[i] < pivot) i++;
            while (i <= j && A[j] > pivot) j--;
            if (i <= j) {
                swap(A, i, j); i++; j--;
            }
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
