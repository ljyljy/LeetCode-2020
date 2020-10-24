class Solution:
    """
      @param: A: an integer array
      @param: k: a postive integer <= length(A)
      @param: targer: an integer
      @return: A list of lists of integer
      """

    def kSumII(self, A, k, target):
        if not A or k <= 0: return []
        A = sorted(A)
        rst = []
        self.dfs(A, k, target, 0, [], rst)
        return rst

    def dfs(self, A, k, target, idx, path, rst):
        if k == 0 and target == 0:
            rst.append(list(path))
            return
        if k == 0 or target <= 0: return

        for i in range(idx, len(A)):
            path.append(A[i])
            self.dfs(A, k - 1, target - A[i], i + 1, path, rst)
            path.pop()
