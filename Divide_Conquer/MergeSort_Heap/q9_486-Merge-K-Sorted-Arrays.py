# 	Q9_486. 合并k个排序数组 · Merge K Sorted Arrays
# 将 k 个有序数组合并为一个大的有序数组。
# 挑战：O(n log k) 的时间完成：N 是所有数组包含的整数总数量；k 是数组的个数。
# 例1：
# 输入:
#   [
#     [1, 3, 5, 7],
#     [2, 4, 6],
#     [0, 8, 9, 10, 11]
#   ]
# 输出: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
# 例 2:
# 输入:
#   [
#     [1,2,3],
#     [1,2]
#   ]
# 输出: [1,1,2,2,3]
# 相关题目：839. 合并两个排序的间隔列表104. 合并k个排序链表（→已做）


from typing import List


class Solution:
    # 法0：堆 O(Nlogk)
    def merge_k_sorted_arrays0(self, arrays: List[List[int]]):
        import heapq
        # 初始化 优先堆，包括三个值：数字大小，数字在哪个数组里，数字在数组的哪个位置
        rst, heap = [], []
        for i in range(len(arrays)):
            if not len(arrays[i]): continue
            # arrays[i][0] 数值大小  i 在第i个数组   0 在该数组的0位置
            heapq.heappush(heap, [arrays[i][0], i, 0])
        while heap:
            min_ele = heapq.heappop(heap)  # 形如：[arrays[i][0], i, 0]
            val, arrays_i, idx = min_ele
            rst.append(val)  # 数组本身升序，故头结点肯定是min
            if idx == len(arrays[arrays_i]) - 1:
                continue
            else:  # 压入该数组(当前pop出最小元素的数组)的下一个元素
                new_val = arrays[arrays_i][idx + 1]
                heapq.heappush(heap, [new_val, arrays_i, idx + 1])
        return rst

    # 法0：写法2
    def mergekSortedArrays0_2(self, arrays):
        import heapq
        heap, rst = [], []
        for i in range(len(arrays)):
            if not len(arrays[i]): continue
            heapq.heappush(heap, [arrays[i][0], i, 0])
        while heap:
            cur = heapq.heappop(heap)
            val, arrays_i, idx = cur
            rst.append(val)
            if idx + 1 <= len(arrays[arrays_i]) - 1:
                new_val = arrays[arrays_i][idx + 1]
                heapq.heappush(heap, [new_val, arrays_i, idx + 1])
        return rst

    # 法1：自顶向下的分治法
    def merge_k_sorted_arrays(self, arrays: List[List[int]]):
        return self.merge_range_arrays(arrays, 0, len(arrays) - 1)

    def merge_range_arrays(self, arrays, start, end):
        if start == end: return arrays[start]
        mid = start + (end - start) // 2
        left = self.merge_range_arrays(arrays, start, mid)
        right = self.merge_range_arrays(arrays, mid + 1, end)
        return self.merge_two_arrays(left, right)

    def merge_two_arrays(self, arr1, arr2):
        i, j = 0, 0
        sorted_arr = []
        while i < len(arr1) and j < len(arr2):
            if arr1[i] < arr2[j]:
                sorted_arr.append(arr1[i])
                i += 1
            else:
                sorted_arr.append(arr2[j])
                j += 1
        while i < len(arr1):
            sorted_arr.append(arr1[i])
            i += 1
        while j < len(arr2):
            sorted_arr.append(arr2[j])
            j += 1
        return sorted_arr

    # 法2：自底向上两两归并
    def merge_k_sorted_arrays2(self, arrays: List[List[int]]):
        while len(arrays) > 1:  # 退出时，len为1（全部归并完毕）
            next_arr = []
            for i in range(0, len(arrays), 2):
                if i + 1 <= len(arrays) - 1:
                    # merge完毕后再次和后面一个继续merge，直到合并为1整个arr
                    merged_arr = self.merge_two_arrays(arrays[i], arrays[i + 1])
                else:  # 当前i是最后一个(目前arr被合并为2个: 合并后的arr[0 ~ i-1] & 待合并的arr[i])
                    merged_arr = arrays[i]  # i == len(arrays)-1
                next_arr.append(merged_arr)
            arrays = next_arr
        return arrays[0]


if __name__ == "__main__":
    sol = Solution()
    arrs = list()
    arrs.append([1, 3, 5, 7])
    arrs.append([2, 4, 6])
    arrs.append([0, 8, 9, 10, 11])
    print(arrs)
    # 法0：堆
    sorted_arr = sol.merge_k_sorted_arrays0(arrs)
    print(sorted_arr)
    # 法1：自顶向下的分治法
    sorted_arr = sol.merge_k_sorted_arrays(arrs)
    print(sorted_arr)
    # 法2：自底向上两两归并
    sorted_arr = sol.merge_k_sorted_arrays2(arrs)
    print(sorted_arr)
