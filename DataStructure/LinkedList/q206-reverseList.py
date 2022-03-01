# 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
#
#
#
#  示例:
#
#  输入: 1->2->3->4->5->NULL
# 输出: 5->4->3->2->1->NULL
#
#
#
#  限制：
#
#  0 <= 节点个数 <= 5000
#
#
#
#  注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/
#  Related Topics 链表
#  👍 80 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# 迭代、递归都要会

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # 法1：迭代法
    def reverseList1(self, head: ListNode) -> ListNode:
        if not head: return None
        prev, cur = None, head  # 1. 初始化
        while cur:
            nxt = cur.next  # 2. 从后向前
            cur.next = prev  # 3. 反转（顺序勿错）
            prev = cur  # 4. 指针后移
            cur = nxt
        return prev

    # 法2：递归法
    # https://leetcode-cn.com/problems/reverse-linked-list/solution/shi-pin-jiang-jie-die-dai-he-di-gui-hen-hswxy/
    def reverseList(self, head: ListNode) -> ListNode:
        if not head or not head.next: return head  # head[本身]为空 or [递归时]下一个空
        # 自上而下递归到最后一个结点，设为last
        last = self.reverseList(head.next)
        head.next.next = head  # 反转; head = last上一层(递归)的父亲
        head.next = None
        return last

# leetcode submit region end(Prohibit modification and deletion)
