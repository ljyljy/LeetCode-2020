# 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
#
#  示例:
#
#  输入:
# [
#   1->4->5,
#   1->3->4,
#   2->6
# ]
# 输出: 1->1->2->3->4->4->5->6
#  Related Topics 堆 链表 分治算法
#  👍 842 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
from typing import List


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    # (1) 最小堆 - 每次 O(logK) 比较 K个指针求 min, 时间复杂度：O(NlogK)
    def mergeKLists1(self, lists: List[ListNode]) -> ListNode:
        import heapq
        # overwrite the compare function
        # so that we can directly put ListNode into heapq
        ListNode.__lt__ = lambda x, y: x.val < y.val

        if not lists: return None
        tail = dummy = ListNode(-1)
        heapK = []
        for head in lists:
            if head: heapq.heappush(heapK, head)

        while heapK:
            head = heapq.heappop(heapK)
            tail.next = head
            tail = head  # tail后移
            if head.next:
                heapq.heappush(heapK, head.next)

        return dummy.next

    # 法2：两两归并 - O(Nlogk)
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if not lists: return None
        while len(lists) > 1:
            next_lists = []
            for i in range(0, len(lists), 2):
                if i + 1 <= len(lists) - 1:
                    new_list = self.merge_two_lists(lists[i], lists[i + 1])
                else:
                    new_list = lists[i]
                next_lists.append(new_list)
            lists = next_lists

        return lists[0]

    def merge_two_lists(self, head1, head2):
        tail = dummy = ListNode(-1)
        while head1 and head2:
            if head1.val < head2.val:
                tail.next = head1
                head1 = head1.next
            else:
                tail.next = head2
                head2 = head2.next
            tail = tail.next

        if head1: tail.next = head1
        if head2: tail.next = head2
        return dummy.next

# leetcode submit region end(Prohibit modification and deletion)
