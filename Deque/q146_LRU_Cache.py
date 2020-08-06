# 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
#
#  获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
# 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
# 写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
#
#
#
#  进阶:
#
#  你是否可以在 O(1) 时间复杂度内完成这两种操作？
#
#
#
#  示例:
#
#  LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
#
# cache.put(1, 1);
# cache.put(2, 2);
# cache.get(1);       // 返回  1
# cache.put(3, 3);    // 该操作会使得关键字 2 作废
# cache.get(2);       // 返回 -1 (未找到)
# cache.put(4, 4);    // 该操作会使得关键字 1 作废
# cache.get(1);       // 返回 -1 (未找到)
# cache.get(3);       // 返回  3
# cache.get(4);       // 返回  4
#
#  Related Topics 设计
#  👍 797 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# /* 缓存容量为 2 */
# LRUCache cache = new LRUCache(2);
# // 你可以把 cache 理解成一个队列
# // 假设左边是队头，右边是队尾
# // 最近使用的排在队头，久未使用的排在队尾
# // 圆括号表示键值对 (key, val)
#
# cache.put(1, 1);
# // cache = [(1, 1)]
#
# cache.put(2, 2);
# // cache = [(2, 2), (1, 1)]
#
# cache.get(1);       // 返回 1
# // cache = [(1, 1), (2, 2)]
# // 解释：因为最近访问了键 1，所以提前至队头
# // 返回键 1 对应的值 1
#
# cache.put(3, 3);
# // cache = [(3, 3), (1, 1)]
# // 解释：缓存容量已满，需要删除内容空出位置
# // 优先删除久未使用的数据，也就是队尾的数据
# // 然后把新的数据插入队头
#
# cache.get(2);       // 返回 -1 (未找到)
# // cache = [(3, 3), (1, 1)]
# // 解释：cache 中不存在键为 2 的数据
#
# cache.put(1, 4);
# // cache = [(1, 4), (3, 3)]
# // 解释：键 1 已存在，把原始值 1 覆盖为 4
# // 不要忘了也要将键值对提前到队头
# https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/lru-suan-fa

# 法1：双向链表 + 哈希
class Node:  # 双向链表
    def __init__(self, key=None, val=None):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hash = {}
        self.head = Node(-1, -1)  # dummy
        self.tail = Node(-1, -1)  # dummy
        self.head.next = self.tail  # 勿忘！dummy的初始串接
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key not in self.hash: return -1
        node = self.hash[key]
        # // 先从链表中删除这个节点
        self._remove_node(node)
        # // 重新插到队尾
        self._append_to_tail(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        # 若映射hash / 结点hash[key]存在
        if self.get(key) != -1:  # // 删除旧的数据, 后续新插入的数据为最近使用的数据
            self.hash[key].val = value
            return  # get(key)操作已经move_to_tail了，直接返回即可
        # 若添加的是新元素，首先判断内存够不够
        if len(self.hash) >= self.capacity:
            self._pop_front()  # 删除最近未使用LeastRecentlyUsed
        node = Node(key, value)
        self._append_to_tail(node)
        self.hash[key] = node

    def _remove_node(self, node: Node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _append_to_tail(self, node: Node):
        node.prev = self.tail.prev
        node.next = self.tail
        node.prev.next = node
        node.next.prev = node

    def _pop_front(self):
        # 更新dummy_head的指向（即删除结点real_head）, 同时勿忘删除映射hash
        real_head = self.head.next
        self.head.next = real_head.next
        real_head.next.prev = self.head
        del self.hash[real_head.key]


# 法2：Py - OrderedDict  & Java - LinkedHashMap
from collections import OrderedDict


class LRUCache2:
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = OrderedDict()

    def get(self, key):
        if key not in self.cache:
            return -1
        val = self.cache.pop(key)  # 弹出
        self.cache[key] = val  # 重新放入队尾
        return val

    def put(self, key, val):
        if key in self.cache:  # 若key存在
            self.cache.pop(key)  # 先删掉
        elif len(self.cache) >= self.capacity:  # 若key不存在，先判断容量够不够
            # ❤ last = True时pop规则为FILO, last = False时pop规则为FIFO
            self.cache.popitem(last=False)  # 队列pop的是队首：FIFO
        self.cache[key] = val

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# leetcode submit region end(Prohibit modification and deletion)
