package Sort;

import javafx.scene.layout.Priority;


import java.util.PriorityQueue;
import java.util.Scanner;

public class HJ63_DNA_GC_Ratio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String dna = sc.nextLine();
            int k = sc.nextInt();
            PriorityQueue<String> subStr = getKSubStrs(dna, k);
            System.out.println(subStr.peek().split("_")[0]);
        }
    }

    private static PriorityQueue<String> getKSubStrs(String dna, int k) { // �����Ӵ�������n
        PriorityQueue<String> res = new PriorityQueue<>(((o1, o2) -> {
            int startIdx1 = Integer.valueOf(o1.split("_")[1]); // ?������ʼidx������λ��
            int startIdx2 = Integer.valueOf(o2.split("_")[1]);
            int[] cntGC = new int[2];
            for (int i = 0; i < k; i++) { // �Ӵ�o1/o2����Ϊk
                char ch1 = o1.charAt(i), ch2 = o2.charAt(i);
                if (ch1 == 'G' || ch1 == 'C') cntGC[0]++;
                if (ch2 == 'G' || ch2 == 'C') cntGC[1]++;
            }
            return cntGC[1] != cntGC[0]? cntGC[1] - cntGC[0]: startIdx1 - startIdx2; // GC�������� & ��ʼidx����
        }));

        int len = dna.length();
        for (int i = 0; i + k <= len; i++) {
            res.offer(dna.substring(i, i+k) + "_" + i); // "�Ӵ�_��ʼidx(No.k+1)"
        }
        return res;
    }
}
