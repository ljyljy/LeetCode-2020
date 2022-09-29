package String;

public class q01_09_string_rotation_lcci {
    public boolean isFlipedString(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        if (n == 0) return true;
//        boolean isFliped = false;
        int i = 0, j = 0;
        for (i = 0; i < n; i++) { // 遍历s1的隔板/起点
            for (j = 0; j < n; j++) { // s1: 从i开始，持续往后走j步，s2.j∈[0,n)
                if (s1.charAt((i+j) % n) != s2.charAt(j)) {
                    break;
                }
            }
            if (j == n) return true;
        }
        return false;
    }

    // 法2：kmp相关，搜索子字符串
//   字符串s+s 包含了所有 s1可以通过轮转操作得到的字符串，只需要检查 s2是否为 s + s的子字符串即可。
// Java：return s1.length() == s2.length() && (s1 + s1).contains(s2);
}
