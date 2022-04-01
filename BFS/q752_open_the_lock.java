package BFS;

import java.util.*;

public class q752_open_the_lock {
    // 最新版本
    private static final String START = "0000";

    public int openLock(String[] deadends, String target) {
        if (target.equals(START)) return 0;
        Set<String> dead = new HashSet<>();
//        for (String str : deadends) dead.add(str); // 法1
        Collections.addAll(dead, deadends); // 法2
        if (dead.contains(START)) return -1;

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(START);
        visited.add(START);

        while (!queue.isEmpty()) {
            step++; // ❤ 注意该句位置！！
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curState = queue.poll();
                for (String nextState : getNextStates(curState)) {
                    if (!visited.contains(nextState) && !dead.contains(nextState)) {
                        if (target.equals(nextState))
                            return step;
                        queue.offer(nextState);
                        visited.add(nextState);
                    }
                }
            }
        }
        return -1;
    }

    // 类比q127、752
    private Set<String> getNextStates(String curState) {
        Set<String> res = new HashSet<>();
        char[] cur = curState.toCharArray();
        for (int i = 0; i < cur.length; i++) {
            char src = cur[i];
            // 当前第i位：前/后拨动一次，加入nextStates
            cur[i] = getPrevChar(src); // 传参【src】
            res.add(new String(cur));
            cur[i] = getNextChar(src);  // 不可传【cur[i]-被prev覆盖】，否则此处会恢复为src
            res.add(new String(cur));
            cur[i] = src; // 恢复初始状态
        }
        return res;
    }

    private char getPrevChar(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    private char getNextChar(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }


    // old
    public int openLock01(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        Set<String> dead = new HashSet<>();
//        for (String str : deadends) dead.add(str); // 法1
        Collections.addAll(dead, deadends); // 法2
        if (dead.contains("0000")) return -1;

//        int step = 0; // 法01—s1
        Map<String, Integer> map = new HashMap<>(); // 法02—s1 <"xxxx", step>
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer("0000");
        seen.add("0000");
        map.put("0000", 0);// 法02—s2

        while (!queue.isEmpty()) {
//            step++; // 法01-s2 ❤ 注意该句位置！！
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curState = queue.poll();
                int step = map.get(curState); // 法02—s3
                for (String nextState : getNextStates(curState)) {
                    if (!seen.contains(nextState) && !queue.contains(nextState)) {
//                        if (target.equals(nextState)) return step; // 法01-s3
                        if (nextState.equals(target)) return step + 1;// 法02—s4
                        map.put(nextState, step + 1);// 法02—s5
                        queue.offer(nextState);
                        seen.add(nextState);
                    }
                }
            }
        }
        return -1;
    }


}
