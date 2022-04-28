package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

// ���Ӵ���������ȣ�q9_1870, Q9_1375 / ���q315
// abcabcabc    k = 3, len = 9
// |  |     |
// l  r    len       res += (len - r + 1), ���q315������[l, r-1]~[l, len-1]
public class q9_1375_count_substr_with_k_diff_char {
    public long kDistinctCharacters(String s, int k) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        long cnt = 0;
        int valid = 0; // ��ͬ�ַ��ĸ���, ��window.size()
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;

        while (right < n) {
            char ch2Add = s.charAt(right++);
            window.put(ch2Add, window.getOrDefault(ch2Add, 0) + 1);
            if (window.get(ch2Add) == 1) {
                valid++;
            }

            while (valid >= k) {
                cnt += (n - right + 1); // ?��cnt�ĸ���λ�ã���������while(valid>k)���棬�����©��valid>k���������
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
//            if (valid == k) { // ��©��valid > kʱ�������
//                cnt += (n - right + 1);
//            }
        }
        return cnt;
    }
}
