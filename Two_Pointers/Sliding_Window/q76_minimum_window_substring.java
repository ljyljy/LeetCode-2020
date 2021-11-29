package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class q76_minimum_window_substring {
    public String minWindow(String src, String tar) {
        Map<Character, Integer> need = new HashMap<>(), // 目标<字母, 词频>
                                window = new HashMap<>(); // 当前窗口内的<字母, 词频>
        for (Character c: tar.toCharArray())
            need.put(c, need.getOrDefault(c, 0)+1);

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足 need 条件的字符个数。如果 valid 和 need.size 的大小相同，则说明窗口已满足条件，已经完全覆盖了串 T。
        // 记录最小覆盖子串的起始索引及长度
        int ans_start = 0, ans_len = Integer.MAX_VALUE;
        while (right < src.length()) {
            // char2Add 是将移入窗口的字符
            char char2Add = src.charAt(right);
            right++;
            // 1、扩大窗口(右边界++)
            // 进行窗口内数据的一系列更新，until找到'可行解'
            if (need.containsKey(char2Add)) { // 如遇到tar中字母:'A/B/C'
                window.put(char2Add, window.getOrDefault(char2Add, 0) + 1);  // map更新
                // PS：使用 Java 的读者要尤其警惕语言特性的陷阱。Java 的 Integer，String 等类型判定相等应该用 equals 方法而不能直接用等号 ==，这是 Java包装类的一个隐晦细节。所以在左移窗口更新数据的时候，不能直接改写为 window.get(d) == need.get(d)，而要用 window.get(d).equals(need.get(d))，之后的题目代码同理。
//                if (window.get(char2Add) == need.get(char2Add)) // tar中某字母词频满足要求了
                if (window.get(char2Add).equals(need.get(char2Add))) // tar中某字母词频满足要求了
                    valid++;
            }

            // 3、已找到'可行解'（字母种类&个数均满足, 即valid==need.size()），持续优化——缩小窗口(左边界++)
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串()
                if (right - left < ans_len) {
                    ans_len = right - left;
                    ans_start = left;
                }

                char char2Del = src.charAt(left);
                left++;
                if (need.containsKey(char2Del)) {
                    if (window.get(char2Del).equals(need.get(char2Del))) {
                        valid--; // 即将把char2Del除去，其词频就不满足要求了
                    }
                    window.put(char2Del, window.getOrDefault(char2Del, 0)-1);
                }
            }
        }
        return ans_len == Integer.MAX_VALUE? "":src.substring(ans_start, ans_start + ans_len);
    }
}
