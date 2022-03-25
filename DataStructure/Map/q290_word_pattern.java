package DataStructure.Map;

import java.util.*;

public class q290_word_pattern {
    // ¿‡±»q205
    public boolean wordPattern(String pattern, String s) {
        int cnt = pattern.length();
        Map<Character, String> ch2Word = new HashMap<>();
        Map<String, Character> word2ch = new HashMap<>();
        String[] words = s.split("\\s");
        int cnt2 = words.length;
        if (cnt != cnt2) return false;

        char[] chs = pattern.toCharArray();
        for (int i = 0; i < cnt; i++) {
            if ((ch2Word.containsKey(chs[i]) && !ch2Word.get(chs[i]).equals(words[i])) ||
                    (word2ch.containsKey(words[i]) && word2ch.get(words[i]) != chs[i])) {
                return false;
            }
            ch2Word.put(chs[i], words[i]);
            word2ch.put(words[i], chs[i]);
        }
        return true;
    }
}
