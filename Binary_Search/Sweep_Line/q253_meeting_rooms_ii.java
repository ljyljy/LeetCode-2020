package Binary_Search.Sweep_Line;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class q253_meeting_rooms_ii {
    class Pair {
        private int time;
        private int change;
        public Pair(){}
        public Pair(int t, int c) {time = t; change = c;}
    }

    public int minMeetingRooms(int[][] intervals) {
        List<Pair> timeCnt = new ArrayList<>();
        for (int[] intv: intervals) {
            timeCnt.add(new Pair(intv[0], 1));
            timeCnt.add(new Pair(intv[1], -1));
        }
        timeCnt.sort(new Comparator<Pair>(){
            public int compare(Pair o1, Pair o2) {
                if (o1.time == o2.time)
                    return o1.change - o2.change;
                return o1.time - o2.time;
            }
        });

        int tmp = 0, maxCnt = 0;
        for (Pair pair : timeCnt) {
            tmp += pair.change;
            maxCnt = Math.max(maxCnt, tmp);
        }
        return maxCnt;
    }

}
