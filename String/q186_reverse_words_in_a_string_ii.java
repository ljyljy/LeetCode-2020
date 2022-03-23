package String;

public class q186_reverse_words_in_a_string_ii {
    public void reverseWords(char[] s) {
        int n = s.length;
        int left = 0, right = 0;
        // 内部单词翻转
        while (right < n) {
            if (s[right] == ' ') {
                swap(s, left, right-1);
                right++;
                left = right;
            } else right++;
        }
        // if (right == n)
        swap(s, left, n-1);
        // 整体翻转
        swap(s, 0, n-1);
    }

    private void swap(char[] s, int i, int j) {
        while (i < j) {
            char ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++; j--;
        }
    }
}
