# 	ACW_795. 前缀和
# 输入一个长度为n的整数序列。接下来再输入m个询问，每个询问输入一对l, r。
# 对于每个询问，输出原序列中从第l个数到第r个数的和。
# 输入格式
# 第一行包含两个整数n和m。
# 第二行包含n个整数，表示整数数列。
# 接下来m行，每行包含两个整数l和r，表示一个询问的区间范围。
# 输出格式
# 共m行，每行输出一个询问的结果。
# 数据范围
# 1≤l≤r≤n,
# 1≤n,m≤100000,
# −1000≤数列中元素的值≤10000
# 输入样例：
# 5 3
# 2 1 3 6 4
# 1 2
# 1 3
# 2 4
# 输出样例：
# 3
# 6
# 10

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
