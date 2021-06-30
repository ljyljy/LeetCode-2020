# 	Q9_437. 书籍复印
# 给定 n 本书, 第 i 本书的页数为 pages[i]. 现在有 k 个人来复印这些书籍, 而每个人只能复印编号连续的一段的书, 比如一个人可以复印 pages[0], pages[1], pages[2], 但是不可以只复印 pages[0], pages[2], pages[3] 而不复印 pages[1].
# 所有人复印的速度是一样的, 复印一页需要花费一分钟, 并且所有人同时开始复印. 怎样分配这 k 个人的任务, 使得这 n 本书能够被尽快复印完?
# 返回完成复印任务最少需要的分钟数.
# 示例 1：
# 输入: pages = [3, 2, 4], k = 2
# 输出: 5
# 解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟.
# 样例 2:
# 输入: pages = [3, 2, 4], k = 3
# 输出: 4
# 解释: 三个人各复印一本书.
# 提示：
# 	书籍页数总和小于等于2147483647。
# 挑战：
# 	时间复杂度 O(nk)
# 相关： 1219. 加热器
#
# 思路: 使用九章算法强化班中所讲的基于答案值域的二分法。
# 答案的范围在 max(pages)~cnt(pages) 之间，每次二分到一个时间 time_limit 的时候，用贪心法从左到右扫描一下 pages，看看需要多少个人来完成复印。
# 如果这个值 <= k，那么意味着大家花的时间可能可以再少一些，如果 > k 则意味着人数不够，需要降低工作量。
# O(n log(cnt) )是该问题时间复杂度上的最优解法.

class Solution:
    def copyBooks(self, pages, k):
        if not pages: return 0
        left, right = max(pages), sum(pages)
        while left + 1 < right:
            mid = left + right >> 1
            if self.check(pages, mid) <= k:  # 所需人数在当前mid限制下，还可以再少
                right = mid  # 说明复印时间能够再少，查找左区间（mid以左）
            else:
                left = mid  # 在mid限制下，人数不够，说明复印时间相应增加（mid以右）
        if self.check(pages, left) <= k:
            return left
        return right

    def check(self, pages, time_limit):
        cnt, time_cost = 1, 0
        for page in pages:
            if page + time_cost > time_limit:
                cnt += 1
                time_cost = 0
            time_cost += page
        return cnt
