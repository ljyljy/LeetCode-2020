# 	Q9_10. 字符串的全排列II 【字符串】
# 给定一个可包含重复的字符串，返回所有不重复的全排列。
# 例1：
# Input: "abb"
# Output: ["abb", "bab", "bba"]
# Example 2:
# Input: "aabb"
# Output: ["aabb", "abab", "baba", "bbaa", "abba", "baab"]
# 基本上和Permutation实现的思路一样，不同的是string的特性，不能直接append 还有pop，也传导的不是reference,所以不需要deepcopy，还有判断是否加一个数进入Permutation的顺序影响时间复杂度， if visited 在前面不会超时。
# 关于最后加元素进Permutation和移除元素进permutation 有两种方法，一种是直接在传参数的时候加，这样后面不需要移除元素， 另一种是在传参前加传参后移除。

class Solution:
    def stringPermutation2(self, str):
        if not str: return []
        chars = list(sorted(str))
        used = [False] * len(chars)  # [False for _ in range(len(str))]
        rst = []
        self.backtrack(chars, [], used, rst)
        return rst

    def backtrack(self, str, path, used, rst):
        if len(path) == len(str):
            rst.append(''.join(path))
            return

        for i in range(len(str)):
            if used[i]: continue  # 不能跳过一个a选下一个a ↓
            if i > 0 and str[i] == str[i - 1] and not used[i - 1]:
                # a' a" b => （1）a' a" b => √ （2） a" a' b => x
                continue
            used[i] = True
            self.backtrack(str, path + [str[i]], used, rst)
            used[i] = False


if __name__ == "__main__":
    sol = Solution()
    rst = sol.stringPermutation2('abc')
    print(rst)
