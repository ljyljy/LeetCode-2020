package Two_Pointers.Sliding_Window;

import java.util.Arrays;

public class q395_longest_substring_with_at_least_k_repeating_characters {

    // 自定义限制条件：滑动窗口
    public int longestSubstring(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        int[] window = new int[26];

        for (int nType = 1; nType <= 26; nType++) {
            Arrays.fill(window, 0); // 每轮重置
            int left = 0, right = 0, uniqueValid = 0, uniqueCnt = 0;

            while (right < n) {
                char ch2Add = s.charAt(right++);
                int cntAdd = ++window[ch2Add - 'a'];
                if (cntAdd == 1) uniqueCnt++; // 不同字符的数量
                if (cntAdd == k) uniqueValid++; // 满足【所有字符频数>=k】

//            【如何缩小滑窗】？？ -- valid个数无限制（无法作为缩小条件）、
                // —— 利用提示：s 仅由小写英文字母组成（最多26种字母！）
                while (uniqueCnt > nType) {
                    char ch2Del = s.charAt(left++);
                    int cntDel = --window[ch2Del - 'a'];
                    if (cntDel == 0) uniqueCnt--;
                    if (cntDel == k-1) uniqueValid--; // 不是<k! 每个字符只能自减一次！
                }
                if (uniqueCnt == uniqueValid)
                    maxLen = Math.max(maxLen, right - left);
            }

        }
        return maxLen;
    }

//    // trial：普通滑动窗口
//    public int longestSubstring_trial(String s, int k) {
//        int n = s.length();
//        int maxLen = 0;
//        char[] ss = s.toCharArray();
//        int[] window = new int[26];
//        int left = 0, right = 0, valid = 0, cnt = 0;
//
//        while (right < n) {
//            char ch2Add = s.charAt(right++);
//            window[ch2Add - 'a']++;
////            if (window[ch2Add - 'a'] == 1) cnt++; 不同字符的数量
//            if (window[ch2Add - 'a'] == k) valid++; // 满足【所有字符频数>=k】
//
////            【如何缩小滑窗】？？ -- valid个数无限制（无法作为缩小条件）、
//            // —— 利用提示：s 仅由小写英文字母组成（最多26种字母！）
//        }
//    }
}
