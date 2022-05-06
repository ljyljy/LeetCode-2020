package String;

public class q796_rotate_string {
    // 法1：暴力
    // "ab|cde" -> "cde|ab" -- 枚举分割点"|"
    public boolean rotateString(String s, String tar) {
        int n = s.length(), m = tar.length();
        if (n != m) return false;

        for (int i = 1; i < n; i++) {
            String prev = s.substring(0, i);
            String nxt = s.substring(i);
            String newStr = nxt + prev;
            if (tar.equals(newStr)) {
                return true;
            }
        }
        return false;
    }

    // private String reverseStr(String s) {
    //     int n = s.length();
    //     char[] ss = s.toCharArray();
    //     for (int i = 0, j = n-1; i < j; i++, j--) {
    //         char tmp = ss[i];
    //         ss[i] = ss[j];
    //         ss[j] = tmp;
    //     }
    //     return new String(ss);
    // }
}
