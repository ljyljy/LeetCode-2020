package Array.Sweep_Line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class q9_391_num_of_airplanes_in_the_sky {
    // 法2: 扫描线 需返回二维列表<时刻, cnt>
    // - 1. Java只能自定义类(成员变量time, cnt)
    // - 2. Python可返回list([time, cnt], [...])
    static class Pair {
        private int time;
        private int change;
        public Pair(){}
        public Pair(int _t, int _c) {time = _t; change = _c;}
    }

    static Comparator<Pair> comparator = new Comparator<Pair>() {
        public int compare(Pair o1, Pair o2) {
            if (o1.time == o2.time)
                return o1.change - o2.change;
            return o1.time - o2.time; // 升序排列<time, change>
        }
    };


    public int countOfAirplanes(List<Interval> airplanes) {
        List<Pair> timeCnt = new ArrayList<>();
        for (Interval airplane: airplanes) {
            timeCnt.add(new Pair(airplane.start, 1));
            timeCnt.add(new Pair(airplane.end, -1));
        }
        Collections.sort(timeCnt, comparator);
        int cnt = 0;
        int maxCnt = 0;
        for (Pair pair : timeCnt) {
            cnt += pair.change;
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;
    }
}
