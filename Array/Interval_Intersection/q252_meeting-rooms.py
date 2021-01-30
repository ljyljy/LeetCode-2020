# 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判
# 断一个人是否能够参加这里面的全部会议。
#
#
#
#  示例 1：
#
#
# 输入：intervals = [[0,30],[5,10],[15,20]]
# 输出：false
#
#
#  示例 2：
#
#
# 输入：intervals = [[7,10],[2,4]]
# 输出：true
#
#
#
#
#  提示：
#
#
#  0 <= intervals.length <= 104
#  intervals[i].length == 2
#  0 <= starti < endi <= 106
#
#  Related Topics 排序
#  👍 59 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        if not intervals: return True
        intervals = sorted(intervals, key=lambda x: x[0])
        last = intervals[0]
        for i in range(1, len(intervals)):
            if self._has_intersection(last, intervals[i]):
                return False
            last = intervals[i]  # 勿忘
        return True

    def _has_intersection(self, intv1, intv2):
        if max(intv1[0], intv2[0]) < min(intv1[1], intv2[1]):
            return True  # ↑ 严格小于，相等的时候：结束后正好可以开始下一场会议，时间不冲突
        return False

# leetcode submit region end(Prohibit modification and deletion)
