package DataStructure.Trie;


import java.util.PrimitiveIterator;

public class q208_implement_trie {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    private class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a'; // 优化：数组代替哈希
                if (cur.children[idx] == null)
                    cur.children[idx] = new TrieNode();
                cur = cur.children[idx]; // 指针下移
            }
            cur.isWord = true; // 叶子处
        }

        private TrieNode find(String word) { // 返回word末尾对应的结点
            TrieNode cur = root;
            char[] chars = word.toCharArray();
            for (char c: chars) {
                int idx = c - 'a'; // 优化：数组代替哈希
                if (cur.children[idx] == null)
                    return null;
                cur = cur.children[idx]; // 指针下移
            }
            return cur;
        }

        public boolean search(String word) {
            TrieNode cur = find(word);
            return cur != null && cur.isWord;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }
    }
}
