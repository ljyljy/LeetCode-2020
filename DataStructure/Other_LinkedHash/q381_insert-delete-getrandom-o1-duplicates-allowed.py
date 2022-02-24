# 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
#
#  注意: 允许出现重复元素。
#
#
#  insert(val)：向集合中插入元素 val。
#  remove(val)：当 val 存在时，从集合中移除一个 val。
#  getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
#
#
#  示例:
#
#  // 初始化一个空的集合。
# RandomizedCollection collection = new RandomizedCollection();
#
# // 向集合中插入 1 。返回 true 表示集合不包含 1 。
# collection.insert(1);
#
# // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
# collection.insert(1);
#
# // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
# collection.insert(2);
#
# // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
# collection.getRandom();
#
# // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
# collection.remove(1);
#
# // getRandom 应有相同概率返回 1 和 2 。
# collection.getRandom();
#
#  Related Topics 设计 数组 哈希表
#  👍 69 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import random
from collections import defaultdict


class RandomizedCollection:
    def __init__(self):
        self.dict_idxes = defaultdict(set)  # {val: set(idxes)}❤❤❤
        self.list_val = []

    def insert(self, val: int) -> bool:
        self.list_val.append(val)
        self.dict_idxes[val].add(len(self.list_val) - 1)
        return len(self.dict_idxes[val]) == 1  # ❤ set(idxes) == 1 ?

    def remove(self, val: int) -> bool:
        # ❤❤ 如果写if val not in self.dict会TLE！！！
        if not self.dict_idxes[val]:
            return False
        x = self.dict_idxes[val].pop()  # 从dict中pop出set(idxes)[0] -- set集的首元素
        self.list_val[x] = None
        return True

    def getRandom(self) -> int:
        x = None
        while x is None:
            x = random.choice(self.list_val)
        return x

# # TEST: list_val = ['a','a','a','b','b','c']
# dictt = defaultdict(set)
# dictt['a'] = set([0,1,2])
# dictt['b'] = set([3,4])
# dictt['c'] = set([5])
# x = dictt['a'].pop() --> x = 0, dictt['a'] = {1, 2}

# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
# leetcode submit region end(Prohibit modification and deletion)
import random
from collections import defaultdict


class RandomizedCollection_ii:
    def __init__(self):
        self.dict_idxes = defaultdict(set)  # {val: set(idxes)}❤❤❤
        self.list_val = []

    def insert(self, val: int) -> bool:
        self.list_val.append(val)
        self.dict_idxes[val].add(len(self.list_val) - 1)
        return len(self.dict_idxes[val]) == 1  # ❤ set(idxes) == 1 ?

    def remove(self, val: int) -> bool:
        # 如果写if val not in self.dict会TLE！！！
        if not self.dict_idxes[val]:  return False
        x = self.dict_idxes[val].pop()  # 从dict中pop出set(idxes)[0] -- set集的首元素
        self.list_val[x] = None
        return True

    def getRandom(self) -> int:
        x = None
        while x is None:
            x = random.choice(self.list_val)
        return x

# # TEST: list_val = ['a','a','a','b','b','c']
# dictt = defaultdict(set)
# dictt['a'] = set([0,1,2])
# dictt['b'] = set([3,4])
# dictt['c'] = set([5])
# x = dictt['a'].pop() --> x = 0, dictt['a'] = {1, 2}
