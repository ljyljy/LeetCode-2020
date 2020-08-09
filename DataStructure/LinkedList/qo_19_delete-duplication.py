# 	Qo_19. 删除链表的重复节点
# 在一个排序的链表中，存在重复的结点，请删除该链表中所有重复的结点，重复的结点不保留。
# 样例1
# 输入：1->2->3->3->4->4->5
# 输出：1->2->5
# 样例2
# 输入：1->1->1->2->3
# 输出：2->3.
# https://www.acwing.com/problem/content/27/

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def deleteDuplication(self, head: ListNode):
        dummy = ListNode(-1)
        dummy.next = head
        p = dummy
        while p.next:
            q = p.next
            while q and p.next.val == q.val:  # 重复元素∈[p.next, q)
                q = q.next  # 若不含重复，q至少向后一步↓
            if p.next.next == q:  # 不含重复
                p = p.next
            else:  # 有重复段∈[p.next, q) -- 删
                p.next = q
        return dummy.next
