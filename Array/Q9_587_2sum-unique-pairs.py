class Solution:
    """
    @param nums: an array of integer
    @param target: An integer
    @return: An integer
    """

    def twoSum6(self, nums, target):
        if not nums or len(nums) < 2: return 0
        cnt = 0
        last_pair = (None, None)
        left, right = 0, len(nums) - 1
        nums.sort()

        while left < right:
            if nums[left] + nums[right] == target:
                if last_pair != (nums[left], nums[right]):
                    cnt += 1
                last_pair = (nums[left], nums[right])
                left, right = left + 1, right - 1

            elif nums[left] + nums[right] < target:
                left += 1
            elif nums[left] + nums[right] > target:
                right -= 1

        return cnt


if __name__ == "__main__":
    sol = Solution()
    cnt = sol.twoSum6([1, 1], 2)
    print(cnt)
