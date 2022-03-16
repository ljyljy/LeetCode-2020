package Array;

public class q169_majority_element {
//    思路：使用res & cnt计数，相同cnt++不同cnt--，最后返回res。 因为众数超过总数的一半，所以不会被减到0以下。
    // 即：不同的数可以互相抵消，众数一定是最后落单的那个
    public int majorityElement(int[] nums) {
        int res = Integer.MIN_VALUE, cnt = 0;
        for (int num: nums) {
            if (cnt == 0) {
                res = num;
                cnt = 1;
            } else if (num == res) {
                cnt++;
            } else cnt--;
        }
        return res;
    }

}
