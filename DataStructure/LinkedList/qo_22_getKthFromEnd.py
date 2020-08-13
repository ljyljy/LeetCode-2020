# 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，
# 它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
#
#
#
#  示例：
#
#  给定一个链表: 1->2->3->4->5, 和 k = 2.
#
# 返回链表 4->5.
#  Related Topics 链表 双指针
#  👍 70 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    # 思路：先求出链表长度n，倒数第k个 <=> 正数第n+1-k个
    def getKthFromEnd(self, head: ListNode, k: int) -> ListNode:
        n, p = 0, head
        if not head: return None
        while head: n += 1; head = head.next;  # 遍历链表求总长
        if k > n: return None
        r = range(n - k)
        for i in r:  # 向后走n-k步，得到正数第n-k+1个结点
            p = p.next
        return p

# leetcode submit region end(Prohibit modification and deletion)