class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


# 法2：双向链表 LinkedList + HashMap + HastSet
# 由双链表维护字符顺序，HashMap维护字符是否在之前出现过以及字符相应的链表节点。HashSet表示已经出现过两次并且被从链表中删除的字符
class LinkedNode:
    def __init__(self, val='^', prev=None, next=None):
        self.val = val
        self.prev = prev
        self.next = next


class Solution:
    # 法2：数据流
    def firstUniqChar(self, s):
        dup_set, chars_map = set(), {}  # char: LinkedNode
        head = LinkedNode()
        tail = head
        for c in s:
            if c in dup_set: continue
            if c in chars_map:  # 删除重复结点并放入dup
                node = chars_map[c]
                prev, next = node.prev, node.next
                prev.next = next  # 跳过中间的node
                if next:  next.prev = prev  # 删除中间的node
                if node == tail: tail = prev  # 若node是尾节点，则tail前移
                del chars_map[c]
                dup_set.add(c)
            else:
                newNode = LinkedNode(c, prev=tail)
                chars_map[c] = newNode
                tail.next = newNode
                tail = newNode
        return head.next.val
