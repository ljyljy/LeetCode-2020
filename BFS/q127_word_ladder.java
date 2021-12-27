package BFS;

import java.util.*;

public class q127_word_ladder {
    // BFS法2-快（dist初始化为1，每层遍历ing就时刻检查有无equals(end),无需等本层遍历结束）
    public int ladderLength_fast(String start, String end, List<String> wordList) {
        // 必须将List转为Set，否则TLE!!!
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);

        int dist = 1; // 起始长度为1
        while (!queue.isEmpty()) {
            dist++; // 到下一层（非本层）的长度
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                for (String nxtW: getNxtWords(curWord, wordSet)) {
                    if (visited.contains(nxtW)) continue;

                    // 法2：更快-每层遍历ing就时刻检查有无equals(end)
                    if (nxtW.equals(end)) return dist; // 直接返回到下一层的长度

                    queue.offer(nxtW);
                    visited.add(nxtW);
                }
            }
        }
        return 0;
    }

    // BFS法1-慢（dist初始化为0，每层遍历结束后，再看整层是否有equals(end)）
    public int ladderLength_slow(String start, String end, List<String> wordList) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        // 必须将List转为Set，否则TLE!!!
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);

        int dist = 0;
        while (!queue.isEmpty()) {
            dist++; // 每层++
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                // System.out.println(curWord + ", " + dist);
                if (curWord.equals(end)) return dist; // ❤字符串比较不可应用'=='
                for (String nxtWord: getNxtWords(curWord, wordSet)) {
                    // System.out.println(nxtWord);
                    if (visited.contains(nxtWord))
                        continue; // 不走回头路
                    visited.add(nxtWord);
                    queue.offer(nxtWord);
                }
            }
        }
        return 0;
    }

    private Set<String> getNxtWords(String curW, Set<String> wordSet) {
        Set<String> nxtWords = new HashSet<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            // 替换某个idx处的字母ch
            for (int i = 0; i < curW.length(); i++) {
                char curC = curW.charAt(i);
                if (curC == ch) continue;
                String replacedW = replace(curW, i, ch);
                if (wordSet.contains(replacedW))
                    nxtWords.add(replacedW);
            }
        }
        return nxtWords;
    }

    private String replace(String curW, int idx, char ch) {
        char[] chs = curW.toCharArray();
        chs[idx] = ch;
        return new String(chs);
    }
}