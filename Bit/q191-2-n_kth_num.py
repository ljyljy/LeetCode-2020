# 1.位运算
#
# 求n的第k位数字:   		n >> k &1
# 返回n的最后一位1:      lowbit(n) = n & -n

def get_kth_num(x, k):
    # 求x二进制的第k位数字： x >> k & 1
    res = x >> k & 1
    return res

if __name__ == "__main__":
    x = 11  # 二进制为1011，共4位；
    # (1)右移 tmp = n >> k: 1011 -> 101 -> 10 -> 1
    # (2)看个位是几: tmp & 1
    # 按高位->低位，依次求x的第k位二进制数
    for k in range(3, -1, -1):  # k=[3: -1: 0]
        print(get_kth_num(x, k), end=' ')

