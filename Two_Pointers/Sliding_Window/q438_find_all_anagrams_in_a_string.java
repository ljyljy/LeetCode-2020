package Two_Pointers.Sliding_Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q438_find_all_anagrams_in_a_string {
    // 见labuladong 滑动窗口例题3
    public List<Integer> findAnagrams(String src, String tar) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char ch: tar.toCharArray())
            need.put(ch, need.getOrDefault(ch, 0)+1);

        int left = 0, right = 0;
        int valid = 0;
        while (right < src.length()) {
            char char2Add = src.charAt(right);
            right++;
            if (need.containsKey(char2Add)) {
                window.put(char2Add, window.getOrDefault(char2Add, 0)+1);
                if (need.get(char2Add).equals(window.get(char2Add)))
                    valid++;
            }

            // 找到可行解，最优化-缩小窗口
            while (right - left + 1 > tar.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char char2Del = src.charAt(left);
                left++;
                if (need.containsKey(char2Del)) {
                    if (need.get(char2Del).equals(window.get(char2Del)))
                        valid--;
                    window.put(char2Del, window.getOrDefault(char2Del, 0)-1);
                }

            }
        }
        return res;
    }
}
