package Array;

import java.util.ArrayList;
import java.util.List;

public class q163_missing_ranges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        // Ԥ������low-1, up+1��Ϊ�ڱ�����nums�߽磡?
        int[] arr = new int[n+2];
        System.arraycopy(nums, 0, arr, 1, n);
        arr[0] = lower-1;
        arr[arr.length-1] = upper+1;

        List<String> res = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            if (arr[i] != arr[i-1] + 1) {
                sb.append(arr[i-1]+1);
                if (arr[i] - arr[i-1] != 2) { // �磺 1->3��ֻȱʧ2
                    sb.append("->" + (arr[i]-1));
                }
                res.add(sb.toString());
            }
        }

        return res;
    }
}
