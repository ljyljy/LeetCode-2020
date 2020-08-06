# 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
#
#  返回删除后的链表的头节点。
#
#  注意：此题对比原题有改动
#
#  示例 1:
#
#  输入: head = [4,5,1,9], val = 5
# 输出: [4,1,9]
# 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
#
#
#  示例 2:
#
#  输入: head = [4,5,1,9], val = 1
# 输出: [4,5,9]
# 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
#
#
#
#
#  说明：
#
#
#  题目保证链表中节点的值互不相同
#  若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
#
#  Related Topics 链表
#  👍 36 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    # 法2：单指针扫描法
    # 时间复杂度：O(N)。N 为链表的长度，最坏情况下，要删除的结点位于链表末尾，这时我们需要遍历整个链表。
    # 空间复杂度：O(1)。仅使用了额外的 dummy。
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        if not head: return None
        if head.val == val: return head.next
        dummy = ListNode(-1)
        dummy.next = head
        while head and head.next:
            if head.next and head.next.val == val:  # 删除next
                head.next = head.next.next
                break
            head = head.next
        return dummy.next

    # 法3：信息交换法（cur代替next，然后next为cur'替死'）：
    # 删除：平均O(1)，最坏-删尾巴：O(n)
    # 前提：head与node2del指向的是同一个链表中的元素！
    def deleteNode3(self, head: ListNode, node2del: ListNode) -> ListNode:
        if not head or not node2del: return None
        if head.val == node2del.val: return head.next

        dummy = ListNode(-1)
        dummy.next = head
        # 待删结点不是尾巴
        if node2del.next:
            node2del.val = node2del.next.val
            node2del.next = node2del.next.next
        else:  # 待删的是尾巴
            while head and head.bext:
                if head.next and head.next.val != node2del.val:
                    head = head.next
            head.next = None
        return dummy.next

    # 法1：递归
    def deleteNode1(self, head: ListNode, val: int) -> ListNode:
        if not head: return None
        if head.val == val: return head.next
        head.next = self.deleteNode(head.next, val)
        return head
# leetcode submit region end(Prohibit modification and deletion)
