# 	31. 下一个排列
# 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
# 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
# 必须原地修改，只允许使用额外常数空间。
# 以下是一些例子，输入位于左侧列，其相应输出位于右侧列
# 例1：
# 输入:[1,3,2,3]
# 输出:[1,3,3,2]
# 例 2:
# 输入:[4,3,2,1]
# 输出:[1,2,3,4]
# 其他示例：
# [1, 2, '3(拐点)', 6, 5, 4] → [1, 2, 4, 3, 5, 6]
# 1,2,3 → 1,3,2
# 3,2,1 → 1,2,3
# 1,1,5 → 1,5,1
# 相关题目
# 917. 回文排列II388. 第k个排列51. 上一个排列16. 带重复元素的排列
# 从最后一个位置开始，找到一个上升点(从后往前看下降序列拐点)，上升点之前的无需改动。 然后，翻转上升点之后的降序。 在降序里，找到第一个比上升点大的，交换位置。
from typing import List


class Solution:
    def nextPermutation(self, num: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        global i
        if len(num) <= 1: return
        n = len(num)
        for i in reversed(range(n - 1)):  # [n-2 :-1: 0]
            if num[i] < num[i + 1]:
                break  # 当前i就是拐点
        else:  # （不可以直接写if i==0，因为拐点可能在0！！！）❤❤❤
            # else: 没找到且i == 0, 说明本身是降序序列，全部翻转
            num[:] = list(reversed(num))  # 必须写num[:] ❤❤❤
            return  # ↑ 否则num只是浅拷贝，不是引用！函数中的num改变，函数外num依旧不变
        # i != 0 (找到拐点i), 寻找拐点后的最小数j
        # （j通常为n-1，若与i相等则向前找到第一个【严格比i大】的数）, 交换
        for j in reversed(range(i + 1, n)):  # range(n-1, i, -1): # [n-1: -1 : i+1]
            if num[j] > num[i]:
                num[j], num[i] = num[i], num[j]
                break
        num[i + 1:] = list(reversed(num[i + 1:]))

    # 法2：若允许开辟额外空间，且无需再num原地修改
    # DFS + 剪枝.先给数组排序, 然后用dfs按顺序寻找permutation, 只要发现prefix与nums不一样, 立即剪枝,
    # 直到找到nums后, 停止剪枝, 下一个就是result, 找到result之后就剪掉剩下的所有枝.
    # 特殊情况: 如果没有下一个了, 那么说明nums是纯倒序, 则result是sorted(nums)
    def nextPermutation2(self, nums):
        sortedNums = sorted(nums)
        self.used = [0] * len(nums)
        self.found = False
        self.result = None

        self.dfs(sortedNums, nums, [])

        if self.result is not None:
            return self.result
        else:
            return sortedNums

    def dfs(self, sortedNums, nums, pre):
        if self.result is not None: return

        if not self.found:
            n = len(pre)  # 剪枝↓
            if pre[:n] != nums[:n]: return

        if pre == nums:
            self.found = True
            return

        if len(pre) == len(nums):
            self.result = pre[:]
            return

        for i in range(len(sortedNums)):  # ↓ 去除重复元素的排列
            if self.used[i] == 0 and not (i > 0 and sortedNums[i - 1] == sortedNums[i] and not self.used[i - 1]):
                self.used[i] = 1
                self.dfs(sortedNums, nums, pre + [sortedNums[i]])
                self.used[i] = 0


if __name__ == "__main__":
    sol = Solution()
    res = sol.nextPermutation([1, 2, 3, 6, 5, 4])
    print(res)
