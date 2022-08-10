import java.lang.reflect.Array;
import java.util.*;

public class tmp {

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE, sum = 0;
        while (right < n) {
            sum += nums[right++];


            while (sum >= k) {
                System.out.println(sum + ",, minlen:" + minLen);
                int curLen = right - left;
                minLen = Math.min(minLen, curLen);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE? -1: minLen;
    }

}
