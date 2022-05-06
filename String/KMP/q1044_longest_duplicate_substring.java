package String.KMP;

import java.util.HashSet;
import java.util.Set;

// 类比：ACW831, Q28, 214, 1044
public class q1044_longest_duplicate_substring {
    // 法2--二分答案【子串长度】 + 字符串哈希
    int n;
    Set<Long> set2 = new HashSet<>();
    int base = 131313;
    long[] h, p;
    public String longestDupSubstring_strHash(String s) {
        n = s.length();
        String res = "";
        h = new long[n+10]; p = new long[n+10];

        // 预处理：字符串哈希【类似<前缀和>】
        p[0] = 1; h[0] = 0;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i-1] * base + s.charAt(i-1);
            p[i] = p[i-1] * base;
        }

        int start = 0, end = n;// 必须是n！否则"aa"输出""(AK="a")
        // [L, mid] [mid+1, R]
        while (start < end) {
            int mid = start + end >> 1;
            String tmp = check2(s, mid);
            if (!tmp.isEmpty())  start = mid+1; // 子串长度可以加大(右区间)
            else end = mid;
            res = tmp.length() > res.length()? tmp: res; // res记录最长子串
        }
        return res;
    }

    private String check2(String s, int len) {
        for (int i = 1; i+len-1 <= n; i++) {
            int j = i+len-1;
            String subStr = s.substring(i-1, j); // str[i-1, i-1+len)
            long hash = h[j] - h[i-1] * p[j-(i-1)];
            if (set2.contains(hash)) return subStr;
            set2.add(hash);
        }
        return "";
    }


    // 法1--TLE(面试掌握): 二分答案【子串长度】 + 普通哈希
    Set<String> set = new HashSet<>();
    public String longestDupSubstring_TLE(String s) {
        n = s.length();
        int start = 0, end = n; // 必须是n！否则"aa"输出""(AK="a")
        String res = "";
        // [L, mid] [mid+1, R]
        while (start < end) {
            int mid = start + end >> 1;
            String tmp = check(s, mid);
            if (!tmp.isEmpty()) start = mid+1; // 子串长度可以加大(右区间)
            else end = mid;
            res = tmp.length() > res.length()? tmp: res; // res记录最长子串
        }
        return res;
    }

    private String check(String s, int len) { // 截取子串长度==len
        for (int i = 0; i+len <= n; i++) { // 枚举分割点i
            String subStr = s.substring(i, i+len);
            if (set.contains(subStr)) return subStr;
            set.add(subStr);
        }
        return "";
    }

    // 法3：KMP - 枚举p = s子串，遍历next[j]，记录最长前后缀长度
    public String longestDupSubstring_KMP_TLE(String s) {
        int n = s.length();
        String maxSub = ""; s = " " + s;
        char[] ss = s.toCharArray();
        for (int k = 1; k <= n; k++) {
            String p = s.substring(k, n+1); // 枚举p为s[k:n)
            p = " " + p;
            int m = p.length();
            char[] pp = p.toCharArray();
            // 构造next数组
            int[] next = new int[m];
            for (int i = 2, j = 0; i <= m-1; i++) {
                while (j > 0 && pp[i] != pp[j+1]) j = next[j];
                if (pp[i] == pp[j+1]) j++;
                next[i] = j;
            }

            // 对比q218：只需要取next[m-1]！
            // 坑：不可以只看next[m-1]！因为不能只考虑以j=m-1为结尾的前后缀！
            // 需要考虑p的所有子串中，最长前后缀长度！即遍历next[]！
            for (int len: next) {
                // 截取时去掉开头的哨兵   ↓ (idx=0处)，因此截取需要整体后移1位(+1)
                String tmp = p.substring(1, len+1); // 或 p[m-next[m-1]:m)
                //            String tmp = p.substring(m-next[m-1], m);
//                System.out.println(tmp);
                maxSub = tmp.length() > maxSub.length()? tmp: maxSub;
            }
        }
        return maxSub;
    }

    public static void main(String[] args) {
        String str = "nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy"; // "banana";
        q1044_longest_duplicate_substring sol = new q1044_longest_duplicate_substring();
        System.out.println(sol.longestDupSubstring_strHash(str));

        System.out.println(sol.longestDupSubstring_TLE(str));

        System.out.println(sol.longestDupSubstring_KMP_TLE(str));
    }
}
