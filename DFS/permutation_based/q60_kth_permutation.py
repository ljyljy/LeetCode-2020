# 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
#
#  按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
#
#
#  "123"
#  "132"
#  "213"
#  "231"
#  "312"
#  "321"
#
#
#  给定 n 和 k，返回第 k 个排列。
#
#  说明：
#
#
#  给定 n 的范围是 [1, 9]。
#  给定 k 的范围是[1, n!]。
#
#
#  示例 1:
#
#  输入: n = 3, k = 3
# 输出: "213"
#
#
#  示例 2:
#
#  输入: n = 4, k = 9
# 输出: "2314"
#
#  Related Topics 数学 回溯算法
#  👍 290 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 时间复杂度 ≤ O(n^2)
    def getPermutation(self, n: int, k: int) -> str:
        nums = list(range(1, n + 1))
        Fac = [1]
        for i in range(1, n): Fac.append(Fac[-1] * i)
        ans, k = "", k - 1
        for i in range(n):
            m = k // Fac[n - 1 - i]
            k = k % Fac[n - i - 1]
            ans += str(nums[m])
            nums.pop(m)
        return str(ans)

    # O(n*k)
    def getPermutation2(self, n: int, k: int) -> str:
        res, visited = [], set()
        self.dfs(n, k, [], visited, res)
        return res[k - 1]

    def dfs(self, n, k, path, visited, res):
        if len(res) == k: return  # 剪枝，dfs至第k层为止(返回k-1层的结果即可)
        if len(path) == n:  # 将path：['2','1','3']合并为['213'](res列表元素为str)
            res.append(''.join(path))  # ❤❤❤
            return
        for i in range(1, n + 1):  # ∵num为1~n的不重复数字
            if i in visited: continue
            visited.add(i)  # ↓ path：['2','1','3']❤❤❤
            self.dfs(n, k, path + [str(i)], visited, res)
            visited.remove(i)


if __name__ == "__main__":
    sol = Solution()
    res = sol.getPermutation(3, 3)
    print(res)
# leetcode submit region end(Prohibit modification and deletion)
