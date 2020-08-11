# 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
#
#
#
#  示例：
#
#  s = "leetcode"
# 返回 0
#
# s = "loveleetcode"
# 返回 2
#
#
#
#
#  提示：你可以假定该字符串只包含小写字母。
#  Related Topics 哈希表 字符串
#  👍 247 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class LinkedNode:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next


# 法1：数据流 LinkedList + HashMap
# class DataStream:
#     def __init__(self, charToPrev={}, dupChars=set()):
#         self.charToPrev = charToPrev
#         self.dupChars = dupChars
#         self.dummy = LinkedNode('^')
#         self.tail = self.dummy
#
#     def add(self, c):
#         if c in self.dupChars: return
#         if c not in self.charToPrev:
#             node = LinkedNode(c)
#             self.charToPrev[c] = self.tail  # append至末尾
#             self.tail.next = node
#             self.tail = node
#             return
#         # delete the existing node （这样后续查找charToPrev，就可以减小遍历量）
#         prev = self.charToPrev[c]
#         prev.next = prev.next.next
#         if not prev.next:
#             self.tail = prev  # 删除的刚好是尾巴，则tail前移
#         else:
#             self.charToPrev[prev.next.val] = prev
#         self.charToPrev.pop(c)
#         self.dupChars.add(c)
#
#     def firstUniqueChar(self):
#         return self.dummy.next.val if self.dummy.next else None


class DataStream:
    def __init__(self):
        self.head = LinkedNode(0)
        self.tail = self.head
        self.numToPrev = {}  # {num: LinkedNode}
        self.dup_set = set()

    def _remove(self, num):
        prev = self.numToPrev[num]
        prev.next = prev.next.next
        self.numToPrev.pop(num)
        # update tail and prev of next
        if prev.next:
            self.numToPrev[prev.next.val] = prev
        else:
            self.tail = prev

    def _add(self, num):
        if num in self.dup_set: return
        if num in self.numToPrev:
            self._remove(num)
            self.dup_set.add(num)
        else:
            new_node = LinkedNode(num)
            self.numToPrev[num] = self.tail
            self.tail.next = new_node
            self.tail = new_node

    def firstUniqueChar(self):
        if self.head.next:
            return self.head.next.val
        return None


class Solution:
    # 法1：使用 Hash & Linked List 处理 Data Stream 的问题
    def firstUniqChar(self, s):
        if not s: return -1
        ds = DataStream()
        for i in range(len(s)):
            ds._add(s[i])
        firstUniqChar = ds.firstUniqueChar()
        if not firstUniqChar:
            return -1
        return s.find(firstUniqChar)

    # 法2
    # https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/python-4chong-fang-fa-yi-ge-12msde-gai-jin-jie-fa-/
    def firstUniqChar2(self, s):
        from collections import OrderedDict  # LinkedHashMap
        odict = OrderedDict()
        for c in s:  # 统计字频
            odict[c] = odict[c] + 1 if c in odict else 1
        # 利用有序的特性，在字典中找出首个出现次数为一的字符串
        for k, v in odict.items():
            if v == 1:
                return s.index(k)
        return -1

# leetcode submit region end(Prohibit modification and deletion)
