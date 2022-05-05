package String;

public class q1160_find_words_that_can_be_formed_by_characters {
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        int[] chs = countChars(chars);

        for (String word: words) {
            boolean flag = true;
            int[] curChs = countChars(word);
            for (char c: word.toCharArray()) {
                if (curChs[c-'a'] > chs[c-'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans += word.length();
            }
        }

        return ans;
    }

    private int[] countChars(String chars) {
        int[] chs = new int[26];
        for (char c: chars.toCharArray()) {
            chs[c - 'a']++;
        }
        return chs;
    }
}
