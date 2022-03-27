package DataStructure.Trie;

import java.util.Arrays;

public class q820_short_encoding_of_words {
    // 类比q208、212、820
    public int minimumLengthEncoding(String[] words) {
        int n = words.length;
        Trie trie = new Trie();
        Arrays.sort(words, (s1, s2)->(s2.length() - s1.length())); // 按单词长度降序?
        int len = 0;
        for (String w: words) {
            len += trie.insert(w);
        }
        return len;
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 前提：长单词优先插入 & 倒序插入?(从后缀开始！)
        public int insert(String word) {
            TrieNode cur = root;
            boolean isNewWord = false;
            char[] ch = word.toCharArray();
            for (int i = ch.length-1; i >= 0; i--) {
                int idx = ch[i] - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                    isNewWord = true;
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
            return isNewWord? word.length() + 1: 0; // +1: 需后缀"#"
        }

        // public boolean findLeaf(String word) {...}
    }
    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode () {
            children = new TrieNode[26];
        }
    }
}
