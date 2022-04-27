package Bit;

public class q201_bitwise_and_of_numbers_range {
    public int rangeBitwiseAnd(int left, int right) {
        // ���⣺left��right������0����˵�32λ���ÿ���һ����0���Ǹ�����
        int mask = 1 << 30; // �з��ţ�1 << 30: ��31λΪ1��(1 << 31: INT_MIN)
        int res = 0;
        while (mask > 0 && (mask & left) == (mask & right)) {
            res |= mask & left;
            mask >>= 1;
        } // �����bitǰ׺res��Ϊ����
        return res;
    }
}
