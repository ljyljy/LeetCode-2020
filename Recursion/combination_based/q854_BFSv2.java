package Recursion.combination_based;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class q854_BFSv2 {
    class Node {
        String str;
        int idx;

        public Node(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }

    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(s1, 0));
        Set<String> visited = new HashSet<>();
        visited.add(s1);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                String curStr = node.str;
                int curIdx = node.idx;
                if (s2.equals(curStr)) return step;
                while (curIdx < n && curStr.charAt(curIdx) == s2.charAt(curIdx)) {
                    curIdx++;
                }
                char pattern = s2.charAt(curIdx);
                for (int j = curIdx+1; j < n; j++) {
                    if (curStr.charAt(j) == s2.charAt(j)) continue;
                    if (curStr.charAt(j) == pattern) {
                        String nxtStr = getNxtStr(curStr, curIdx, j);
                        if (visited.contains(nxtStr)) continue;
                        visited.add(nxtStr);
                        queue.add(new Node(nxtStr, curIdx+1));
                        // if (s2.equals(nxtStr)) return curIdx+1; // WA! 不可提前return，不一定min！
                    }
                }
            }
            step++;
        }
        return step;
    }

    private String getNxtStr(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = chars[i];
        return new String(chars);
    }
}
