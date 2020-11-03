# 	ACW786. 第K个数
# 给定一个长度为n的整数数列，以及一个整数k，请用快速选择算法求出数列从小到大排序后的第k个数。
# 输入格式
# 第一行包含两个整数 n 和 k。
# 第二行包含 n 个整数（所有整数均在1~109109范围内），表示整数数列。
# 输出格式
# 输出一个整数，表示数列的第k小数。
# 输入样例：
# 5 3
# 2 4 1 5 3
# 输出样例：
# 3

def top_k_smallest_num(arr, k):
    import heapq
    if not arr or k <= 0: return []
    max_heap = []
    for num in arr:
        heapq.heappush(max_heap, -num)
        if len(max_heap) > k:
            heapq.heappop(max_heap)
    # max_heap = list(map(lambda x: -x, max_heap))
    # max_heap.sort(reverse=False)
    return -max_heap[0]  # -heapq.heappop(max_heap)  # max_heap[0]: 第k小的数


def quick_select(arr, l, r, k):
    if l == r: return arr[l]
    x, i, j = arr[l + r >> 1], l - 1, r + 1
    while i < j:
        while True:
            i += 1
            if arr[i] >= x: break
        while True:
            j -= 1
            if arr[j] <= x: break
        if i < j:  arr[i], arr[j] = arr[j], arr[i]
    sl = j - l + 1  # 左半段长度/数的个数
    if k <= sl:
        return quick_select(arr, l, j, k)
    return quick_select(arr, j + 1, r, k - sl)


def quickSort(arr, l, r):
    if l >= r: return
    i, j, pivot = l, r, arr[l + r >> 1]
    while i <= j:
        while i <= j and arr[i] < pivot: i += 1
        while i <= j and arr[j] > pivot: j -= 1
        if i <= j:
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
            j -= 1
    quickSort(arr, l, j)
    quickSort(arr, i, r)  # ❤注意是[iii, r]!!!!


if __name__ == "__main__":
    n, k = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    ans = top_k_smallest_num(arr, k)
    ans2 = quick_select(arr, 0, len(arr) - 1, k)
    print(ans)
    print(ans2)

    # 法3：
    quickSort(arr, 0, len(arr) - 1)
    print(arr)
    print(arr[k - 1])
