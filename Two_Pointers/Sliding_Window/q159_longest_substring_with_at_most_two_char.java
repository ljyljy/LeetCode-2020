package Two_Pointers.Sliding_Window;

import java.util.*;

public class q159_longest_substring_with_at_most_two_char {
    // ¿‡±»q3°¢30°¢340
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> window = new HashMap<>();
        char[] ss = s.toCharArray();
        int left = 0, right = 0, n = s.length();

        int maxLen = 0;
        while (right < n) {
            char ch2Add = ss[right++];
            window.put(ch2Add, window.getOrDefault(ch2Add, 0) + 1);

            while (window.size() > 2) {
                char ch2Del = ss[left++];
                window.put(ch2Del, window.getOrDefault(ch2Del, 0) - 1);
                if (window.get(ch2Del) <= 0)
                    window.remove(ch2Del);
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
