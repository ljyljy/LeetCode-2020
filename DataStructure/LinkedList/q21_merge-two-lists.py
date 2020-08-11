# 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
#
#  示例1：
#
#  输入：1->2->4, 1->3->4
# 输出：1->1->2->3->4->4
#
#  限制：
#
#  0 <= 链表长度 <= 1000
#
#  注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
#  Related Topics 分治算法
#  👍 39 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    # 双指针
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode(-1)
        cur = dummy
        while l1 and l2:
            if l1.val < l2.val:
                cur.next = l1
                cur = cur.next
                l1 = l1.next
            else:
                cur.next = l2
                cur = cur.next
                l2 = l2.next
        if l1: cur.next = l1
        if l2: cur.next = l2
        return dummy.next

# leetcode submit region end(Prohibit modification and deletion)
