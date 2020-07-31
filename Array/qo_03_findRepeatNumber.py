# 找出数组中重复的数字。
#
#
# 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
# 找出数组中任意一个重复的数字。
#
#  示例 1：
#
#  输入：
# [2, 3, 1, 0, 2, 5, 3]
# 输出：2 或 3
#
#
#
#
#  限制：
#
#  2 <= n <= 100000
#  Related Topics 数组 哈希表


# leetcode submit region begin(Prohibit modification and deletion)
import collections
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
                if nums[i] == nums[nums[i]]:  # ❤
                    return nums[i]
                nums[nums[i]], nums[i] = nums[i], nums[nums[i]]
        return -1

    # 注意！！！   ↑
    # Python 中， a, b = c, d 操作的原理是先暂存元组 (c, d) ，然后【 “按顺序”】 赋值给 a 和 b 。
    # 因此，若写为 nums[i], nums[nums[i]] = nums[nums[i]], nums[i] ，
    # 则 nums[i] 先被赋值，之后 nums[nums[i]] 指向的元素则会出错。

    # 法2: # 不修改输入的数组。（但使用了额外空间）
    def findRepeatNumber2(self, nums):
        if not nums: return -1
        dict_nums = collections.Counter(nums)

        for k, v in dict_nums.items():
            if v > 1:
                return k
        return -1

    # 变题
    # 14. 不修改数组找出重复的数字
    # 给定一个长度为 n+1 的数组nums，数组中所有的数均在 1∼n 的范围内，其中 n≥1。
    # 请找出数组中任意一个重复的数，但不能修改输入的数组。 【法2】
    # 思考题：如果只能使用 O(1) 的额外空间，该怎么做呢？【法3】
    # 法3：时间O(nlogn), 空间O(1)
    def duplicateInArray(self, nums):
        L, R = 1, len(nums) - 1
        while L < R:
            mid = L + R >> 1  # [L, mid],[mid+1, R]
            cnt_L, len_L = 0, mid - L + 1
            for x in nums:
                cnt_L += (L <= x <= mid)
            if cnt_L > len_L:  # 左区间范围内: 数的个数 > 坑个数
                R = mid  # 抽屉原理：左区间定存在重复数
            else:
                L = mid + 1  # 查找右半边
        return L  # 或R

        # leetcode submit region end(Prohibit modification and deletion)
