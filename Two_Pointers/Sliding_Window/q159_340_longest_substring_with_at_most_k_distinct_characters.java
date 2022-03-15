package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class q159_340_longest_substring_with_at_most_k_distinct_characters {
    // ¿‡±»q159,3,30
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> window = new HashMap<>();
        char[] ss = s.toCharArray();
        int left = 0, right = 0, n = ss.length;

        int maxLen = 0;
        while (right < n) {
            char ch2Add = ss[right++];
            window.put(ch2Add, window.getOrDefault(ch2Add, 0) + 1);

            while (window.size() > k) {
                char ch2Del = ss[left++];
                window.put(ch2Del, window.getOrDefault(ch2Del, 0) - 1);
                if (window.get(ch2Del) <= 0) {
                    window.remove(ch2Del);
                }
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
