arr = [1, 5, 5, 200, 5000, 200, 99999]
arr_ = list(set(sorted(arr)))  # 排序 & set去重 & 转回list

# 二分求出对应x的离散化下标
def find(arr_, x):
    start, end = 0, len(arr_) - 1
    while start + 1 < end:
        mid = start + end >> 1
        if arr_[mid] == x:
            return mid
        elif arr_[mid] > x:
            end = mid
        else:
            start = mid
    if arr_[start] == x: return start
    if arr_[end] == x: return end
    return -1

# 找到第一个 >= x的下标的位置
# 【ACW二分模板1】 [l, r] -> [l, mid], [mid+1, r]
def find2(arr_, x):
    L, R = 0, len(arr_) - 1
    while L < R:
        mid = L + R >> 1
        if arr_[mid] >= x: R = mid
        else: L = mid + 1
    return R  # start == end

print(arr_)
print(find(arr_, 5000))  # 3
print(find2(arr_, 5000))  # 3

print(find(arr_, 500))  # -1
print(find2(arr_, 500))  # 3


