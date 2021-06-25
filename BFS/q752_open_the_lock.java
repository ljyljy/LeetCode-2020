package BFS;

import java.util.*;

public class q752_open_the_lock {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        Set<String> dead = new HashSet<>();
//        for (String str : deadends) dead.add(str); // 法1
        Collections.addAll(dead, deadends); // 法2
        if (dead.contains("0000")) return -1;

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer("0000");
        seen.add("0000");

        while (!queue.isEmpty()) {
            step++; // ❤ 注意该句位置！！
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curState = queue.poll();
                for (String nextState : getNextState(curState)) {
                    if (!seen.contains(nextState) && !queue.contains(nextState)){
                        if (target.equals(nextState))
                            return step;
                        queue.offer(nextState);
                        seen.add(nextState);
                    }
                }
            }
        }
        return -1;
    }

    private char getPrevChar(char x) {
        return x == '0'? '9': (char)(x-1);
    }

    private char getNextChar(char x) {
        return x == '9'? '0': (char)(x+1);
    }

    private List<String> getNextState(String curState) {
        List<String> nextStates = new ArrayList<>();
        char[] chars = curState.toCharArray();
        for (int i = 0; i < curState.length(); i++) {
            char num0 = chars[i];
            // 当前第i位：前/后拨动一次，加入nextStates
            chars[i] = getPrevChar(num0);
            nextStates.add(new String(chars)); // 勿忘char[]转为String！
            chars[i] = getNextChar(num0);
            nextStates.add(new String(chars));
            chars[i] = num0; // 恢复初始状态
        }
        return nextStates;
    }
}
