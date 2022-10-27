package Other.math;

public class q1822_sign_of_the_product_of_an_array {
    public int arraySign(int[] nums) {
        int n = nums.length;
        int sign = 1;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) {
                sign = -sign;
            }
        }
        return sign;
    }
}
