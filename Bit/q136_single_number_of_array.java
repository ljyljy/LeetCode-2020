package Bit;

public class q136_single_number_of_array {
    // 法2：位运算
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++)
            res ^= nums[i];  // 交换律 & 结合律 --> 相等的两数为一组
        // --> p^p=0 归零率 --> x^0=x 恒等率
        return res;
    }
}
