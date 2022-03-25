package DataStructure.Map;

import java.util.*;

public class q205_isomorphic_strings {
    // ¿‡±»q290
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int n1 = s.length(), n2 = t.length();
        if (n1 != n2) return false;

        for (int i = 0; i < n1; i++) {
            char ch_s = s.charAt(i), ch_t = t.charAt(i);
            if ((s2t.containsKey(ch_s) && s2t.get(ch_s) != ch_t) ||
                    (t2s.containsKey(ch_t) && t2s.get(ch_t) != ch_s)) {
                return false;
            }
            s2t.put(ch_s, ch_t);
            t2s.put(ch_t, ch_s);
        }
        return true;
    }
}
