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
    # 双指针（迭代）
    # 复杂度分析
    # 时间：O(n+m) ，其中 nn 和 mm 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。
    # 空间：O(1) 。我们只需要常数的空间存放若干变量。
    def mergeTwoLists1(self, l1: ListNode, l2: ListNode) -> ListNode:
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

    # # 法2：递归
    # 我们可以如下递归地定义两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
    # 也就是说，两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并。
    # 我们直接将以上递归过程建模，同时需要考虑边界情况。如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。
    # 复杂度分析
    # -	时间复杂度：O(n+m)，其中 nn 和 mm 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
    # -	空间复杂度：O(n+m)，其中 nn 和 mm 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m次，因此空间复杂度为 O(n+m)。
    def mergeTwoLists(self, l1, l2):
        if l1 is None:
            return l2
        elif l2 is None:
            return l1
        elif l1.val < l2.val:
            l1.next = self.mergeTwoLists(l1.next, l2)
            return l1
        else:
            l2.next = self.mergeTwoLists(l1, l2.next)
            return l2

# leetcode submit region end(Prohibit modification and deletion)
