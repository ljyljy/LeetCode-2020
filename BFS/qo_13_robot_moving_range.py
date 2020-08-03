# 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
# 格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
# 它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
#
#
#
#  示例 1：
#
#  输入：m = 2, n = 3, k = 1
# 输出：3
#
#
#  示例 2：
#
#  输入：m = 3, n = 1, k = 0
# 输出：1
#
#
#  提示：
#
#
#  1 <= n,m <= 100
#  0 <= k <= 20
#
#  👍 134 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

from queue import Queue
from collections import deque

# 只需要保证从左上角(0,0)开始，只向下、右搜索，就可以不重复搜索完所有结果
dirs = [(1, 0), (0, 1)]  # 两方向足矣！❤❤❤


# ,(-1, 0), (0, -1)] # 因此其实无需向上、向左搜索（重复了）

# 求num各个数位之和
def digitSum(num):
    sum = 0
    while num:
        sum += num % 10
        num //= 10
    return sum


class Solution:
    # Deque
    def movingCount(self, m: int, n: int, k: int) -> int:
        queue = deque()
        queue.append((0, 0))  # 追加在右边
        used = set()
        while queue:
            x, y = queue.popleft()  # 从左边pop
            if (x, y) in used or not self.isBounded(x, y, m, n) \
                    or digitSum(x) + digitSum(y) > k:
                continue

            used.add((x, y))
            for dir in dirs:
                new_x, new_y = x + dir[0], y + dir[1]
                queue.append((new_x, new_y))

        return len(used)

    # Queue
    def movingCount2(self, m: int, n: int, k: int) -> int:
        q = Queue()
        q.put((0, 0))
        used = set()
        while not q.empty():  # while q 会TLE!
            x, y = q.get()
            if (x, y) in used or not self.isBounded(x, y, m, n) \
                    or digitSum(x) + digitSum(y) > k:
                continue

            used.add((x, y))
            for dir in dirs:
                new_x, new_y = x + dir[0], y + dir[1]
                q.put((new_x, new_y))

        return len(used)

    def isBounded(self, x, y, m, n):
        return 0 <= x < m and 0 <= y < n

# leetcode submit region end(Prohibit modification and deletion)
