# 	Q9-31. 数组划分
# 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
# 所有小于k的元素移到左边，所有大于等于k的元素移到右边
# 返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k
# 说明： 你应该真正的划分数组 nums，而不仅仅只是计算比 k 小的整数数，如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。
# 挑战：使用 O(n) 的时间复杂度在数组上进行划分。
# 示例：
# 输入: [3,2,2,1],2
# 输出:1
# 解释: 真实的数组为[1,2,2,3].所以返回 1。
# 相关题目
# 769. 螺旋矩阵761. 最小子集625. 数组划分II373. 奇偶分割数组144. 交错正负数96. 链表划
# 思路：快速选择算法：通过头尾指针跳过小于k的前缀和大于等于k的后缀，可以找到与第一个大于等于k的值和最后一个小于k的值。进行交换后可达到划分数组的目的，直到找到两个指针相遇为止。  伪代码如下：
# •	令left = 0，right = length-1。
# •	当nums[left] < k时，left指针向右移动。
# •	当nums[right] >= k时，right指针向左移动。
# •	如果left <= right，交换两个值。
# •	如果left > right，返回left作为最终结果，否则返回第二步
# 时间复杂度O(n)：最多扫描一遍数组，时间复杂度为O(n)
# 空间复杂度O(1)：只需要选择用的游标变量的额外空间，所以空间复杂度为O(1)

class Solution:
    def partitionArray(self, nums, k):
        left, right = 0, len(nums) - 1
        while left <= right:
            while left <= right and nums[left] < k:
                left += 1
            while left <= right and nums[right] >= k:
                right -= 1
            if left <= right:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
                right -= 1
        return left
