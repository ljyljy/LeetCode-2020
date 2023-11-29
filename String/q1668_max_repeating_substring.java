package String;

public class q1668_max_repeating_substring {
    public int maxRepeating(String sequence, String word) {
        int cnt = 0;
        int n = sequence.length();
        StringBuffer curWord = new StringBuffer(word);
        if (curWord.length() == 0) {
            return 0;
        }
        int idx = sequence.indexOf(curWord.toString(), 0);
        while (idx != -1) {
            cnt++;
            curWord.append(word);
            idx = sequence.indexOf(curWord.toString(), idx + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {

    }
}
