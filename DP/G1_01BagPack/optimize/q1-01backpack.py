# 	ACW2. 01背包
# 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。第 i 件物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
# 输出最大价值。
# 输入格式
# 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
# 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
# 输出格式
# 输出一个整数，表示最大价值。
# 说明： 数据范围：
# 0<N,V≤1000
# 0<vi,wi≤1000
# 例：
# 4 5 （N, V）
# 1 2 （vi,wi）
# 2 4
# 3 4
# 4 5
# => 8
# 思路：
# f[i][j]表示只看前i个物品，总体积是j的情况下，总价值最大是多少。
# result = max{f[n][0~V]}
# f[i][j]:
# 1.不选第i个物品，f[i][j] = f[i - 1][j];
# 2.选第i个物品，f[i][j] = f[i一1][j - v[i]];
# f[i][j] = max{1. 2.}
# 初始状态：f[0][0] = 0;
# https://www.acwing.com/solution/content/4129/


"""
f[i][j]表示只看前i个物品，总体积是j的情况下，总价值最大是多少。
result = max{f[n][0~V]}
f[i][j]:
1.不选第i个物品，f[i][j] = f[i - 1][j];
2.选第i个物品，f[i][j] = f[i一1][j - v[i]];
f[i][j] = max{1. 2.}
初始状态：f[0][0] = 0;
"""

# 法1：二维dp
# 将键盘输入的数字n, v分割并批量转int
N, V = map(int, input().split())
goods_vw = []
for _ in range(N):  # goods: (n, 2)-> 2列：[ni, wi]
    goods_vw.append([int(i) for i in input().split()])

# 初始化，先全部赋值为0，这样至少体积为0或者不选任何物品的时候是满足要求
dp = [[0] * (V + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, V + 1):
        dp[i][j] = dp[i - 1][j]  # 第i个物品不选
        if j - goods_vw[i - 1][0] >= 0:  # 选の前提：体积不越界（背包容量j > 第i件物品的体积[0]）
            # dp[idx][j]: 只看前idx个物品，总体积是j的情况下，当前最大总价值；↓ 第idx个物品：goods_vw【idx-1】
            dp[i][j] = max(dp[i][j], dp[i - 1][j - goods_vw[i - 1][0]] + goods_vw[i - 1][1])  # 注意goods对应下标要减1！
print(dp[-1][-1])

# 法2：优化[状态压缩DP] - 一维dp
# 可优化原因：
# (1) 对于i：由于状态转移矩阵中，dp[i]每次只用到dp[i-1]，所以可以使用滚动数组递推
# (2) 对于j：但还涉及了dp[i]【j-v[i]】，即对于j部分，递推出的dp[i][j]要么用到自身j，要么用到【j-v[i]】∈[0, j)，
#     由于其一定小于j, 且需要保证v[i]在遍历时是上一层的初始值 & dp[i-1][j-x]不经过二次更新/覆盖，所以需要逆序for（从大到小遍历j）
N, V = map(int, input().split())
goods_vw = []
for _ in range(N):
    goods_vw.append([int(i) for i in input().split()])

dp = [0 for _ in range(V + 1)]
for i in range(1, N + 1):
    for j in reversed(range(1, V + 1)):
        if j - goods_vw[i - 1][0] >= 0:
            # 由于是从大到小遍历，而dp[j-goods_vw[i-1][0]]比dp[j]小，
            # 所以dp[j-xxx]还没被更新覆盖，保存的仍然是上一层dp[i-1]处的[j-xxx]
            # (若是从小到大遍历，则[j-xx]会被更新覆盖，得到的是状态压缩前dp[i]层的[j-xxx]，
            #   而非dp[i-1]层，与原状态转移式不符)
            dp[j] = max(dp[j], dp[j - goods_vw[i - 1][0]] + goods_vw[i - 1][1])
print(dp[-1])
