# 给出一个区间的集合，请合并所有重叠的区间。
#
#
#
#  示例 1:
#
#  输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
# 输出: [[1,6],[8,10],[15,18]]
# 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
#
#
#  示例 2:
#
#  输入: intervals = [[1,4],[4,5]]
# 输出: [[1,5]]
# 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
#
#  注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
#
#
#
#  提示：
#
#
#  intervals[i][0] <= intervals[i][1]
#
#  Related Topics 排序 数组
#  👍 580 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals: return []
        # 按照区间start进行排序
        intervals = sorted(intervals, key=lambda x: x[0])
        rst, last = [], intervals[0]
        # 如果两段区间有交集的话，合并两段区间
        # 没有的话，将最后的区间加入答案，并令新的区间作为最后的区间
        for i in range(1, len(intervals)):
            if self._has_intersection(last, intervals[i]):
                last = self._merge_intervals(last, intervals[i])
            else:
                rst.append(last)
                last = intervals[i]
        rst.append(last)  # 最后还剩last区间没有加入rst
        return rst

    # 判断区间是否有交集，要满足较大的start小于等于较小的end
    def _has_intersection(self, intv1, intv2):
        return max(intv1[0], intv2[0]) <= min(intv1[-1], intv2[-1])

    def _merge_intervals(self, intv1, intv2):
        return [min(intv1[0], intv2[0]), max(intv1[-1], intv2[-1])]
# leetcode submit region end(Prohibit modification and deletion)
