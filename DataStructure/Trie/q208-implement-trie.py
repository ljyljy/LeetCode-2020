# 	Q208. 实现Trie
# 实现一个 TrieNode (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
# 示例:
# TrieNode trie = new TrieNode();
# trie.insert("apple");
# trie.search("apple");   // 返回 true
# trie.search("app");     // 返回 false
# trie.startsWith("app"); // 返回 true
# trie.insert("app");
# trie.search("app");     // 返回 true
# 相关题目
# 634. 单词矩阵623. K步编辑559. 查找树服务527. 序列化Trie473. 单词的添加与查找


class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_word = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]  # 指针下移
        node.is_word = True

    # ❤若找到，则返回叶子❤
    def find(self, word: str) -> TrieNode:
        cur = self.root
        for c in word:  # 若找到，则指针下移至孩子
            cur = cur.children.get(c)
            if not cur:  # 若不存在，则返回None，不报错
                return None
        return cur

    def search(self, word: str) -> bool:
        leaf = self.find(word)
        return leaf != None and leaf.is_word

    def startsWith(self, prefix: str) -> bool:
        return self.find(prefix) != None
