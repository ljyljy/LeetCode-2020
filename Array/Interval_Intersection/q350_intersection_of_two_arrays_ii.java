package Array.Interval_Intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q350_intersection_of_two_arrays_ii {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); Arrays.sort(nums2);
        int n1 = nums1.length, n2 = nums2.length;
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++; j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else j++; // nums2[i] < nums1[j]
        }
        int[] ans = new int[res.size()];
        for (int k = 0; k < res.size(); k++) {
            ans[k] = res.get(k);
        }
        return ans;
    }
}
