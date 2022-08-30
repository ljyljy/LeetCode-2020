from typing import List

KEYBOARD = {  # 放到class外！可以省去self.dict~
    '2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl',
    '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz',
}


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits: return []
        rst = []
        self.dfs(digits, 0, [], rst)
        return rst

    def dfs(self, digits, idx, path, rst):
        if idx == len(digits):
            rst.append(''.join(path))
            return
        for letter in KEYBOARD[digits[idx]]:
            self.dfs(digits, idx + 1, path + [letter], rst)


if __name__ == "__main__":
    sol = Solution()
    res = sol.letterCombinations("23")
    print(res)
