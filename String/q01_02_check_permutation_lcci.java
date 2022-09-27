package String;

public class q01_02_check_permutation_lcci {
    public boolean CheckPermutation(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        int[] cnts = new int[26];
        for (int i = 0; i < n; i++) {
            cnts[s1.charAt(i) - 'a']++;
            cnts[s2.charAt(i) - 'a']--;
        }

        return allZero(cnts);
    }

    private boolean allZero(int[] cnts) {
        for (int cnt: cnts) {
            if (cnt != 0) return false;
        }
        return true;
    }
}
