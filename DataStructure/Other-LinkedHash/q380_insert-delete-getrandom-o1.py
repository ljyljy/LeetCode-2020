# 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
#
#
#  insert(val)：当元素 val 不存在时，向集合中插入该项。
#  remove(val)：元素 val 存在时，从集合中移除该项。
#  getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
#
#
#  示例 :
#
#
# // 初始化一个空的集合。
# RandomizedSet randomSet = new RandomizedSet();
#
# // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
# randomSet.insert(1);
#
# // 返回 false ，表示集合中不存在 2 。
# randomSet.remove(2);
#
# // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
# randomSet.insert(2);
#
# // getRandom 应随机返回 1 或 2 。
# randomSet.getRandom();
#
# // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
# randomSet.remove(1);
#
# // 2 已在集合中，所以返回 false 。
# randomSet.insert(2);
#
# // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
# randomSet.getRandom();
#
#  Related Topics 设计 数组 哈希表
#  👍 165 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import random


class RandomizedSet:

    def __init__(self):
        self.dict = {}
        self.list = []

    def insert(self, val: int) -> bool:
        if val in self.dict: return False
        self.list.append(val)
        self.dict[val] = len(self.list) - 1
        return True

    def remove(self, val2del: int) -> bool:
        # 交换list[-1]与list[dict[val2del]], 然后将最后的元素删除
        if val2del in self.dict:
            last_ele, idx_del = self.list[-1], self.dict[val2del]
            # 修改last_ele的映射（填补val2del）
            self.list[idx_del], self.dict[last_ele] = last_ele, idx_del
            # poplast
            self.list.pop()
            del self.dict[val2del]
            return True
        return False

    def getRandom(self) -> int:
        return random.choice(self.list)

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
# leetcode submit region end(Prohibit modification and deletion)
