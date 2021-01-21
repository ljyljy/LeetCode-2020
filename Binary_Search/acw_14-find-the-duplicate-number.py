# 	acw_14. 不修改数组找出重复的数字 find-the-duplicate-number
# 给定一个长度为 n+1 的数组nums，数组中所有的数均在 1∼n 的范围内，其中 n≥1。
# 请找出数组中任意一个重复的数，但不能修改输入的数组。 [法2]
# 思考题：如果只能使用 O(1) 的额外空间，该怎么做呢？【法3】
# 思路：分治 + 抽屉原理 —> 左边长度(n/2) + [mid] + 右边长度(n/2) =  n + 1
# n+1个数，放在n个抽屉nums[]里。要求不修改数组找出重复的某数。
# 每次锁定新区间：某一边(区间)肯定满足 数的个数>坑的个数；cnt_L>区间长度(mid-L+1),
# 答案定在满足条件的那一半区间中，继续搜索。
# 二分模板：2.1.1 二分法模板
# AcWing - https://www.acwing.com/blog/content/31/


class Solution:
    # 法3：时间O(nlogn), 空间O(1)
    def duplicateInArray(self, nums):
        L, R = 1, len(nums) - 1  # 二分对象是区间[1,n], 而非num[0~n]!!!
        while L < R:
            mid = L + R >> 1  # [L, mid],[mid+1, R]
            cnt_L, len_L = 0, mid - L + 1
            for num in nums:
                cnt_L += (L <= num <= mid)  # num∈[L, mid] -> 数的个数
            if cnt_L > len_L:  # 左区间范围内: 数的个数 > 坑个数
                R = mid  # 抽屉原理：左区间定存在重复数
            else:
                L = mid + 1  # 查找右半边
        return L  # 或R

# 相关题目(二分 + 分治)：2.1.5 二分法例题：魔术索引
#
