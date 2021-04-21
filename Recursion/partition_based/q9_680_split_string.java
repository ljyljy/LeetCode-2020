package Recursion.partition_based;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q9_680_split_string {
    List<List<String>> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();
    public List<List<String>> splitString(String s) {
        // if (s == null || s.length() == 0) {
        //     res.add(new ArrayList<>());
        //     return res;
        // }
        char[] chars = s.toCharArray();
        dfs(chars, 0);
        return res;
    }

    private void dfs(char[] chars, int idx) {
        if (idx == chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 2种情况/选择：分割1个-s[idx]处/2个字符-s[idx, idx+1]
        for (int i = idx; i < idx + 2 && i < chars.length; i++) {
            path.addLast(new String(chars, idx, i-idx+1));
            dfs(chars, i+1);
            path.removeLast();
        }
    }

    private void dfs2(char[] chars, int idx) {
        if (idx == chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // option 1: 分割1个字符-s[idx]处
        path.addLast(new String(chars, idx, 1));
        dfs2(chars, idx+1);
        path.removeLast();
        // option 2: 分割2个字符-s[idx, idx+1]处
        if (idx + 2 <= chars.length){
            path.addLast(new String(chars, idx, 2));
            dfs2(chars, idx+2);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        q9_680_split_string sol = new q9_680_split_string();
        List<List<String>> res = sol.splitString(s);
        System.out.println(res);
    }
}
