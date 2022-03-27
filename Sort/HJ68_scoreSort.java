package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HJ68_scoreSort {
    private static final int ASC = 1, DESC = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int sortType = sc.nextInt();
            sc.nextLine(); // Ìø¹ý±¾ÐÐ
            List<info> infoList = new ArrayList<>(n);


            for (int i = 0; i < n; i++) {
                String[] info = sc.nextLine().split("\\s");
                String name = info[0];
                int score = Integer.valueOf(info[1]);
                infoList.add(new info(name, score));
            }
            if (sortType == ASC) Collections.sort(infoList, ((o1, o2) -> o1.score - o2.score));
            else Collections.sort(infoList, ((o1, o2) -> o2.score - o1.score));

            for (info e: infoList) {
                System.out.println(e);
            }
        }
    }

    static class info {
        String name;
        int score;

        public info(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + " " + score;
        }
    }
}
