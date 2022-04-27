package Bit;

public class q201_bitwise_and_of_numbers_range {
    public int rangeBitwiseAnd(int left, int right) {
        // 题意：left、right都大于0，因此第32位不用看，一定是0（非负数）
        int mask = 1 << 30; // 有符号，1 << 30: 第31位为1。(1 << 31: INT_MIN)
        int res = 0;
        while (mask > 0 && (mask & left) == (mask & right)) {
            res |= mask & left;
            mask >>= 1;
        } // 最长公共bit前缀res即为所求。
        return res;
    }
}
