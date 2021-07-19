package String;

public class q14_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int n = strs[0].length(), cnt = strs.length;
        String pattern = strs[0];
        // 遍历strs[0]每个字母
        for (int i = 0; i < n; i++) {
            char ch = pattern.charAt(i);
            for (int j = 1; j < cnt; j++) {
                // char ch2 = strs[j].charAt(i);
                // if (i < strs[j].length() && strs[j].charAt(i) == ch) continue;
                if (i >= strs[j].length() || strs[j].charAt(i) != ch)
                    return pattern.substring(0, i);
            }
        }
        return pattern;
    }
}
