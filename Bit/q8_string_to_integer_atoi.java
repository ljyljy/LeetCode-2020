package Bit;

public class q8_string_to_integer_atoi {
    // 写法1
    private int flag, n;
    public int myAtoi(String s) {
        s = s.trim();
        n = s.length(); flag = 1;

        int i = checkSign(s);
        if (i == -1) return 0; // 非法数字
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

    private boolean isOverFlow(int res, int num) { // 如："-91283472332" -> INT_MIN
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
            if (i >= n) return -1; // 只有前导空格 & ±，不是合法数字
            char nxt_c = s.charAt(i); // 前提：i < n
            if (nxt_c == '+' || nxt_c == '-') return -1;
        }
        return i; // 符号后，紧跟数字
    }

    // 写法2
    public int myAtoi_old(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int n = s.length();
        int i = 0, flag = 1, res = 0;
        while (i < n && chars[i] == ' ') i++; // 1. 跳过前导空格
        if (i < n) { // 题中只允许在数字前紧跟1个±，无需用while
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
