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


if __name__ == "__main__":
    n = int(input())
    arr = list(map(int, input().split()))
    quick_sort(arr, 0, n - 1)
    for num in arr:
        print(num, end=' ')
