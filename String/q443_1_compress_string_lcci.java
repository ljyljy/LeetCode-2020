package String;

public class q443_1_compress_string_lcci {
    public String compressString(String S) {
        int n = S.length();
        String res = "";
        for (int i = 0; i < n; ) {
            char cur = S.charAt(i);
            int cnt = 1;
            res += "" + cur;
            while (i+1 < n && S.charAt(i) == S.charAt(i+1)) {
                cnt++;
                i++;
            }
            res += "" + cnt;
            i++;
        }
        return res.length() >= S.length()? S: res;
    }
}
