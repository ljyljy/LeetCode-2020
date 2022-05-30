package Greedy;

public class q134_gas_station {
    //    写法2：【荐，参考LC_思路2】
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; ) {  // 遍历起始点
            int curLeft = 0, curCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n; // 必须赋临时值j，否则会覆盖i旧值???
                curLeft += gas[j];
                curCost += cost[j];
                if (curLeft < curCost) {
                    break;
                }
                cnt++;
                // i = (i+cnt) % n; // 枚举：本轮能否走完整一圈（循环，取余）
            }
            if (cnt == n) return i;
            i = i + cnt + 1; // 思路见优化2
// 依次遍历起始点(从无解的i+cnt后一位开始retry)，不可取余
            // ↑ (i+cnt)处导致无解，说明该处cost很大，从下一位开始，尽量让(i+cnt)摆在后位(尽量积攒更多的剩余油量curLeft)
        }
        return -1;
    }

    // 写法2
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int remain = gas[i], j = i;
            while (remain >= cost[j]) {
                int newJ = (j + 1) % n;
                remain = remain - cost[j] + gas[newJ];
                j = newJ;

                if (j == i) return i;
            }
            if (j < i) return -1;
            i = j;
        }
        return -1;
    }
}
