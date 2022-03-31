package Sort;

import java.util.Scanner;
import java.util.TreeSet;

public class HJ80_array_merge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            TreeSet<Integer> set = new TreeSet<>();
            int n1 = sc.nextInt();
            for (int i = 0; i < n1; i++) {
                set.add(sc.nextInt());
            }
            int n2 = sc.nextInt();
            for (int i = 0; i < n2; i++) {
                set.add(sc.nextInt());
            }
            for (int temp : set) {
                System.out.print(temp);
            }
            System.out.println();
        }
        sc.close();
    }
}