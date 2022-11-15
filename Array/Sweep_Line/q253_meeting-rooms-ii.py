class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        if not intervals: return 0
        points = []
        for interval in intervals:
            points.append([interval[0], 1])
            points.append([interval[1], -1])
        ans, ongoing_meetings = 0, 0
        for _, delta in sorted(points):  # 注意！按序@sorted!!!
            ongoing_meetings += delta
            ans = max(ans, ongoing_meetings)
        return ans
