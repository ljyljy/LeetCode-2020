# 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
#
#
#
#  你可以假设数组是非空的，并且给定的数组总是存在多数元素。
#
#
#
#  示例 1:
#
#  输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
# 输出: 2
#
#
#
#  限制：
#
#  1 <= 数组长度 <= 50000
#
#
#
#  注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
#
#
#  Related Topics 位运算 分治算法
#  👍 58 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List

# 思路：使用val & count计数，相同++不同--，最后返回val。 因为众数超过总数的一半，所以不会被减到0以下。
#
# // 即：不同的数可以互相抵消，众数一定是最后落单的那个
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        if not nums: return None
        val, cnt = -1, 0
        for num in nums:
            if not cnt:
                val, cnt = num, 1
            elif val == num:
                cnt += 1
            else:
                cnt -= 1
        return val
# leetcode submit region end(Prohibit modification and deletion)
