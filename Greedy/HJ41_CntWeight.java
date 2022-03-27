package Greedy;

/*˼·��
    ������������ n;
     ������������w[i];
     �����������c[i]��
     ���  ����ƴ�յ���������
     w1  w2  w3  w4  ......  wn
     c1  c2  c3  c4  ......  cn

    ���ü���ȥ�ص�����
    ���ڼ����������0
    ����һ�����������ʱ��
    {0} ��� {0,0+1}->{0,1}
    ���ڶ����������֮��
    {0��1} ��� {0��1��0+1��1+1}--> {0,1,2}
    ���������������֮��
    {0,1,2} ���{0��1��2��0+2��1+2��2+2} ---> {0,1,2,3,4}
    ȫ������һ��֮������������򼴿ɣ�����������ܵõ������н��
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

        for (int i = 0; i < n; i++) { //��������i
            List<Integer> lastSet = new ArrayList<>(set); // ȡ��ǰ���еĽ��, ��{0,1,2}
            for (int j = 1; j <= cnt[i]; j++) { //��������j��[1, cnt[i]] -�� ����3={����2������1}
                for (int old: lastSet) {
                    set.add(old + weight[i] * j); // {old, old+2} -> {0,1,2, {0,1,2}+2} -> {0,1,2,3,4}
                }
            }
        }
        return set.size();
    }
}