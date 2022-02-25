package DataStructure.LinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HJ48_removeNode {
    // 牛客链表题！使用集合容器！（List?）
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int headNum = sc.nextInt();
            List<Integer> list = new LinkedList<>();
            list.add(headNum);

            for (int i = 0; i < n-1; i++) { // 第2~n个结点(共n-1个)
                int node2Add = sc.nextInt();
                int nodePrev = sc.nextInt();
                int prevIdx = list.indexOf(nodePrev); // ? indexOf
                list.add(prevIdx+1, node2Add);
            }
            Object node2del = sc.nextInt();
            list.remove(node2del); // 删除Obj(传obj=内部equals，×：传int=删idx)
            for (int num: list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
