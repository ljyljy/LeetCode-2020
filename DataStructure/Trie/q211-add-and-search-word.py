class TrieNode:
    def __init__(self):
        self.children = {}  # [None for _ in range(26)]
        self.is_word = False


class WordDictionary:

    def __init__(self):
        self.root = TrieNode()

    def addWord(self, word: str) -> None:
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]  # 指针下移
        node.is_word = True

    def search(self, word: str) -> bool:
        if not word: return False
        return self.search_helper(word, 0, self.root)

    def search_helper(self, word, idx, node):
        if not node: return False
        if idx >= len(word): return node.is_word
        ch = word[idx]
        if ch != '.':  # 非通配符，则下探当前节点node的某个孩子（名为'ch'） ↓
            return self.search_helper(word, idx + 1, node.children.get(ch))
        else:  # 通配符，需遍历node的所有children
            for child in node.children:
                if self.search_helper(word, idx + 1, node.children[child]):
                    return True  # ↑不可直接return上面 否则'a.b'此类将判错

        return False
