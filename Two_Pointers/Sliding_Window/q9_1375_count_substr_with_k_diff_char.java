package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

// 求子串数量，类比：q9_1870, Q9_1375 / 类比q315
// abcabcabc    k = 3, len = 9
// |  |     |
// l  r    len       res += (len - r + 1), 类比q315，即从[l, r-1]~[l, len-1]
public class q9_1375_count_substr_with_k_diff_char {
    public long kDistinctCharacters(String s, int k) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        long cnt = 0;
        int valid = 0; // 不同字符的个数, 即window.size()
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;

        while (right < n) {
            char ch2Add = s.charAt(right++);
            window.put(ch2Add, window.getOrDefault(ch2Add, 0) + 1);
            if (window.get(ch2Add) == 1) {
                valid++;
            }

            while (valid >= k) {
                cnt += (n - right + 1); // ?【cnt的更新位置，不可以在while(valid>k)后面，否则会漏掉valid>k的情况！】
                char ch2Del = s.charAt(left++);
                window.put(ch2Del, window.getOrDefault(ch2Del, 0) - 1);
                if (window.get(ch2Del) == 0) {
                    window.remove(ch2Del);
                    valid--;
                }
            }


            // WA!
//            while (valid > k) {
//                char ch2Del = s.charAt(left++);
//                window.put(ch2Del, window.getOrDefault(ch2Del, 0) - 1);
//                if (window.get(ch2Del) == 0) {
//                    window.remove(ch2Del);
//                    valid--;
//                }
//            }
//
//            if (valid == k) { // 会漏掉valid > k时的情况！
//                cnt += (n - right + 1);
//            }
        }
        return cnt;
    }
}
