package String;

import java.util.*;

public class q151_reverse_words_in_a_string {
    public String reverseWords(String s) {
        String str = s.trim();
        List<String> words = Arrays.asList(str.split("\\s+")); // 正则：中间1或多个空格
        Collections.reverse(words);
        return String.join(" ", words);
    }



}
