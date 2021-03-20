package DataStructure.LinkedList;

import java.util.Scanner;

public class ACW826_LinkedList_array {
    private static int N = 100010;
    private static int[] e = new int[N];  // 模拟node.val
    private static int[] ne = new int[N]; // 模拟node.idx(下一个结点的下标地址)
    private static int head;
    private static int idx;  // 下一个结点的下标地址

    //初始化第1个插入节点下标为0，所以操作第k个数时都要-1 ！！
    public static void init() {
        head = -1;  // head
        idx = 0;
    }

    // 插到头结点后
    public static void insert_H(int x) {
        e[idx] = x;
        ne[idx] = head; //
        head = idx++;
    }

    // 插到第k个结点后
    public static void insert_K(int k, int x) {
        e[idx] = x;
        ne[idx] = ne[k];
        ne[k] = idx++;
    }

    //删除第k个数后面的数
    public static void delete(int k){
        ne[k] = ne[ne[k]];
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        while (m-- > 0) {
            String op = sc.next();
            if ("H".equalsIgnoreCase(op)) {
                int x = sc.nextInt();
                insert_H(x);
            } else if ("D".equalsIgnoreCase(op)) {
                int k = sc.nextInt();
                if(k == 0){
                    //当k为0时，删除头结点，不是清空链表
                    head = ne[head];
                }else{
                    delete(k - 1);
                }
            } else if("I".equals(op)){
                //k和x接收位置不要写反！！
                int k =sc.nextInt();
                int x =sc.nextInt();
                insert_K(k - 1, x);
            }
        }

        for (int i = head; i != -1; i = ne[i]){
            System.out.print(e[i] + " ");
        }
    }
}
