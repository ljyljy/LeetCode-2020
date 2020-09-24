class Solution:
    def largestSubarrray(self, A, K):
        res = []
        start = 0
        for i in range(len(A) - K + 1):  # i ∈ [0, len(A)-K ]
            for j in range(K):
                if A[i + j] > A[start + j]:
                    start = i
                    break  # 继续下一个i
                elif A[i + j] < A[start + j]:
                    break  # 继续下一个i

        for k in range(K):
            res.append(A[start + k])
        return res
