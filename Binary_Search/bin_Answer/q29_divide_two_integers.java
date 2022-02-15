package Binary_Search.bin_Answer;

public class q29_divide_two_integers {
    // 3*(2^1) <  10 < 3*(2^2) -- ����(cnt��divisor)/���ٳ�
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;
    public int divide(int dividend, int divisor) {
        // ������������->��MAX����|MIN|=|MAX|+1��
        if (dividend == MIN && divisor == -1) return MAX;
        // ��¼����ķ���
        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }
        // ȫ��ת���ɸ�������ֹ���
        dividend = dividend > 0? -dividend:dividend;
        divisor = divisor > 0? -divisor:divisor;

        int ans = div(dividend, divisor);
        return sign > 0? ans: -ans;
    }

    // ���˷���ע�ⶼ�Ǹ����ˣ��Ƚϵ�ʱ���������෴��(|divided| >= |divisor|)
    // �򵥵���⣬����ÿ�μ�ȥ������2^x.����(tmp == divisor*(2^x) = divisor*cnt)��ʣ�µĲ��ּ����������Ĺ������
    private int div(int dividend, int divisor) {
        int ans = 0;
        while (dividend <= divisor) {
            int tmp = divisor, cnt = 1;
            // ����ע�ⲻҪд�� tmp + tmp >= dividend������д�ӷ��п��ܻ����������ѭ��
            while (tmp >= dividend - tmp) { // ����tmp + tmp <= dividend���ߵ� & ����
                // tmp �� cnt ÿ�ζ�Ӧ����һ����*2�������Խб���
                cnt <<= 1; // �� 2^x
                tmp <<= 1; // ��
            }
            // ��������ȥ������ 2^x ������Ϊ�µı�����
            dividend -= tmp;
            ans += cnt;
        }
        return ans;
    }
}
