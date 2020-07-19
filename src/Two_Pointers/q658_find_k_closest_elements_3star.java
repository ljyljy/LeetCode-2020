import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class q658_find_k_closest_elements_3star {
    // 1.双指针
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int left = 0, right = n - 1;
        int removeNums = n - k;
        while (removeNums > 0) {
            if (x - arr[left] > arr[right] - x) {
                left++;
            } else { // 左差 <= 右差（相等时，右--，删除右侧元素，优先保留左/小idx）
                right--;// 【优先保留左/小idx】
            }
            removeNums--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; ++i) { // 或 i <= right（勿忘'='!!！）
            res.add(arr[i]);
        }
        return res;
    }

    // 3.二分法2: 双指针中间往两边扩散
    public List<Integer> findClosestElements3(int[] arr, int k, int target) {
        int left = findLowerClosest(arr, target); // target<arr[0]时，left为-1
        int right = left + 1;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            if (isLeftCloser(arr, target, left, right)) {
                res.add(arr[left--]);
            } else {
                res.add(arr[right++]);
            }
        }
        Collections.sort(res);
        return res;
    }

    // 找到距离target最近的左相邻元素
    private int findLowerClosest(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < target) {
                start = mid;
            } else { // 优先考虑end(相等时)
                end = mid;
            }
        }
        if (arr[end] < target) return end; // 优先考虑end
        if (arr[start] < target) return start;
        return -1; // target <= arr[start]
    }

    private boolean isLeftCloser(int[] arr, int target, int left, int right) {
        if (left < 0) return false;
        if (right > arr.length - 1) return true;

        if (target - arr[left] != arr[right] - target) {
            return target - arr[left] < arr[right] - target;
        }
        return true;// 优先考虑左/小idx
    }



    // 2.二分法1（写法2）
    public List<Integer> findClosestElements22(int[] arr, int k, int x) {
        int n = arr.length;
        int left1 = 0, left2 =  n - k;

        while (left1 < left2) {
            int mid = left1 + (left2 - left1) / 2; // 最优左边界mid
            // 尝试从长度为 k + 1 的连续子区间删除一个元素
            // 从而定位左区间端点的边界值
            if (x - arr[mid] > arr[mid+k] - x) {// x偏右(非arr[mid+k-1]！！！)
                // 下一轮搜索区间是 [mid + 1, right]
                left1 = mid+1; // 【右半段】候选左边界
            } else {// 【左半段】x偏左/正中间，说明最优左边界还可以向左挪动！一定要挪到不能再挪为止！
                left2= mid; // 尽量要找到符合要求的【最左/小idx】
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left1; i < left1 + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }


// 2.二分法1：找到最优左边界left∈[0, n-k]后，返回arr(left:left+k)
//    假设 mid 是左边界，则当前区间覆盖的范围是 [mid, mid + k -1]. 如果发现 a[mid] 与 x 距离比 a[mid + k] 与 x 的距离要大，说明解一定在右侧。
//    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
//        int n = arr.length;
//        int start = 0, end =  n - k;
//
//        while (start + 1 < end) {
//            int mid = start + (end - start) / 2; // 最优左边界mid
//            // 尝试从长度为 k + 1 的连续子区间删除一个元素
//            // 从而定位左区间端点的边界值
//            if (x - arr[mid] > arr[mid+k] - x) { // x偏右(非arr[mid+k-1]！！！)
//                start = mid; // 【右半段】候选左边界
//            } else { // 【左半段】x偏左/正中间，说明最优左边界还可以向左挪动！一定要挪到不能再挪为止！
//                end = mid; // 尽量要找到符合要求的【最左/小idx】
//            }
//        }
//        int leftBound = 0;
//        // 当start+1 == end时，优先【最左/小idx -- start】
//        if (arr[start] <= x) leftBound = start;
//        else if (arr[end] <= x) leftBound = end;
//
//        List<Integer> res = new ArrayList<>();
//        for (int i = leftBound; i < leftBound + k; i++) {
//            res.add(arr[i]);
//        }
//        return res;
//    }


}
