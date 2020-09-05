# 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
#
#  说明：
#
#  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
#
#  示例 1:
#
#  输入: [2,2,3,2]
# 输出: 3
#
#
#  示例 2:
#
#  输入: [0,1,0,1,0,1,99]
# 输出: 99
#  Related Topics 位运算
#  👍 411 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法0：位运算 O(1)
    def singleNumber(self, nums: List[int]) -> int:
        # first appearance:
        # add num to seen_once
        # don't add to seen_twice because of presence in seen_once

        # second appearance:
        # remove num from seen_once
        # add num to seen_twice

        # third appearance:
        # don't add to seen_once because of presence in seen_twice
        # remove num from seen_twice
        seen_once = seen_twice = 0
        for num in nums:
            seen_once = ~seen_twice & (num ^ seen_once)
            seen_twice = ~seen_once & (num ^ seen_twice)
        return seen_once

    # 法1：HashSet
    def singleNumber1(self, nums: List[int]) -> int:
        # 3×(a+b+c)−(a+a+a+b+b+b+c)=2c
        return (3 * sum(set(nums)) - sum(nums)) // 2

    # 法2：HashMap + Counter
    def singleNumber2(self, nums: List[int]) -> int:
        from collections import Counter
        dictt = Counter(nums)
        for k in dictt.keys():
            if dictt[k] == 1:
                return k
# 法3:
# class Solution {
#     public int singleNumber(int[] nums) {
#         int ones = 0, twos = 0;
#         for (int x: nums) {
#             // 之前出现过两次的，这次再出现就是出现了三次
#             int threes = twos & x;
#
#             // 之前出现过两次，这次没出现，是出现了两次。
#             // 之前出现过一次的，这次再出现，也是出现了两次。
#             twos = (twos & ~x) | (ones & x);
#
#             // 统计记录出现了奇数次的，并从其中清除出现三次的。
#             // 这样ones里面始终只会记录出现了一次的。
#             ones = ones ^ x;
#             ones &= ~threes;
#         }
#         return ones;
#     }
# }
# leetcode submit region end(Prohibit modification and deletion)
