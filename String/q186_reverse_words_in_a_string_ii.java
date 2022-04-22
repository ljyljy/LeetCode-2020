package String;

public class q186_reverse_words_in_a_string_ii {
    public void reverseWords(char[] s) {
        int n = s.length;
        int left = 0, right = 0;
        // �ڲ����ʷ�ת
        while (right < n) {
            if (s[right] == ' ') {
                swap(s, left, right-1);
                right++;
                left = right;
            } else right++;
        }
        // if (right == n)
        swap(s, left, n-1);
        // ���巭ת
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


    // public String reverseWords_WA(String s) { // δ���ǵ��ʼ�Ķ���ո�
    //     if (s.isEmpty() || s.trim().isEmpty()) return "";
    //     String str = s.trim();
    //     char[] ss = str.toCharArray();
    //     int n = ss.length;
    //     swap(ss, 0, n-1); // "eulb si yks eht"

    //     int i = 0;
    //     while (i < n) {
    //         int j = i;

    //         while(i < n && j < n && ss[j] != ' ') j++;
    //         swap(ss, i, j-1);
    //         i = j+1;
    //     }
    //     return new String(ss);
    // }


    // private void swap(char[] s, int i, int j) {
    //     while (i < j) {
    //         char ch = s[i];
    //         s[i] = s[j];
    //         s[j] = ch;
    //         i++; j--;
    //     }
    // }
}
