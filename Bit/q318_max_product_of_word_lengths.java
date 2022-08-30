package Bit;

public class q318_max_product_of_word_lengths {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (char ch: word.toCharArray()) {
                masks[i] |= 1 << (ch - 'a'); // Ó³Éäµ½µ×0-25Î»
            }
        }

        int maxProd = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }
}
