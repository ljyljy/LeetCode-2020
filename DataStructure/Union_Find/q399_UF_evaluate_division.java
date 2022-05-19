package DataStructure.Union_Find;

import java.util.*;

// 类比q399,q990
public class q399_UF_evaluate_division {
    List<List<String>> equations;
    double[] values;
    List<List<String>> queries;
    int k = 0;

    // 法3：并查集
    // https://leetcode.cn/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values, List<List<String>> queries) {
        this.equations = equations; this.values = values; this.queries = queries;
        int n = equations.size();
        UnionFind uf = new UnionFind(n*2);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> map = new HashMap<>(2 * n);
        int id = 0;
        for (List<String> eq: equations) {
            String a = eq.get(0), b = eq.get(1);
            if (!map.containsKey(a)) {
                map.put(a, id++);
            }
            if (!map.containsKey(b)) {
                map.put(b, id++);
            }
            uf.union(map.get(a), map.get(b), values[k++]);
        }

        // 第 2 步：做查询
        int m = queries.size();
        double[] res = new double[m];
        k = 0;
        for (List<String> q: queries) {
            String a = q.get(0), b = q.get(1);
            Integer idxA = map.get(a), idxB = map.get(b);
            if (idxA == null || idxB == null) {
                res[k++] = -1;
            } else res[k++] = uf.connected(idxA, idxB);
        }
        return res;
    }

    class UnionFind {
        public int connCnt;
        private int[] parent;
        private double[] weight;

        public UnionFind (int n) {
            connCnt = n;
            parent = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0;
            }
        }

        // 已知 w[x]=x/rX, w[y]=y/rY, x/y=xDivY
        // ∴ 更新w[rX] = rX/parent[rX] = rX/rY
        //             = (x/w[x])/(y/w[y]) = x / y / w[x] * x[y]
        //             = xDivY / w[x] * w[y]
        public void union (int x, int y, double xDivY) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                weight[rootX] = xDivY * weight[y] / weight[x];
                connCnt--;
            }
        }

        private int find(int x) {
            if (x != parent[x]) {
                // 路径压缩：w[x]_old = x/prevX, w[prevX] = prevX/rX
                // w[x]_new = x/rX = w[x]_old * w[prevX]
                int prevX = parent[x]; // ? 暂存前驱！
                parent[x] = find(parent[x]);
                weight[x] *= weight[prevX];
            }
            return parent[x];
        }

        // 已知w[x]=x/rX, w[y]=y/rY,
        //    则 x->y = x/y = w[x]/x[y] (if rX==rY)
        public double connected (int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else return -1;
        }
    }

//    private void Test_printGraph(Map<String, List<Point>> graph) {
//        System.out.println("Print Graph:");
//        for (Map.Entry<String, List<Point>> entry: graph.entrySet()) {
//            System.out.println("divided=" + entry.getKey());
//            List<Point> list = entry.getValue();
//            for (Point p: list) {
//                System.out.println(p.divisor + " -> " + p.divAns);
//            }
//        }
//    }

    public static void main(String[] args) {
        // equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> equations = new ArrayList<>();
        String[] equats = {"a", "b", "b", "c"};
        for (int i = 0; i < equats.length; i+=2) {
            List<String> list1 = new ArrayList<>();
            list1.add(equats[i]);
            list1.add(equats[i+1]);
            equations.add(list1);
        }

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        String[] qs = {"a","c", "b","a", "a","e", "a","a", "x","x"};
        for (int i = 0; i < qs.length; i+=2) {
            List<String> list1 = new ArrayList<>();
            list1.add(qs[i]);
            list1.add(qs[i+1]);
            queries.add(list1);
        }

        q399_UF_evaluate_division sol = new q399_UF_evaluate_division();
        double[] res = sol.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));
    }

}



