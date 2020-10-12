# V1 – 传统归并排序（涉及边界问题）
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


# V2 – 改进版（无需考虑边界问题，但需使用切片）
def merge_sort2(arr):
    if len(arr) > 1:
        mid = 0 + len(arr) >> 1
        L, R = arr[:mid], arr[mid:]
        merge_sort2(L)
        merge_sort2(R)
        # merge
        i, j, k = 0, 0, 0
        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                arr[k] = L[i]  # 原地修改（从0开始--∵切片）
                k += 1
                i += 1
            else:
                arr[k] = R[j]
                k += 1
                j += 1
        while i < len(L):
            arr[k] = L[i]  # 原地修改（从0开始--∵切片）
            k += 1
            i += 1
        while j < len(R):
            arr[k] = R[j]
            k += 1
            j += 1
    return arr


if __name__ == "__main__":
    N = int(input())
    arr = list(map(int, input().split()))
    # v1
    sorted_arr = merge_sort(arr, 0, len(arr) - 1)
    for num in sorted_arr:
        print(num, end=' ')
    # v2
    sorted_arr2 = merge_sort2(arr)
    print(' '.join(list(map(str, sorted_arr2))))
