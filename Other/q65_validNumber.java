package Other;

public class q65_validNumber {
    // �ַ���ģ��
    /**
     * ��Ч����
     * 1) ���ַ����� e/E ���зָ�:
     * - ������� e/E �������ԡ��������򡸸����������Ҳ�����ǡ�������
     * - ��������� e/E �����ο����ǡ��������򡸸�������
     * 2) check ���������жϡ��������򡸸���������
     * - '+/-' ֻ�ܳ�����ͷ��
     * - '.'������һ��
     * - ���ٴ���һ������
     */
    public boolean isNumber(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        // 1. �ҵ�e/E��Ψһ�±꣨����Ψһ��ֱ��F��
        int idx_e = -1;
        for (int i = 0; i < n; i++) {
            if (ss[i] == 'e' || ss[i] == 'E') {
                if (idx_e == -1) {
                    idx_e = i;
                } else return false; // e��Ψһ���Ƿ�����
            }
        }
        boolean ans = true;
        if (idx_e != -1) { // ��e�ָ�, [0, idx_e-1] [idx_e+1, n)
            ans &= check(ss, 0, idx_e - 1, false); // �ֱ��������Ƿ���Ч
            ans &= check(ss, idx_e + 1, n - 1, true); // e�����������
        } else {
            ans &= check(ss, 0, n-1, false);
        }
        return ans;
    }

    private boolean check(char[] ss, int start, int end, boolean mustInt) {
        if (start > end) return false;
        if (ss[start] == '+' || ss[start] == '-') start++; // ����һ��+/-
        boolean hasDot = false, hasNum = false;
        for (int i = start; i <= end; i++) {
            if (ss[i] == '.') {
                if (mustInt || hasDot) return false; // ����һ��'.'��
                hasDot = true;
            } else if (Character.isDigit(ss[i])) { // '0'~'9'
                hasNum = true;
            } else return false; // �����Ƿ��ַ�
        }
        return hasNum;
    }
}
