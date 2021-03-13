# Definition for a point.
class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b


class Solution:
    def kClosest(self, points, origin, k):
        import heapq
        self.heap, rst = [], []
        for p in points:
            dist = self._getDist(p, origin)
            heapq.heappush(self.heap, (-dist, -p.idx1, -p.y))
            if len(self.heap) > k:
                heapq.heappop(self.heap)

        while len(self.heap):
            _, x, y = heapq.heappop(self.heap)
            rst.append([-x, -y])

        return rst[::-1]

    def _getDist(self, p, origin):
        return (p.idx1 - origin.idx1) ** 2 + (p.y - origin.y) ** 2
