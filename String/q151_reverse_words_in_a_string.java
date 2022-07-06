package String;

import java.util.*;

public class q151_reverse_words_in_a_string {
    // 法1： Java特性，推荐！
    public String reverseWords(String s) {
        String str = s.trim();
        List<String> words = Arrays.asList(str.split("\\s+")); // 正则：中间1或多个空格
        Collections.reverse(words);
        return String.join(" ", words);
    }

    // 写法2：常规
    public String reverseWords2(String s) {
        s = s.trim();
        int n = s.length();
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // 去除连续' '，只保留一个
            while (s.charAt(i) == ' ' && s.charAt(i+1) == ' ') i++;
            char ch = s.charAt(i);
            if (ch == ' ' && sb.length() != 0) {
                sb.insert(0, ' ');
                res.insert(0, sb);
                sb.setLength(0);
            } else {
                sb.append(ch);
            }
        }
        res.insert(0, sb); // 最后i == n时，还剩最后一个单词！
        return res.toString();
    }




}
