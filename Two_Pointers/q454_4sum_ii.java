package Two_Pointers;

import java.util.HashMap;
import java.util.Map;

public class q454_4sum_ii {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int cnt = 0;
        int n = nums1.length;
        Map<Integer, Integer> map = new HashMap<>(); // <sum1+2, cnt>
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum2 = nums3[i] + nums4[j]; // sum34 = 0 - sum12
                cnt += map.getOrDefault(-sum2, 0);
            }
        }
        return cnt;
    }
}
