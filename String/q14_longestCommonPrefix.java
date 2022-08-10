package String;

public class q14_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String pattern = strs[0];
        int len = pattern.length(), cnt = strs.length;

        for (int i = 0; i < len; i++) {// 遍历strs[0]每个字母
            char ch = pattern.charAt(i);
            for (int j = 1; j < cnt; j++) {// 遍历str[j], j=1:end
                if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
                    return pattern.substring(0, i);
                }
            }
        }
        return pattern;
    }

    // 新版本
    public String longestCommonPrefix2(String[] strs) {
        int n = strs.length, len = strs[0].length();
        String p = strs[0];
        if (n == 1 || len == 0) return p;

        for (int i = 0; i < len; i++) {
            char c1 = p.charAt(i);
            for (int j = 1; j < n; j++) {
                String w2 = strs[j];
                int n2 = w2.length();
                if (i >= n2) return p.substring(0, i);
                char c2 = w2.charAt(i); // 前提：i < n2
                if (c1 != c2) return p.substring(0, i);
            }
        }
        return p; // 注意：而非返回"", 若不匹配，上面就已经返回了。这里肯定是全量匹配了！
    }
}
