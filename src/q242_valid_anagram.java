import java.util.Arrays;

public class q242_valid_anagram {

    // 2.数组
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int cnt : counter) {
            if (cnt != 0)
                return false;
        }
        return true;
    }

    // 1.暴力 空间O(1);  时间O(nlogn)，假设 n 是 s 的长度，排序O(nlogn) 和比较两个字符串的成本O(n)。排序时间占主导地位，总体时间复杂度为 O(nlogn)。
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

}
