# 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
# 向链表中的任意节点或者 null。
#
#
#
#  示例 1：
#
#
#
#  输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
# 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
#
#
#  示例 2：
#
#
#
#  输入：head = [[1,1],[2,1]]
# 输出：[[1,1],[2,1]]
#
#
#  示例 3：
#
#
#
#  输入：head = [[3,null],[3,0],[3,null]]
# 输出：[[3,null],[3,0],[3,null]]
#
#
#  示例 4：
#
#  输入：head = []
# 输出：[]
# 解释：给定的链表为空（空指针），因此返回 null。
#
#
#
#
#  提示：
#
#
#  -10000 <= Node.val <= 10000
#  Node.random 为空（null）或指向链表中的节点。
#  节点数目不超过 1000 。
#
#
#
#
#  注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-point
# er/
#
#
#  Related Topics 链表
#  👍 81 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random


class Solution:
    # 空间：O(1)
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head: return None
        p = head
        # (1)拷贝原p的值：复制当前节点p并插入到p与p.next中间
        while p:
            new_p = Node(p.val)
            new_p.next = p.next
            p.next = new_p
            p = new_p.next  # 即 p.next.next
        # (2)拷贝原p的random指针
        p = head  # 始终跳跃至原结点
        while p:
            p.next.random = None
            if p.random:  # p'->random = p.random'
                p.next.random = p.random.next
            p = p.next.next  # 移动至下一个原结点(中间隔个'副本')
        # (3)将“副本/替身”串联
        dummy = Node(-1)
        cur = dummy
        p = head  # p每次跳跃的目的地都是原结点
        while p:
            cur.next = p.next
            cur = cur.next
            p = p.next.next  # jump to next【原】结点
        return dummy.next
# leetcode submit region end(Prohibit modification and deletion)
