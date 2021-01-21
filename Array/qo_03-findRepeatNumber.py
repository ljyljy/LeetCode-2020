# 	qo_03. 数组中重复的数字
# 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
# 请找出数组中任意一个重复的数字。
# 说明：
# 2 <= n <= 100000
# 例1：
# 输入：
# [2, 3, 1, 0, 2, 5, 3]
# 输出：2 或 3
# 思考：
# （法2）若不能修改原数组呢？
# （法1、法3）若只能使用O(1)的额外空间，怎么办？（法3：且不修改原数组）
# 解题思路：
# 寻找数组中的重复数字，直接想到的方法是遍历数组，并使用 HashMap 统计每个数字的数量，遇到数量大于 1 的数字则返回。此方法时间复杂度和空间复杂度均为 O(N)。
# 题目指出 在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。 因此，可遍历数组并通过原地交换操作使元素的 索引 与 值 一一对应（即 nums[i] = i ）。因而，就能通过索引找到对应的值。
# 遍历中，当第二次遇到数字 x 时，一定有 nums[x] = x （因为第一次遇到 x 时已经将其交换至 nums[x] 处了）。利用以上方法，即可得到一组重复数字。
#
# 算法流程
# 遍历数组 nums ，设索引初始值为 i = 0:
# 若 nums[i] == i ： 说明此数字已在对应索引位置，无需交换，pass。
# 若 nums[nums[i]] == nums[i]： 说明索引 nums[i] 处的元素值也为 nums[i]，即找到一组相同值，返回此值 nums[i]；
# 否则： 当前数字是第一次遇到，因此交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置。
# 若遍历完毕尚未返回，则返回 -1 ，代表数组中无相同值。
# 复杂度分析：
# 时间复杂度 O(N)： 遍历数组使用O(N) ，每轮遍历的判断和交换操作使用O(1) 。
# 空间复杂度 O(1)： 使用常数复杂度的额外空间。
from typing import List


class Solution:
    # 如果没有重复数字，那么正常排序后，数字i应该在下标为i的位置，所以思路是:
    # 重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m), 那么我们就拿与下标m的数字交换。
    # 在交换过程中，如果有重复的数字发生，那么终止返回ture
    # 法1：只能使用O(1)的额外空间，但改变了原数组
    def findRepeatNumber(self, nums: List[int]) -> int:
        if not nums: return -1

        for i in range(len(nums)):
            while i != nums[i]:
                if nums[i] == nums[nums[i]]:  # ❤ 发现重复
                    return nums[i]
                nums[nums[i]], nums[i] = nums[i], nums[nums[i]]
        return -1
    # 注意！！！   ↑
    # Python 中， a, b = c, d 操作的原理是先暂存元组 (c, d) ，然后【 “按顺序”】 赋值给 a 和 b 。
    # 因此，若写为 nums[i], nums[nums[i]] = nums[nums[i]], nums[i] ，
    # 则 nums[i] 先被赋值，之后 nums[nums[i]] 指向的元素则会出错。


# 法2: # 不修改输入的数组。（但使用了额外空间）
import collections


def findRepeatNumber2(self, nums):
    if not nums: return -1
    dict_nums = collections.Counter(nums)

    for k, v in dict_nums.items():
        if v > 1: return k
    return -1

# 变题：# 法3: # 不修改输入的数组。空间O(1) 时间O(nlogn)【见ACW_14】
