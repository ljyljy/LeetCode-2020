# 	Q9_609. 两数和-小于或等于目标值
# 给定一个整数数组，找出这个数组中有多少对的和是小于或等于目标值, 返回对数。
# 例1：
# 输入: nums = [2, 7, 11, 15], target = 24.
# 输出: 5.
# 解释:
# 2 + 7 < 24
# 2 + 11 < 24
# 2 + 15 < 24
# 7 + 11 < 24
# 7 + 15 < 24
# 相关题目：
# 1879. 两数之和 VII1796. 差为K的数对数量689. 两数之和 - BST版本610. 两数和 - 差等于目标值533. 两数和的最接近值

class Solution:
    # 法1：二分 -- O(nlogn)
    def twoSum5(self, nums, target):
        nums.sort()  # 排序，或 nums = sorted(nums)
        n, ans = len(nums), 0
        # 对于每个i，二分找到最大的j nums[i]+nums[j]<=target
        for i in range(n):
            left, right = i, n
            while left + 1 < right:
                mid = left + right >> 1
                # 不大于target 可以提高下界
                if nums[i] + nums[mid] <= target:
                    left = mid
                else:
                    right = mid
            ans += left - i
        return ans

    # 法2：双指针 -- O(n^2)
    def twoSum5_2(self, nums, target):
        nums.sort()
        lf, rt, cnt = 0, len(nums) - 1, 0
        while lf < rt:
            if nums[lf] + nums[rt] > target:
                rt -= 1
            else:
                cnt += rt - lf  # 先更新cnt，再lf++
                lf += 1
        return cnt
