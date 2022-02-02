package Bit;

public class q8_string_to_integer_atoi {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int n = s.length();
        int i = 0, flag = 1, res = 0;
        while (i < n && chars[i] == ' ') i++; // 1. 跳过前导0
        if (i < n) {
            if (chars[i] == '-') {
                flag = -1; // 2. 符号
                i++;
            } else if (chars[i] == '+') {
                i++;
            }
            if (i >= n || chars[i] == '+' || chars[i]=='-')
                return 0;  // 3.若出现其余+/—，返回0
        }


        while (i < chars.length && Character.isDigit(chars[i])) {
            int num = chars[i] - '0';
            // 4.判断溢出
            if (overFlow(res, num)) {
                // 判断正负，正则输出INT_MAX，负责输出INT_MIN
                return flag > 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + num; // 类比q7
            i++;
        }
        return flag * res;
    }

    // ?高级判断溢出
    private boolean overFlow (int res, int num){
        return (res * 10 / 10 != res)
                || (res == Integer.MAX_VALUE / 10 && num > 7);
    }
}
