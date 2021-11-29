package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class q3_longest_substring_without_repeating_characters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char ch2Add = s.charAt(right);
            right++;
            window.put(ch2Add, window.getOrDefault(ch2Add, 0)+1);
            // 缩小窗口
            while (window.get(ch2Add) > 1) {
                char ch2Del = s.charAt(left);
                left++;
                window.put(ch2Del, window.getOrDefault(ch2Del, 0)-1);
            }
            // 更新答案(∵缩小完毕后，肯定满足可行解条件)
            res = Math.max(res, right-left); // R-L就是len,无需+1！❤❤❤
        }
        return res;
    }
}
