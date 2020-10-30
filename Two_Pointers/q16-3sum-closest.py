# 	Q16. 最接近的三数之和 · 3Sum Closest
# 给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。 只需要返回三元组之和，无需返回三元组本身。
# 挑战:  O(n^2) 时间, O(1) 额外空间.
# 例1：
# 输入:[2,7,11,15],3		输出:20
# 解释:  2+7+11=20
# 例2:
# 输入:[-1,2,1,-4],1		输出:2
# 解释:  -1+2+1=2.
# 相关题目 918. 三数之和533. 两数和的最接近值57. 三数之和
#
# 排序后。 固定一个点，利用双指针的方式，扫描，记录答案即可。

import sys
from typing import List


class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans = None
        for i in range(len(nums)):
            left, right = i + 1, len(nums) - 1
            while left < right:
                tmp_sum = nums[i] + nums[left] + nums[right]
                print(nums[i], nums[left], nums[right], tmp_sum)
                if ans is None or abs(target - ans) > abs(target - tmp_sum):
                    ans = tmp_sum  # 不可写if not ans --> 特殊情况：ans为0（但有效）
                if tmp_sum == target:
                    return target
                elif tmp_sum > target:
                    right -= 1
                else:
                    left += 1
        return ans


if __name__ == "__main__":
    nums, target = [-3, 0, 1, 2], 1
    sol = Solution()
    ans = sol.threeSumClosest(nums, target)
    print(ans)
