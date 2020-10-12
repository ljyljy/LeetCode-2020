# 冒泡排序确定最小值
def bubble_sort_by_min(list):
    for i in range(0, len(list) - 1):
        flag = False
        # i为0, range函数取不到右端点
        for j in range(len(list) - 2, i - 1, -1):
            if list[j] > list[j + 1]:
                list[j], list[j + 1] = list[j + 1], list[j]
                flag = True
                print(list)
        if not flag:
            break


# 冒泡排序确定最大值
def bubble_sort_by_max(list):
    for i in range(len(list) - 1, 0, -1):
        flag = False
        for j in range(0, i):
            if list[j] > list[j + 1]:
                list[j], list[j + 1] = list[j + 1], list[j]
                flag = True
        if not flag:
            break


# 选择排序
def selection_sort(list):
    for i in range(0, len(list) - 1):
        for j in range(i, len(list)):
            if list[i] > list[j]:
                list[i], list[j] = list[j], list[i]


# 插入排序
def insertion_sort(list):
    for i in range(1, len(list)):
        insert_num = list[i]
        # 从后向前比较
        j = i - 1
        while j != -1:
            if insert_num < list[j]:
                list[j + 1] = list[j]
            else:
                break
            j -= 1
        list[j + 1] = insert_num


# %%
# 归并排序 - v0
def merge_sort(arr, l, r):
    if l >= r: return
    mid = l + r >> 1
    merge_sort(arr, l, mid)
    merge_sort(arr, mid + 1, r)
    # merge(arr, start, mid, end)
    i, j, tmp = l, mid + 1, []
    while i <= mid and j <= r:
        if arr[i] <= arr[j]:
            tmp.append(arr[i])
            i += 1
        else:
            tmp.append(arr[j])
            j += 1
    while i <= mid:
        tmp.append(arr[i])
        i += 1
    while j <= r:
        tmp.append(arr[j])
        j += 1
    for k in range(len(tmp)): arr[l + k] = tmp[k]
    return arr


# N = int(input())
# arr = list(map(int, input().split()))
# sorted_arr = merge_sort(arr, 0, len(arr) - 1)
# for num in sorted_arr:
#     print(num, end=' ')


# 归并排序 - v1
def merge_sort(list, l, r):
    # 确定出口
    if l >= r:
        return 0
    # 算中间位置
    res = 0
    mid = l + r >> 1
    res += merge_sort(list, l, mid)
    res += merge_sort(list, mid + 1, r)

    # 合并两边
    temp = []
    i = l
    j = mid + 1
    while i <= mid and j <= r:
        if list[i] < list[j]:
            temp.append(list[i])
            i += 1
        else:
            res += mid - i + 1
            temp.append(list[j])
            j += 1

    # 将剩余的数填入数组
    while i <= mid:
        temp.append(list[i])
        i += 1
    while j <= r:
        temp.append(list[i])
        j += 1

    # 将temp赋值给原数组
    for i in range(0, len(temp)):
        list[i + l] = temp[i]
    return res


# 归并排序 - v2
"""
  # 归并排序（模板见https://www.acwing.com/blog/content/277/） - 写法1
    def reversePairs(self, nums: List[int]) -> int:
        return self.merge(nums, 0, len(nums)-1)

    def merge(self, nums, lt, rt):
        if lt >= rt: return 0
        mid = lt + rt >> 1
        l_cnt = self.merge(nums, lt, mid)
        r_cnt = self.merge(nums, mid+1, rt)
        total_cnt = l_cnt + r_cnt 
	# ↑ 此时，[lt, mid] 和 [mid+1, rt] 已经完成了排序并且计算好逆序对
	# ↓ 加速优化：此时不用计算横跨两个区间的逆序对，直接返回 reverse_pairs
	if nums[mid] <= nums[mid + 1]: return total_cnt   # 不写不影响结果

        i, j, tmp = lt, mid+1, []
        while i <= mid and j <= rt:
            if nums[i] <= nums[j]:
                tmp.append(nums[i])
                i += 1
            else:  # 当前j(后者)严格小于i(前者)
                total_cnt += mid-i+1  # 则j能与前面的[i, mid]个数组成逆序对
                tmp.append(nums[j])
                j += 1
        while i <= mid:
            tmp.append(nums[i])
            i += 1
        while j <= rt:
            tmp.append(nums[j])
            j += 1
        # 将nums[start:end]升序排列
        for ii in range(len(tmp)):
            nums[lt + ii] = tmp[ii]
        return total_cnt

    # 归并排序 - 写法2
    def reversePairs2(self, nums: List[int]) -> int:
        self.total_cnt = 0

        # 不同：传统merge是传入(arr1, arr2)
        def merge(nums, start, mid, end):
            i, j, tmp = start, mid+1, []
            while i <= mid and j <= end:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:  # 当前j(后者)严格小于i(前者)
                    # 则j能与前面的[i, mid]个数组成逆序对
                    self.total_cnt += mid - i + 1
                    tmp.append(nums[j])
                    j += 1
            while i <= mid:
                tmp.append(nums[i])
                i += 1
            while j <= end:
                tmp.append(nums[j])
                j += 1
            for ii in range(len(tmp)):
                nums[start + ii] = tmp[ii]

        def mergeSort(nums, start, end):
            if start >= end: return
            mid = start + end >> 1
            mergeSort(nums, start, mid)
            mergeSort(nums, mid+1, end)
            merge(nums, start, mid, end)

        mergeSort(nums, 0, len(nums)-1)
        return self.total_cnt

"""


def merge_sort(arr, l, r):
    if l >= r: return
    mid = l + r >> 1
    merge_sort(arr, l, mid)
    merge_sort(arr, mid + 1, r)
    # merge(arr, start, mid, end)
    i, j, tmp = l, mid + 1, []
    while i <= mid and j <= r:
        if arr[i] <= arr[j]:
            tmp.append(arr[i])
            i += 1
        else:
            tmp.append(arr[j])
            j += 1
    while i <= mid:
        tmp.append(arr[i])
        i += 1
    while j <= r:
        tmp.append(arr[j])
        j += 1
    for k in range(len(tmp)): arr[l + k] = tmp[k]
    return arr


# %%

# 快速排序
"""
(1)通过一趟排序把将要排序的数据分成两个独立的部分，其中一个部分必然小于另一个部分，
然后再按照这个方法将两个部分快速排序，整个过程可以递归，最后变成最终有序的数据
(2)怎么分成两部分?
找到一个数字为基准 ，比它小的移到它左边，比其大的移到基准数右边
"""


def quick_sort(arr, l, r):
    if l >= r: return
    # 初始化(i, j作为哨兵，位于list之外的两侧)
    i, j = l - 1, r + 1
    # 取中间值, idx=l + r >> 1;
    x = arr[(l + r) // 2]  # 若取l[l]/l[r]，则需注意边界①②!！
    while i < j:  # 而非l<r！
        while True:  # do (j--); while(list[j] > x)
            j -= 1
            if arr[j] <= x: break
        while True:
            i += 1
            if arr[i] >= x: break
        if i < j:  # 若do-while结束后，i仍在j以左（合理）
            arr[j], arr[i] = arr[i], arr[j]
        # else:  # quick_sort(...) 写法①
    quick_sort(arr, l, j)  # ②(list, l, i-1)
    quick_sort(arr, j + 1, r)  # ②(list, i, r)


#
# if __name__ == "__main__":
#     n = int(input())
#     arr = list(map(int, input().split()))
#     quick_sort(arr, 0, n - 1)
#     for num in arr:
#         print(num, end=' ')


# 父节点向下移动
def push_down(heap, size, u):
    # 初始化
    t = u
    left = u * 2
    right = left + 1
    # 判断左孩子是否大于父节点
    if left <= size and heap[left] > heap[t]:
        t = left
    # 判断右孩子是否大于父节点
    if right <= size and heap[right] > heap[t]:
        t = right
    # 交换位置，进行递归
    if t != u:
        heap[t], heap[u] = heap[u], heap[t]
        # t为被换下的父节点
        push_down(heap, size, t)


# 子节点向上移动
def push_up(heap, u):
    while u // 2 and heap[u // 2] < heap[u]:
        heap[u // 2], heap[u] = heap[u], heap[u // 2]
        u //= 2


# 插入元素
def insert(heap, x):
    heap.append(x)
    push_up(heap, len(heap) - 1)


# 移除堆顶元素
def remove_top(heap, size):
    heap[1] = heap[size]
    size -= 1
    push_down(heap, size, 1)


# 堆排序
def heap_sort(heap, size):
    for i in range(1, size):
        push_up(list, i)

    # 拿出最大元素，放在最后
    for i in range(1, size):
        heap[1], heap[size] = heap[size], heap[1]
        size -= 1
        push_down(heap, size, 1)


# 计数排序

# 桶排序

# 基数排序

def get_pos(x, i):
    while True:
        i -= 1
        if i != 0:
            x //= 10
        else:
            break
    return x % 10


def radix_sort(list, n):
    cnt = []
    # 遍历位数
    for i in range(1, 4):
        # 清空
        cnt.clear()
        # 创建桶
        for j in range(0, 10):
            cnt.append([])

        # 按照每一位的大小来分配归属桶
        for j in range(0, n + 1):
            cnt[get_pos(list[j], i)].append(list[j])

        k = 0
        for j in range(0, 10):
            for x in cnt[j]:
                list[k] = x
                k += 1


list = [0, 5, 4, 3, 2, 1, 3]

# bubble_sort_by_max(list)
# bubble_sort_by_min(list)
# selection_sort(list)
# insertion_sort(list)
# print(merge_sort(list, 0, len(list) - 1))
# quick_sort(list, 0, len(list) - 1)
heap_sort(list, len(list) - 1)
# radix_sort(list, len(list) - 1)
print(list)
