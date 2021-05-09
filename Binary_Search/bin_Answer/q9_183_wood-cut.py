# 	183. 木材加工
# 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。
# 木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0 即可。
# 样例 1
# 输入:
# L = [232, 124, 456]
# k = 7
# 输出: 114
# Explanation: 我们可以把它分成114cm的7段，而115cm不可以(456/4=114)
# 样例 2
# 输入:
# L = [1, 2, 3]
# k = 7
# 输出: 0
# 挑战：O(n log Len), Len为 n 段原木中最大的长度.）
# 相关： 1791. 简单查找1671. 玩游戏
# 思路: 使用九章算法强化班中所讲的基于答案值域的二分法。
# 木头长度的范围在 1 到 max(L)，在这个范围内二分出一个长度 length，然后看看以这个 wood length 为前提的基础上，能切割出多少木头，如果少于 k 根，说明要短一些才行，如果多余 k，说明可以继续边长一些。
#
# 算法：二分
# 题目意思是说给出 n 段木材L[i], 将这 n 段木材切分为至少 k 段，这 k 段等长，
# 若直接枚举每段木材的长度则时间复杂度高达 O(n*maxL), 我们可以使用二分答案来优化枚举木材长度的过程
# •	设left=0,即木材长度最小为0，设right=max_L 即所有木材中最长的长度，因为结果是不可能大于这个长度的，mid = left + right
# •	若长度为mid时不能完成，说明太长了，那么我们往区间[left,mid]搜，
# •	若可以完成，说明也许可以更长，那么我们往[mid,right]搜,
# •	在check/count函数中，我们判断用所有木头除当前mid的值的和是否大于等于k，若小于则说明该mid不可行, 若大于等于则说明mid可行
# •	由于判断条件是left + 1 < right,最后结果就是left的值
# 复杂度分析
# •	时间复杂度O(nlog（n）)
# o	二分查找的复杂度
# •	空间复杂度O(size(L))
# o	只有数组L

class Solution:
    def woodCut(self, L, k):
        n = len(L)  # 给定的原木材根数
        if n == 0: return 0
        max_Li = max(L)
        left, right = 0, max_Li

        def count(mid):
            cnt = 0
            for i in range(n):
                cnt += L[i] // mid  # 计算当前长度(mid)下能分成几段
            if cnt >= k:
                return True  # 如果还能分更长的，返回true
            else:
                return False

        while left + 1 < right:
            mid = left + right >> 1
            if count(mid):  # 如果还能分更长的，则尝试增加mid长度(∵题目要求找最大切割长度)
                left = mid  # 往[mid,right]走
            else:  # 如果不能分更长的，则缩短mid长度
                right = mid  # 往[left,mid]走
        if count(right): return right
        # if count(left):
        return left
