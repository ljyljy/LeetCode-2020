
# """二分查找模板，从索引数组arr中找到【大于等于x】的最小的索引"""
# [l, r] --> [l, mid], [mid+1, r]
# idx_arr0: 离散化前输入的所有索引（插入、查询の原始索引）
# 如:[2,5,100,2000,500000] -> 查找100，返回2+1
def find(x, idx2disparse):
    start, end = 0, len(idx2disparse) - 1
    while start < end:
        mid = start + end >> 1
        if idx2disparse[mid] >= x:
            end = mid
        else:
            start = mid + 1
    # 因为要计算【前缀和数组的下标】，所以加1保证索引从1开始
    return start + 1  # start == end;


if __name__ == "__main__":
    n, m = map(int, input().split())
    N = 300010
    arr_dispersed = [0] * N  # 用于存储离散化后的索引和对应值，其中索引对应离散化后的索引，值对应离散化前索引的取值
    prefix_sum = [0] * N  # 存a数组的前缀和数组

    add, query = [], []  # 存储插入*n、查询*2m操作   ❤二元组：【tuple(idx, val)】
    idx2disparse = []  # 存储离散化前输入的所有索引，n+2*m

    for i in range(n):
        idx1, val1 = map(int, input().split())
        add.append((idx1, val1))
        idx2disparse.append(idx1)

    for i in range(m):
        l, r = map(int, input().split())
        query.append((l, r))
        idx2disparse.extend([l, r])
        # idx2disparse.append(l)
        # idx2disparse.append(r)

    # ❤ 将idx2disparse数组排序并去重
    idx2disparse = list(sorted(set(idx2disparse)))

    # 1. 处理插入
    for idx1, val1 in add:
        idx_ = find(idx1, idx2disparse)
        arr_dispersed[idx_] += val1

    # 2. 处理前缀和
    for i in range(1, len(idx2disparse) + 1):
        prefix_sum[i] = prefix_sum[i - 1] + arr_dispersed[i]  # 离散化后数组下标从1开始

    # 3. 处理查询
    for l, r in query:
        l_, r_ = find(l, idx2disparse), find(r, idx2disparse)
        res = prefix_sum[r_] - prefix_sum[l_ - 1]
        print(res)

