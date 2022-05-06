package String.KMP;

// 类比：ACW831, Q28, 214
public class q214_shortest_palindrome {
    // 法2：KMP - O(n+m)：
    public String shortestPalindrome(String s) {
        String s0 = s;
        StringBuffer rev_s = new StringBuffer(s).reverse();
//        String rev_s = reverseStr(s);
        s = " " + s + "#" + rev_s.toString(); // 开头KMP哨兵！
        String p = s;
        int n = s.length(), m = p.length();
        char[] ss = s.toCharArray(), pp = p.toCharArray();

        // 1) 构造next数组
        int[] next = new int[m]; // m包含了哨兵，无需m+1（与q28，acw831同！）
        for (int i = 2, j = 0; i <= m-1; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }

//        // 2) 匹配 - 无需
//        for (int i = 1, j = 0; i <= n; i++) {
//            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
//            if (ss[i] == pp[j+1]) j++;
//            if (j == m) {
////                System.out.print(i - m); // 匹配成功的起始idx
//                j = next[j];
//            }
//        }
        int maxLen = next[m-1]; // 最长回文前缀的长度, 即以j=m=end为终点的最长匹配前缀长度
        System.out.println("KMP.maxLen=" + maxLen);
        String fillPrefix = new StringBuilder(s0.substring(maxLen)).reverse().toString(); // 非回文的后缀.翻转=>作为非回文的前缀

        return fillPrefix + s0;

    }

    // 法1：暴力[掌握] - O(n^2)： str[0:i) + revStr[n-i:n)
    public String shortestPalindrome_BF(String s) {
        int n = s.length();
        String rev_s = reverseStr(s); // (anana)bc -> cb(anana)
        for (int i = n; i >= 0; i--) {
            // i = n-2时，str[0:n-2) == "anana" == rev_s[2:n)
            if (s.substring(0, i).equals(rev_s.substring(n-i))) {
                return rev_s.substring(0, n-i) + s;
            }
        }
        return "";
    }

    private String reverseStr(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        for (int i = 0, j = n-1; i < j; i++, j--) {
            char tmp = ss[i];
            ss[i] = ss[j];
            ss[j] = tmp;
        }
        return new String(ss);
    }

    public static void main(String[] args) {
        q214_shortest_palindrome sol = new q214_shortest_palindrome();

        String str = "ananab";
        System.out.println(sol.shortestPalindrome_BF(str));
        System.out.println(sol.shortestPalindrome(str));
        System.out.println("============");

        String str2 = "ananabc";
        System.out.println(sol.shortestPalindrome_BF(str2));
        System.out.println(sol.shortestPalindrome(str2));
        System.out.println("============");

        String str3 = "abcd";
        System.out.println(sol.shortestPalindrome_BF(str3));
        System.out.println(sol.shortestPalindrome(str3));
        System.out.println("============");
    }
}
