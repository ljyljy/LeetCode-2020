package Recursion.permutation_based;

import java.util.*;

public class q126_word_ladder_ii {
    // ע�ͼ�Py��
    Set<String> words = new HashSet<>();
    Map<String, Integer> dists = new HashMap<>(); //  // BFS�ã�<word����endW�ĵ������>
    // DFS��
    List<List<String>> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();
    public List<List<String>> findLadders(String beginW, String endW, List<String> wordList) {
        words.addAll(wordList);
        words.add(beginW);
        path.addLast(beginW); // ?���������Ҫ�������path��
        getDists_BFS(endW);
        getAllRes_DFS(beginW, endW);
        return res;
    }

    private void getDists_BFS(String endW) {
        dists.put(endW, 0);

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(endW);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                // if (cur.equals(endW)) continue;
                int curDist = dists.get(cur);
                for (String nxt: getNextW(cur)) {
                    if (!dists.containsKey(nxt)) {
                        dists.put(nxt, curDist+1);
                        queue.offer(nxt);
                    }
                }
            }

        }
    }

    private void getAllRes_DFS(String curW, String endW) {
        if (curW.equals(endW)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String nxt: getNextW(curW)) {
            // ? ÿ��һ������ȷ���� end �� distance Խ��Խ��!
            if (dists.get(nxt) == dists.get(curW)-1) { // ����dist[endW]=0
                path.addLast(nxt);
                getAllRes_DFS(nxt, endW);
                path.removeLast();
            }

        }
    }

    private Set<String> getNextW(String str) {
        Set<String> nxtWords = new HashSet<>();
        int n = str.length();
        char[] ss = str.toCharArray();
        for (int i = 0; i < n; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                char src = ss[i];
                ss[i] = c;
                String newS = new String(ss);
                if (newS.equals(str)) continue;
                if (words.contains(newS)) {
                    nxtWords.add(newS);
                }
                ss[i] = src;
            }
        }
        return nxtWords;
    }
}
