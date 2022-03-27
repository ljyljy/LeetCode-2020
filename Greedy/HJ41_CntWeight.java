package Greedy;

/*思路：
    输入砝码种类 n;
     输入砝码质量w[i];
     输入砝码个数c[i]；
     输出  可以拼凑的质量个数
     w1  w2  w3  w4  ......  wn
     c1  c2  c3  c4  ......  cn

    利用集合去重的性质
    先在集合里面添加0
    当第一个砝码进来的时候
    {0} 变成 {0,0+1}->{0,1}
    当第二个砝码进来之后
    {0，1} 变成 {0，1，0+1，1+1}--> {0,1,2}
    当第三个砝码进来之后
    {0,1,2} 变成{0，1，2，0+2，1+2，2+2} ---> {0,1,2,3,4}
    全部遍历一遍之后结束整个程序即可，这个就是所能得到的所有结果
*/


import java.util.*;

public class HJ41_CntWeight {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] weight = new int[n];
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++) {
                weight[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                cnt[i] = sc.nextInt();
            }

            System.out.println(getWeights(n, weight, cnt));
        }
    }

    private static int getWeights(int n, int[] weight, int[] cnt) {
        Set<Integer> set = new HashSet<>();
        set.add(0);

        for (int i = 0; i < n; i++) { //遍历砝码i
            List<Integer> lastSet = new ArrayList<>(set); // 取当前所有的结果, 如{0,1,2}
            for (int j = 1; j <= cnt[i]; j++) { //遍历个数j∈[1, cnt[i]] -》 砝码3={重量2，个数1}
                for (int old: lastSet) {
                    set.add(old + weight[i] * j); // {old, old+2} -> {0,1,2, {0,1,2}+2} -> {0,1,2,3,4}
                }
            }
        }
        return set.size();
    }
}