# 	ACW797. 差分
# 输入一个长度为n的整数序列。
# 接下来输入m个操作，每个操作含三个整数l, r, c，表示序列中[l, r]之间的每个数加上c。
# 请你输出进行完所有操作后的序列。
# 输入格式
# 第一行包含两个整数n和m。
# 第二行包含n个整数，表示整数序列。
# 接下来m行，每行包含三个整数l，r，c，表示一个操作。
# 输出格式
# 共一行，包含n个整数，表示最终序列。
# 数据范围
# 1≤n,m≤100000,
# 1≤l≤r≤n,
# −1000≤c≤1000,
# −1000≤整数序列中元素的值≤1000。
# 输入样例：
# 6 3
# 1 2 2 1 2 1
# 1 3 1  -> [2 3 3 1 2 1]
# 3 5 1  -> [2 3 4 2 3 1]
# 1 6 1  -> [3 4 5 3 4 2]
# 输出样例：
# 3 4 5 3 4 2

def insert(diff_arr, l, r, c):
    diff_arr[l] += c
    diff_arr[r + 1] -= c


if __name__ == "__main__":
    n, m = map(int, input().split())  # 6, 3
    arr = [0] * (n + 2)
    diff_arr = [0] * (n + 2)
    nums = list(map(int, input().split()))  # [1, 2, 2, 1, 2, 1]
    for idx, val in enumerate(nums):
        arr[idx + 1] = nums[idx]
    for i in range(1, n + 1):
        insert(diff_arr, i, i, arr[i])
        diff_arr[i] = arr[i] - arr[i - 1]

    while m > 0:
        m -= 1
        l, r, c = map(int, input().split())
        insert(diff_arr, l, r, c)
    for i in range(1, n + 1):
        diff_arr[i] += diff_arr[i - 1]
    for i in range(1, n + 1):
        print(diff_arr[i], end=' ')
