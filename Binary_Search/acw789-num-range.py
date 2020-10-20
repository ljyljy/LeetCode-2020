# 	ACW-789. 数的范围
# 给定一个按照升序排列的长度为n的整数数组，以及 q 个查询。
# 对于每个查询，返回一个元素k的起始位置和终止位置（位置从0开始计数）。
# 如果数组中不存在该元素，则返回“-1 -1”。
# 输入格式：第一行包含整数n和q，表示数组长度和询问个数。
# 第二行包含n个整数（均在1~10000范围内），表示完整数组。
# 接下来q行，每行包含一个整数k，表示一个询问元素。
# 输出格式： 共q行，每行包含两个整数，表示所求元素的起始位置和终止位置。
# 如果数组中不存在该元素，则返回“-1 -1”。
# 输入样例：
# 6 3
# 1 2 2 3 3 4
# 3
# 4
# 5
# 输出样例：
# 3 4
# 5 5
# -1 -1

def get_range_bsearch(arr, k, isFindLeft=True):
    start, end = 0, len(arr) - 1
    while start + 1 < end:
        mid = start + end >> 1
        if arr[mid] > k:
            end = mid
        elif arr[mid] < k:
            start = mid
        else:
            if isFindLeft:
                end = mid
            else:
                start = mid
    if isFindLeft:
        if arr[start] == k: return start
        if arr[end] == k: return end
        return -1
    else:
        if arr[end] == k: return end
        if arr[start] == k: return start
        return -1


if __name__ == "__main__":
    n_arr, n_q = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    while n_q:
        q = int(input())
        left = get_range_bsearch(arr, q, True)
        right = get_range_bsearch(arr, q, False)
        print(f'{left} {right}')
        n_q -= 1
