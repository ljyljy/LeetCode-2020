# Follow up II：多个数组如何处理？ 不用 HashMap 怎么做？
# http://www.jiuzhang.com/solutions/intersection-of-arrays/
# 	Q9_793. 多个数组的交集
# 给定多个数组，编写一个函数来计算它们交集的大小。
# 说明： 输入的所有数组元素总数不超过500000。 题目数据每个数组里的元素没有重复。
# 例1：
# 输入:  [[1,2,3],[3,4,5],[3,9,10]]
# 输出:  1             解释: 只有3出现在三个数组中。
# 例 2:
# 输入: [[1,2,3,4],[1,2,5,6,7][9,10,1,5,2,3]]
# 输出:  2             解释: 交集是[1,2].

class Solution:
    def intersectionOfArrays(self, arrs):
        M = {}
        for arr in arrs:
            for num in arr:
                M[num] = M[num] + 1 if num in M else 1
        cnt = 0
        for k, v in M.items():
            if v == len(arrs): cnt += 1
        return cnt
