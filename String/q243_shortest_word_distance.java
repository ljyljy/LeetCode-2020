package String;

public class q243_shortest_word_distance {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length;
        int res = n;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < n; i++) {
            String word = wordsDict[i];
            if (word.equals(word1)) {
                idx1 = i;
            } else if (word.equals(word2)) {
                idx2 = i;
            }
            if (idx1 >= 0 && idx2 >= 0) {
                res = Math.min(res, Math.abs(idx1 - idx2));
            }
        }
        return res;
    }
}
