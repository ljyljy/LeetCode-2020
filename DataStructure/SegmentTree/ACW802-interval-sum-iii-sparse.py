def find(x):
    """二分查找模板，从索引数组alls中找到大于等于x的最小的索引"""
    l = 0
    r = len(alls)-1
    while l<r:
        mid = l+r>>1
        if alls[mid]>=x: r = mid    # ！！！if条件忘记了=号
        else: l = mid+1
    return l+1    # 因为要计算前缀和，所以加1保证索引从1开始

if __name__=="__main__":
    n, m = map(int, input().split())
    N = 300010
    arr_dispersed = [0] * N    # 用于存储离散化后的索引和对应值，其中索引对应离散化后的索引，值对应离散化前索引的取值
    prefix_sum = [0] * N    # 存a数组的前缀和数组

    add = []    # 存储插入操作的二元组
    query = []    # 存储查询操作的二元组

    alls = []    # 存储离散化前输入的所有索引，n+2*m

    for i in range(n):
        idx1, val1 = map(int, input().split())
        add.append((idx1, val1))
        alls.append(idx1)

    for i in range(m):
        l, r = map(int, input().split())
        query.append((l, r))
        alls.append(l)
        alls.append(r)

    alls = list(sorted(set(alls)))    # 将alls数组排序并去重


    # 1. 处理插入
    for idx1, val1 in add:
        idx_ = find(idx1)
        arr_dispersed[idx_]+=val1

    # 2. 处理前缀和
    for i in range(1, len(alls)+1):
        prefix_sum[i] = prefix_sum[i - 1] + arr_dispersed[i]

    # 3. 处理查询
    for l, r in query:
        l2 = find(l)
        r2 = find(r)
        res = prefix_sum[r2] - prefix_sum[l2 - 1]
        print(res)
