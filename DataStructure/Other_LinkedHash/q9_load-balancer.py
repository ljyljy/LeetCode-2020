# 	Lint[锁]. Load Balancer·
# Implement a load balancer for web servers:
# 1.	Add a new server to the cluster => add(server_id).
# 2.	Remove a bad server from the cluster => remove(server_id).
# 3.	Pick a server in the cluster randomly with equal probability => pick().
# 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
# 例1：
# At beginning, the cluster is empty => {}.
# add(1); add(2); add(3); pick()
# >> 1         // the return value is random, it can be either 1, 2, or 3.
# 思路：
# add/remove server id in O(1), we can only use hash map
# pick randomly in O(1) —— list
# 要求三个方法都是O(1)的时间复杂度
# 使用一个HashMap存储server_id（key）和对应的index（value），可以使得put和remove的时间都为常数级: 使用一个ArrayList存储所有的server_id，方便通过索引随机pick一台服务器
# remove方法：在arraylist中删除元素，如果仅仅使用其自带的remove方法，时间复杂度为O(n), 因为相当于在数组中删除元素，需要进行待删除元素后面所有元素的前移。因此在这里可以将待删除的元素先与数组中最后一个元素交换，然后删除最后一个元素即可。
# remove的实现：在交换待删除元素与最后一个元素的地方，由于要删除的server_id已经知道，所以只需要将最后一个位置的值覆盖待删除元素即可。然后在hashmap中更新其索引。后面remove的地方也要在hashmap中删除相应的k-v pair。
# 复杂度：
# 时间复杂度：三个方法都是constant time
# 空间复杂度：整体是O(n), n为服务器的数量，每个方法都是O(1)

# from collections import defaultdict
import random


class LoadBalancer:
    def __init__(self):
        self.dict = {}
        self.list = []

    def add(self, server_id: int):
        if server_id in self.dict:
            return
        self.list.append(server_id)
        self.dict[server_id] = len(self.list) - 1

    def remove(self, id2del: int):
        # 交换list[-1]与list[dict[val2del]], 然后将最后的元素删除
        if id2del in self.dict:
            last_id, idx_del = self.list[-1], self.dict[id2del]
            # 修改last_ele的映射（填补val2del）
            self.list[idx_del] = last_id
            self.dict[last_id] = idx_del
            # poplast
            self.list.pop()
            del self.dict[id2del]

    def pick(self):
        return random.choice(self.list)
