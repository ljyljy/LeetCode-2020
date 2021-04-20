package String;

public class q28_implement_strstr {
    // 法1：BF双指针 - O(m*n)
    public int strStr(String src, String pp) {
        if (pp.isEmpty()) return 0;
        int n0 = src.length(), n1 = pp.length();
        char[] s = src.toCharArray(), p = pp.toCharArray();
        for (int i = 0; i <= n0 - n1; i++) {// 枚举原串的「发起点」
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int ii = i, jj = 0;
            while (ii < n0 && jj < n1 && s[ii] == p[jj]) {
                ii++; jj++;
            }
            if (jj == n1) return i; // i是匹配起始idx，而非ii <-匹配结尾idx+1
        }
        return -1;
    }
    // 法2：KMP

}
