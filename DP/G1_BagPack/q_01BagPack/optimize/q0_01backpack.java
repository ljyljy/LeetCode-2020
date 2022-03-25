package DP.G1_BagPack.q_01BagPack.optimize;

import java.util.Scanner;

public class q0_01backpack {
    // 法1【朴素，必理解透彻】：二维dp
    /*
    定义一个二阶矩阵dp[N+1][V+1],
    这里之所以要N+1和V+1，是因为第0行表示只能选择第0个物品的时候，即没有物品的时候
    第0列表示背包的体积为0的时候，即不能装任何东西的时候

    dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值
    对于dp[i][j]有两种情况：
    1. 不选择当前的第i件物品/第i件物品比背包容量要大，则dp[i][j] = dp[i-1][j]
    2. 选择当前的第i件物品（潜在要求第i件物品体积小于等于背包总容量），则能装入的物品最大价值为：
        当前物品的价值 加上 背包剩余容量在只能选前i-1件物品的情况下的最大价值
        dp[i][j] = dp[i-1][j-v[i]] + w[i]
    dp[i][j]在两种情况中选择比较大的情况作为当前的最优解；
    即：
    if(j >= v[i]):
        dp[i][j] = max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])
    else:
        dp[i][j] = dp[i-1][j]
    */
    // v[i]: 物品i的体积; w[i]: 物品i的价值/权重
    private void backPack01_v1 (int N, int V, int[] v, int[] w) {
        // 初始化，先全部赋值为0，这样至少体积为0或者不选任何物品的时候是满足要求
        //  dp[i][j]: 只看前i个物品，总体积是j的情况下，当前最大总价值
        int[][] dp = new int[N+1][V+1]; // N: 物品下标0~N-1; V: 容积0~V

        // 该步骤可省 --初始化: 1) dp[i][0]全0(背包容积为0时，放不下任何物品)  2) dp[0][j]其实可以不用(j-v[0]>=0)
        for (int j = V; j >= v[0]; j--)// 错：for (int j = 0; j <= V; j++)
            dp[0][j] = Math.max(dp[0][j], dp[0][j-v[0]] + w[0]);
        // ↑ ❤ 为避免重复放入物品，j需要逆序遍历！

        for (int i = 1; i <= N; i++) { // 先遍历物品，dp[0][j]需初始化
            for (int j = 0; j <= V; j++) { // 后遍历容积(也可以先遍历容积，不影响)
                dp[i][j] = dp[i-1][j]; // 1) 不选物品i的最大价值
                if (j-v[i] >= 0) { // 选の前提：体积不越界
                    dp[i][j] = Math.max(dp[i-1][j], // ↓无后效性：依赖上方(i-1) & 左方(j-v[i])
                            dp[i-1][j-v[i]] + w[i]); // 2) 选物品i（前i-1物品的最大价值+物品i的价值）
                }
            }
        }
        System.out.println(dp[N][V]);
    }

    // 法2【推荐，优化2】：一维dp
    // 正式算法的代码
    // 将dp优化为一维数组
    /*
    注意，这里第二层循环的时候，还是小到大循环的话，那么

    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])
    实际上变成了
    dp[i][j] = Math.max(dp[i][j], dp[i][j-v[i]] + w[i]);

    因为i-1的值已经在前面被更新过了，覆盖了
    为了避免这个问题，所以要逆序更新，即先更新第i个，然后更新第i-1个，从而保证第i-1个不被覆盖

    如果不逆序的话，输出结果为10，dp数组实际为：
    0 0 0 0 0 0
    0 2 4 6 8 10
    0 2 4 6 8 10
    0 2 4 6 8 10
    0 2 4 6 8 10
    */
    private void backPack01_v2 (int N, int V, int[] v, int[] w) {
        int[] dp = new int[V+1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 1; j--) {
                if (j - v[i] >= 0)
                    dp[j] = Math.max(dp[j], dp[j-v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
    }

    public static void main(String[] args) {
        // 将键盘输入的数字n, v分割并批量转int
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), V = sc.nextInt();
        int[] v = new int[N+1], w = new int[N+1];
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt(); // 体积
            w[i] = sc.nextInt(); // 价值/权重
        }
        sc.close();
        q0_01backpack s1 = new q0_01backpack();
        s1.backPack01_v1(N, V, v, w);
    }
}

