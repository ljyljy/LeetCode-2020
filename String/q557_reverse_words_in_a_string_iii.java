package String;

public class q557_reverse_words_in_a_string_iii {
    public String reverseWords(String s) {
        String[] words = s.split("\\s");
        StringBuilder sb = new StringBuilder();
        for (String word: words) {
            sb.append(reverse(word) + " ");
        }
        // sb.deleteCharAt(sb.length()-1); // 去除最后一个空格
        return sb.toString().trim();
    }

    private String reverse(String word) {
        int n = word.length();
        char[] chs = word.toCharArray();
        for (int i = 0, j = n-1; i < j; i++, j--) {
            char tmp = chs[i];
            chs[i] = chs[j];
            chs[j] = tmp;
        }
        return new String(chs);
    }
}
