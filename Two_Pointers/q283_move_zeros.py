# 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
#
#  示例:
#
#  输入: [0,1,0,3,12]
# 输出: [1,3,12,0,0]
#
#  说明:
#
#
#  必须在原数组上操作，不能拷贝额外的数组。
#  尽量减少操作次数。
#
#  Related Topics 数组 双指针
#  👍 654 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# 算法：双指针
# 算法思路
# 使用两个指针right和left，left为新数组的指针，right为原数组的指针，原数组指针向后扫，遇到非0的数就赋值给新数组的指针位置，并将新数组指针向后移动
# 代码思路
# 将两个指针先指向0，即数组头部
# right向后扫描，当遇到非0数即nums[right] != 0时，将其赋值给新数组指针指向的位置，即nums[left] = nums[right]，并将left向后移动一位
# 若新数组指针还未指向尾部，即剩余的位置都是0，将剩余数组赋值为0
from typing import List


class Solution:

    # 法1：可保证写次数最少
    def moveZeroes0(self, nums: List[int]) -> None:
        left, right = 0, 0
        while right < len(nums):
            # left和right在遇到0后不再一起走，left驻留，right++
            # 需要将后续非0元素全部前移
            if nums[right] != 0:
                if left != right:  # 保证left在right后面
                    # ∵为了减少写次数 ∴ 不swap，前移完成后，left往后全赋值0即可
                    nums[left] = nums[right]
                left += 1
            right += 1

        while left < len(nums):
            if nums[left] != 0:
                nums[left] = 0
            left += 1

    # 法2: 基于 swap 的版本，无法保证写次数最小。但比较好理解。
    def moveZeroes(self, nums: List[int]) -> None:
        left, right = 0, 0
        while right < len(nums):
            if nums[right] != 0:
                nums[right], nums[left] = nums[left], nums[right]
                left += 1
            right += 1

# leetcode submit region end(Prohibit modification and deletion)
