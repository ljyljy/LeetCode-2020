# why 1e-8?(↓经验：误差缩小2位)
# -- 这样的话可以把误差缩小到1e-8，题目要求只输出6位小数
def float_bsearch(num, l, r):
    def q(num):
        return num * num * num

    while l + 1e-8 <= r:
        mid = (l + r) / 2  # 浮点数
        if q(mid) > num:
            r = mid
        else:
            l = mid
    return l


if __name__ == "__main__":
    # ∵−10000≤n≤10000 ∴3次方根∈[-22, 22]
    n = float(input())
    l, r = -22, 22
    ans = float_bsearch(n, l, r)
    print(f'{ans:.6f}')
