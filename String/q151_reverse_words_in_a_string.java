package String;

import java.util.*;

public class q151_reverse_words_in_a_string {
    // ��1�� Java���ԣ��Ƽ���
    public String reverseWords(String s) {
        String str = s.trim();
        List<String> words = Arrays.asList(str.split("\\s+")); // �����м�1�����ո�
        Collections.reverse(words);
        return String.join(" ", words);
    }

    // д��2������
    public String reverseWords2(String s) {
        s = s.trim();
        int n = s.length();
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // ȥ������' '��ֻ����һ��
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
        res.insert(0, sb); // ���i == nʱ����ʣ���һ�����ʣ�
        return res.toString();
    }




}
