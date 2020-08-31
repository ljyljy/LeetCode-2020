# 	Q9_6. 合并排序数组II
# 合并两个有序升序的整数数组A和B变成一个新的数组。新数组也要有序。
# 挑战：你能否优化你的算法，如果其中一个数组很大而另一个数组很小？
# 例1：
# 输入: A=[1,2,3,4], B=[2,4,5,6]
# 输出: [1,2,2,3,4,4,5,6]
# 样例解释: 返回合并后的数组。
# 相关题目：839. 合并两个排序的间隔列表548. 两数组的交集 II547. 两数组的交集165. 合并两个排序链表104. 合并k个排序链表64. 合并排序数组

class Solution:
    def mergeSortedArr(self, A, B):
        i, j = 0, 0
        C = []
        while i < len(A) and j < len(B):
            if A[i] < B[j]:
                C.append(A[i])
                i += 1
            else:
                C.append(B[j])
                j += 1
        while i < len(A):
            C.append(A[i])
            i += 1
        while j < len(B):
            C.append(B[j])
            j += 1
        return C
