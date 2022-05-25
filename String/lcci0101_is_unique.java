package String;

public class lcci0101_is_unique {
    public boolean isUnique(String astr) {
        int[] cnt = new int[256];
        for (char c: astr.toCharArray()) {
            cnt[c]++;
            if (cnt[c] > 1) return false;
        }
        return true;
    }
}
