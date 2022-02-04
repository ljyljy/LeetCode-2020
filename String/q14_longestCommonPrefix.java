package String;

public class q14_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String pattern = strs[0];
        int n = pattern.length(), cnt = strs.length;

        for (int i = 0; i < n; i++) {// 遍历strs[0]每个字母
            char ch = pattern.charAt(i);
            for (int j = 1; j < cnt; j++) {// 遍历str[j], j=1:end
                if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
                    return pattern.substring(0, i);
                }
            }
        }
        return pattern;
    }
}
