package Bit;

public class q8_string_to_integer_atoi {
    // д��1
    private int flag, n;
    public int myAtoi(String s) {
        s = s.trim();
        n = s.length(); flag = 1;

        int i = checkSign(s);
        if (i == -1) return 0; // �Ƿ�����
        int res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int num = s.charAt(i) - '0';
            if (isOverFlow(res, num)) {
                return flag == -1? Integer.MIN_VALUE: Integer.MAX_VALUE;
            }
            res = res * 10 + num;
            i++;
        }
        return flag * res;
    }

    private boolean isOverFlow(int res, int num) { // �磺"-91283472332" -> INT_MIN
        return (res *10 / 10 != res)
                || (res == Integer.MAX_VALUE / 10 && num > 7);  // +2147483647, -2147483648
    }

    private int checkSign(String s) {
        int i = 0;
        if (i < n) {
            char c = s.charAt(i);
            if (c == '+') {
                i++;
            } else if (c == '-') {
                flag = -1;
                i++;
            }
            if (i >= n) return -1; // ֻ��ǰ���ո� & �������ǺϷ�����
            char nxt_c = s.charAt(i); // ǰ�᣺i < n
            if (nxt_c == '+' || nxt_c == '-') return -1;
        }
        return i; // ���ź󣬽�������
    }

    // д��2
    public int myAtoi_old(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int n = s.length();
        int i = 0, flag = 1, res = 0;
        while (i < n && chars[i] == ' ') i++; // 1. ����ǰ���ո�
        if (i < n) { // ����ֻ����������ǰ����1������������while
            if (chars[i] == '-') {
                flag = -1; // 2. ����
                i++;
            } else if (chars[i] == '+') {
                i++;
            }
            if (i >= n || chars[i] == '+' || chars[i]=='-')
                return 0;  // 3.����������+/��������0
        }


        while (i < chars.length && Character.isDigit(chars[i])) {
            int num = chars[i] - '0';
            // 4.�ж����
            if (overFlow(res, num)) {
                // �ж��������������INT_MAX���������INT_MIN
                return flag > 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + num; // ���q7
            i++;
        }
        return flag * res;
    }

    // ?�߼��ж����
    private boolean overFlow (int res, int num){
        return (res * 10 / 10 != res)
                || (res == Integer.MAX_VALUE / 10 && num > 7);
    }
}
