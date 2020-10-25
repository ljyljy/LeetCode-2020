# 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
#
#
#  B.length >= 3
#  存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B
# [B.length - 1]
#
#
#  （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
#
#  给出一个整数数组 A，返回最长 “山脉” 的长度。
#
#  如果不含有 “山脉” 则返回 0。
#
#
#
#  示例 1：
#
#  输入：[2,1,4,7,3,2,5]
# 输出：5
# 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
#
#
#  示例 2：
#
#  输入：[2,2,2]
# 输出：0
# 解释：不含 “山脉”。
#
#
#
#
#  提示：
#
#
#  0 <= A.length <= 10000
#  0 <= A[i] <= 10000
#
#  Related Topics 双指针
#  👍 112 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def longestMountain(self, A: List[int]) -> int:
        n, left, rst = len(A), 0, 0
        if n < 3: return 0
        while left + 2 < n:
            right = left + 1
            if A[left] < A[right]:
                while right + 1 < n and A[right] < A[right + 1]:
                    right += 1  # 跳出时：①right为山顶，②到达边界right==n-1
                # 到达山顶，后续下坡
                if right + 1 < n and A[right] > A[right + 1]:
                    while right + 1 < n and A[right] > A[right + 1]:
                        right += 1  # 跳出时：①子山脉end，到达下一个山脉首位，②边界
                    rst = max(rst, right - left + 1)
                else:
                    right += 1  # right后移至下一个山脉首位
            left = right  # 更新赋值给left，搜索下一个山脉
        return rst

# leetcode submit region end(Prohibit modification and deletion)
