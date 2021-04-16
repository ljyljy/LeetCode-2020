package DataStructure.Heap;


import java.util.Comparator;
import java.util.PriorityQueue;

public class q973_k_closest_points_ii {
    private class Point {
        public int x, y;
        public Point() {x = 0; y = 0;}
        public Point(int x, int y) {this.x = x; this.y = y;}
    }

    public Point[] kClosest (Point[] points, Point ori, int k) {
        if (points == null || points.length == 0) return null;
        int n = points.length;
        /* // 大顶堆（降序排列，后减前） - Java默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<Point> heap = new PriorityQueue<>(n, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                long dist = Long.compare(getDist(p2, ori), getDist(p1, ori)); // getDist(p2, origin) - getDist(p1, origin);
                if (dist == 0)  dist = p2.x - p1.x;
                if (dist == 0)  dist = p2.y - p1.y;
                return (int) dist;
            }
        });
        */  // 写法2↓ lambda表达式(最大堆 - poll()：dist/x/y大的先被踢)
        PriorityQueue<Point> heap = new PriorityQueue<>(n, (p1, p2) -> {
            long dist = Long.compare(getDist(p2, ori), getDist(p1, ori));
            if (dist == 0)  dist = p2.x - p1.x;
            if (dist == 0)  dist = p2.y - p1.y;
            return (int) dist; // 大顶堆（降序排列，p2减p1）
        });

        for (Point p : points) {
            heap.offer(p); // 堆内: 大(顶)->小(底)
            if (heap.size() > k)
                heap.poll(); // 踢出大数
        }

        // 要求从小到大保存/输出（∴堆内剩余元素的逆序）
        Point[] res = new Point[k];
        while (!heap.isEmpty()) {
            res[--k] = heap.poll(); // 从后往前保存！
        } // ↑ k-1 --> 递减至 0
        return res;
    }

    private long getDist(Point p, Point ori) {
        // 可能会超出int范围，dist应设为long
        return (p.x - ori.x) * (p.x - ori.x) + (p.y - ori.y) * (p.y - ori.y);
    }

    public static void main(String[] args) {
        int num = 6;
        System.out.println(num ^ 2);
        System.out.println(num * num);
        System.out.println(Math.pow(num, 2));
    }
}
