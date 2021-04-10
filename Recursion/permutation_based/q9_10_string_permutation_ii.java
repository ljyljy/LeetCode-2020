package Recursion.permutation_based;

import java.util.*;

public class q9_10_string_permutation_ii {
    // 写法1【推荐模板】: 字符串处理：Deque<String> path（vs 复原IP）
    private List<String> res = new ArrayList<>();
    private Deque<String> path = new ArrayDeque<>(1);
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // 去重预处理(根据相邻元素)
        boolean[] used = new boolean[chars.length];
        dfs(chars, 0, used);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(char[] chars, int idx, boolean[] used) {
        if (path.size() == chars.length) {
            res.add(String.join("", path));
            return;
        }
        // 注释详见：全排列II
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            if (i > 0 && chars[i-1] == chars[i] && !used[i-1])
                continue;
            used[i] = true;
            path.addLast("" + chars[i]); // String.valueOf(chars[i])
            dfs(chars, i+1, used);
            used[i] = false;
            path.removeLast();
        }
    }

    // 写法2【更快】：String path
    public String[] permutation2(String s) {
        char[] chars = s.toCharArray();
        boolean[] used = new boolean[chars.length];
        Arrays.sort(chars); // 去重预处理(根据相邻元素)
        dfs2(chars, 0, used, "");
        // 转换 Set<String> ==> String[]，数组长度res.size()
        return res.toArray(new String[res.size()]);
    }

    private void dfs2(char[] chars, int idx, boolean[] used, String path) {
        if (path.length() == chars.length) {
            res.add(path);
            return;
        }
        // ∵ 全排列 ∴ i从0起始！！而非startIdx！
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue; // 去重1 - 已访问过（同一树枝元素i）∵下标i从0起 ∴存在树枝上元素的重复
            if (i >= 1 && chars[i] == chars[i-1] && !used[i-1]) // 去重2 - 同一树层
                continue; // 同一树层の相同字母：'左兄弟'(前一个相同字母)递归结束并回溯为!used[i-1]
            used[i] = true;
            dfs2(chars, i+1, used, path + chars[i]);
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        q9_10_string_permutation_ii sol = new q9_10_string_permutation_ii();
        String[] strs = sol.permutation("abca");
        for (String str: strs){
            System.out.println(str);
        }
    }
}
