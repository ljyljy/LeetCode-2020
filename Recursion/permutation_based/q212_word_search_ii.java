package Recursion.permutation_based;

import java.util.*;

public class q212_word_search_ii {
    // ���q208��212��820
    private char[][] board;
    private int m, n;
    private Set<String> res = new HashSet<>();
    // private StringBuilder path = new StringBuilder();
    private int[] _row = {0, 1, 0, -1};
    private int[] _col = {1, 0, -1, 0};
    private boolean[][] visited;
    private Trie trie = new Trie();
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length; n = board[0].length;
        visited = new boolean[m][n];

        // Ԥ����Trie�ֵ��� - ����ǰ׺
        TrieNode root = trie.root;
        for (String word: words) {
            trie.insert(word);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = board[i][j];
                TrieNode nxt = trie.root.children[ch - 'a'];
                visited[i][j] = true; // ?�����ظ���[["a","a"]], words=["aaa"], ��֤���[], ����"aaa"
                dfs(i, j, visited, nxt);
                visited[i][j] = false;
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(int i, int j, boolean[][] visited, TrieNode nxt) {
        if (nxt == null) return;

        if (nxt.isWord) {
            res.add(nxt.word);
            // return; // ??? ����return������"oa"�����κ����"oaa","oath"
        }

        for (int dir = 0; dir < 4; dir++) {
            int newI = i + _row[dir], newJ = j + _col[dir];
            if (!isValid(newI, newJ)) continue;
            visited[newI][newJ] = true;
            TrieNode newNxt = nxt.children[board[newI][newJ] - 'a'];
            dfs(newI, newJ, visited, newNxt);
            visited[newI][newJ] = false;
        }
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n
                && !visited[x][y];
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word; // ? ��ǵ���

        public TrieNode(){
            children = new TrieNode[26];
        }
    }

    class Trie {
        public TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char ch: word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
            cur.word = word;// ? ��ǵ���
        }

        // public TrieNode findLeaf(String word) {
        //     TrieNode cur = root;
        //     for (char ch: word.toCharArray()) {
        //         int idx = ch - 'a';
        //         if (cur.children[idx] == null) {
        //             return null;
        //         }
        //         cur = cur.children[idx];
        //     }
        //     return cur;
        // }

        // public boolean search(String word) {
        //     TrieNode leaf = findLeaf(word);
        //     return leaf != null && leaf.isWord;
        // }
    }
}
