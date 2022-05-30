package Array.Interval_Intersection;

import java.util.*;

public class q349_intersection_of_two_arrays {
    // 法1：二分 - O(nlogn)
    public int[] intersection_v1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(nlogn)
        Arrays.sort(nums2);
        Set<Integer> res = new HashSet<>();
        for (int num1: nums1) {
            if (binSearch(nums2, num1)) {
                res.add(num1);
            }
        }
        int[] res_ = new int[res.size()];
        int k = 0;
        for (int num: res) res_[k++] = num;
        return res_;
    }

    private boolean binSearch(int[] nums, int x) {
        int n = nums.length;
        int start = 0, end = n-1;
        while (start < end) { // [L, mid] [mid+1, R]
            int mid = start + end >> 1;
            if (x == nums[mid]) {
                return true;
            } else if (nums[mid] < x) {
                start = mid + 1;
            } else end = mid;
        }
        return nums[start] == x;
    }

    // 法2：双指针 - O(nlogn) + O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(nlogn)
        Arrays.sort(nums2);
        Set<Integer> res = new HashSet<>();
        int i = 0, j = 0, n1 = nums1.length, n2 = nums2.length;
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else { // ==
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res_ = new int[res.size()];
        int k = 0;
        for (int num: res) res_[k++] = num;
        return res_;
    }

}
