package Two_Pointers;

import java.util.*;

public class ACW800_two_arrays_target_sum {
    private static int N = 100010;
    private static int[] a = new int[N];
    private static int[] b = new int[N];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int x = scanner.nextInt();
        for(int i = 0;i < n;i++){
            a[i] = scanner.nextInt();
        }
        for(int i = 0;i < m;i++){
            b[i] = scanner.nextInt();
        }
        for(int i = 0,j = m - 1; i < n; i++){
            while(j > 0 && a[i] + b[j] > x) j--;

            if(a[i] + b[j] == x){
                System.out.println(i+" "+j);
                break;
            }
        }

    }

}


