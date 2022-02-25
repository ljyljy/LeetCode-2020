package DataStructure.LinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HJ48_removeNode {
    // ţ�������⣡ʹ�ü�����������List?��
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int headNum = sc.nextInt();
            List<Integer> list = new LinkedList<>();
            list.add(headNum);

            for (int i = 0; i < n-1; i++) { // ��2~n�����(��n-1��)
                int node2Add = sc.nextInt();
                int nodePrev = sc.nextInt();
                int prevIdx = list.indexOf(nodePrev); // ? indexOf
                list.add(prevIdx+1, node2Add);
            }
            Object node2del = sc.nextInt();
            list.remove(node2del); // ɾ��Obj(��obj=�ڲ�equals��������int=ɾidx)
            for (int num: list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
