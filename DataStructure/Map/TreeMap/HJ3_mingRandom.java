package DataStructure.Map.TreeMap;

import java.util.*;

public class HJ3_mingRandom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            Set<Integer> set = new TreeSet<>(); // ����
            int n = sc.nextInt();
            while (n > 0) {
                set.add(sc.nextInt());
                n--;
            }

            for (int num: set) { // ����������ݶ������ԣ�����while���WA����
                System.out.println(num);
            }
        }
    }
}

