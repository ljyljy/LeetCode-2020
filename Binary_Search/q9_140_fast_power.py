# 计算a^n % b，其中a，b和n都是32位的非负整数。
# base^pow % modd
def qmi(base, pow, modd):
    res = 1 % modd
    if pow == 0: return res  # a^0 == 1
    while pow:
        if pow & 1:  # 按位与 <=> 若为奇数
            res = res * base % modd
        base = base * base % modd
        pow = pow >> 1
    return res
