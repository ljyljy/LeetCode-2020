package String.KMP;

public class q459_repeated_substring_pattern {
    // 法1：技巧--双倍字符串， 应用：q459,【a471】
    /**
     * 题目459的思路大致如下：
     * 令 t = s + s, 从下标1的字符开始查找字符串s， 找到下标idx，
     * 例如：s="abc", 则"t=a(bc abc)".indexOf("abc", 1)=3=len(s)=idx，说明s无重复
     *       s="abcabc", 则"t=a(bc[abc abc]abc)".indexOf("abcabc", 1)=3<len(s)=6，说明s存在重复
     */
    public boolean repeatedSubstringPattern0(String s) {
        // "a[bb abb]" -> “[]”内第一个abb出现在整体idx=len(s)处
        // "a[bc(abc abc)abc]" -> “[]”内第一个abcabc出现在整体idx=len(abc)处
        // System.out.println((s + s).indexOf(s, 1));
        return (s + s).indexOf(s, 1) != s.length();
    }

    // 法2：KMP变种 - 只求next数组
    public boolean repeatedSubstringPattern(String s) {
        int m = s.length();
        String p = " " + s;
        int[] next = new int[m+1];

        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j+1)) j = next[j];
            if (p.charAt(i) == p.charAt(j+1)) j++;
            next[i] = j;
        }
        int maxLen = next[m]; // 最长前后缀的长度，如"abcdabcd abcd" -> next[m] = 8
        System.out.println(maxLen);
        return maxLen != 0 && m % (m - maxLen) == 0;
    }
}
