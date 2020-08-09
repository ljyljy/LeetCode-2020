# 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
#
#  示例1:
#
#
#  输入：[1, 2, 3, 3, 2, 1]
#  输出：[1, 2, 3]
#
#
#  示例2:
#
#
#  输入：[1, 1, 1, 1, 2]
#  输出：[1, 2]
#
#
#  提示：
#
#
#  链表长度在[0, 20000]范围内。
#  链表元素在[0, 20000]范围内。
#
#
#  进阶：
#
#  如果不得使用临时缓冲区，该怎么解决？
#  Related Topics 链表
#  👍 58 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# https://leetcode-cn.com/problems/remove-duplicate-node-lcci/solution/guan-yu-yi-chu-zhong-fu-jie-dian-de-yi-dian-si-kao/

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        # global pre_p
        dummy, unique = ListNode(-1), set()
        dummy.next = head
        p = head
        # 由于【未排序】，还需要一个set(), 其底层实现是字典（哈希表），因而集合的查找的时间复杂度为o(1)
        while p:
            if p.val in unique:  # 有重复
                pre_p.next = pre_p.next.next  # 技巧1-2
                p = pre_p.next  # 若为None，则退出while
            else:
                unique.add(p.val)
                pre_p = p  # 技巧1-1 （初次循环必定进入else）
                p = p.next
        return dummy.next
# leetcode submit region end(Prohibit modification and deletion)
