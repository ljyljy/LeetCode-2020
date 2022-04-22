# 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
#
#  你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
#
#  如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
#
#  说明:
#
#
#  如果题目有解，该答案即为唯一答案。
#  输入数组均为非空数组，且长度相同。
#  输入数组中的元素均为非负数。
#
#
#  示例 1:
#
#  输入:
# gas  = [1,2,3,4,5]
# cost = [3,4,5,1,2]
#
# 输出: 3
#
# 解释:
# 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
# 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
# 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
# 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
# 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
# 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
# 因此，3 可为起始索引。
#
#  示例 2:
#
#  输入:
# gas  = [2,3,4]
# cost = [3,4,3]
#
# 输出: -1
#
# 解释:
# 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
# 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
# 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
# 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
# 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
# 因此，无论怎样，你都不可能绕环路行驶一周。
#  Related Topics 贪心算法
#  👍 456 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法3：优化2
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        for i in range(n):
            remain, j = gas[i], i
            while remain >= cost[j]:
                new_j = (j + 1) % n  # 环
                remain = remain - cost[j] + gas[new_j]
                j = new_j
                if j == i: return i
            # 最远距离绕到了之前，所以 i 后边的都不可能绕一圈了
            if j < i: return -1
            i = j;  # ❤WHY？ -- i 直接跳到 j，外层 for 循环执行 i++，相当于从 j + 1 开始考虑
        return -1

    # 法2：优化1 -
    def canCompleteCircuit2(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        farIdx, farIdxRemain = [-1 for _ in range(n)], [None for _ in range(n)]
        for i in range(n):
            remain, j = gas[i], i
            while remain >= cost[j]:
                remain -= cost[j]  # 到达下个点后的剩余
                j = (j + 1) % n  # 环
                if farIdx[j] != -1 and farIdxRemain is not None:
                    remain += farIdxRemain[j]
                    j = farIdx[j]
                else:
                    remain += gas[j]  # 加上当前点的补给
                if j == i: return i
        return -1

    # 法1：暴力
    def canCompleteCircuit1(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        # 考虑从每一个点出发
        for i in range(n):
            remain, j = gas[i], i
            while remain >= cost[j]:
                new_j = (j + 1) % n  # 环
                remain = remain - cost[j] + gas[new_j]
                j = new_j
                if j == i: return i  # j 回到了起点i
        return -1
# leetcode submit region end(Prohibit modification and deletion)
