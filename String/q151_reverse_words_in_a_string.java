package String;

import java.util.*;

public class q151_reverse_words_in_a_string {
    public String reverseWords(String s) {
        String str = s.trim();
        List<String> words = Arrays.asList(str.split("\\s+")); // �����м�1�����ո�
        Collections.reverse(words);
        return String.join(" ", words);
    }



}
