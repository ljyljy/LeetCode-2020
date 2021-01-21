# Last number that number^2 <= x
# follow up: what if return a double, not an integer?
# 	586. 对x开根II · Sqrt(x) II – 二分答案
# 实现 double sqrt(double x) 并且 x >= 0。
# 计算并返回x开根后的值。
# 你不需要在意结果的精确度，我们会帮你输出结果。
# 例1:
# 输入: n = 2 			输出: 1.41421356
# 例2:
# 输入: n = 3			输出: 1.73205081。
# 挑战 O(logn)
# 相关题目 1791. 简单查找1728. 卡牌分组777. 完全平方数586. 对x开根II428. x的n次幂14. 二分查找
#
#  Related Topics 数学 二分查找
#  👍 449 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# 思路：直接对答案可能存在的区间进行二分 => 二分答案
# 注意：判断区间的时候一个小技巧： mid * mid == x 中使用乘法可能会溢出，写成 mid == x / mid 即可防止溢出，不需要使用long或者BigInteger
class Solution:
    def sqrt(self, x) -> int:
        left, right, eps = 0, x, 1e-12
        # ❤注意小数情况，若x<1将右边界扩大到1可避免结果错误（比如0.04=0.2*0.2）
        # 如果我们不将x右边界扩大到1，则无法在[0,0.04]的区间范围内找到正解！！！
        if right < 1: right = 1

        while left + eps < right:
            # mid = left + right >> 1  # 不可这么写！这样是整除！！！
            mid = left + (right - left) / 2
            # ↓ right可能变化！而条件mid^2 < x永远不变！
            if mid * mid < x:  # ❤ 是x 非right！ ↑
                left = mid
            else:
                right = mid

        return left


if __name__ == "__main__":
    sol = Solution()
    res = sol.sqrt(0.04)
    print(res)
# leetcode submit region end(Prohibit modification and deletion)
