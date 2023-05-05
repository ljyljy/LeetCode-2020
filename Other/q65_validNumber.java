package Other;

public class q65_validNumber {
    // 字符串模拟
    /**
     * 有效数字
     * 1) 将字符串以 e/E 进行分割:
     * - 如果存在 e/E ：左侧可以「整数」或「浮点数」，右侧必须是「整数」
     * - 如果不存在 e/E ：整段可以是「整数」或「浮点数」
     * 2) check 函数用于判断「整数」或「浮点数」：
     * - '+/-' 只能出现在头部
     * - '.'最多出现一次
     * - 至少存在一个数字
     */
    public boolean isNumber(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        // 1. 找到e/E的唯一下标（若不唯一，直接F）
        int idx_e = -1;
        for (int i = 0; i < n; i++) {
            if (ss[i] == 'e' || ss[i] == 'E') {
                if (idx_e == -1) {
                    idx_e = i;
                } else return false; // e不唯一，非法数字
            }
        }
        boolean ans = true;
        if (idx_e != -1) { // 以e分割, [0, idx_e-1] [idx_e+1, n)
            ans &= check(ss, 0, idx_e - 1, false); // 分别检查两段是否有效
            ans &= check(ss, idx_e + 1, n - 1, true); // e后必须是整数
        } else {
            ans &= check(ss, 0, n-1, false);
        }
        return ans;
    }

    private boolean check(char[] ss, int start, int end, boolean mustInt) {
        if (start > end) return false;
        if (ss[start] == '+' || ss[start] == '-') start++; // 至多一个+/-
        boolean hasDot = false, hasNum = false;
        for (int i = start; i <= end; i++) {
            if (ss[i] == '.') {
                if (mustInt || hasDot) return false; // 至多一个'.'！
                hasDot = true;
            } else if (Character.isDigit(ss[i])) { // '0'~'9'
                hasNum = true;
            } else return false; // 其他非法字符
        }
        return hasNum;
    }
}
