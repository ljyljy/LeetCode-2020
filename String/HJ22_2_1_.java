package String;

import java.lang.reflect.Array;
import java.util.*;

//5
//6
//A B A B A A


//2
//3
//B A B
public class HJ22_2_1_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int chipCnt = sc.nextInt();
            int jobCnt = sc.nextInt();
            sc.nextLine();
            String[] jobs = sc.nextLine().split("\\s");
            calc(chipCnt, jobCnt, jobs);
        }
        sc.close();
    }

    private static void calc(int chipCnt, int jobCnt, String[] jobs) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        double[] chipRemain = new double[chipCnt+1]; // <chipIdx, remain>, 资源Idx=remain/2.5-1

        Arrays.fill(chipRemain, 10.0);
        for (String job: jobs) {
            char ch = job.charAt(0);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int[] lastChipIdx = new int[]{-1, -1}; // lastA/BIdx,从1起
        for (int i = 1; i <= jobCnt; i++) {
            if (!isValid(lastChipIdx, chipCnt)) {
                System.out.println("0\n0");
                return;
            }
            String job = jobs[i-1];

            if (job.equals("A")) {
                if (lastChipIdx[0] == -1) {
                    lastChipIdx[0] = findFirstIdx(chipRemain, jobCnt);
                    if (lastChipIdx[0] == -1) {
                        System.out.println("0\n0");
                        return;
                    }
                }

                if (chipRemain[lastChipIdx[0]] > 2.5) {
                    chipRemain[lastChipIdx[0]] -= 2.5;
                    if (i == jobCnt) {
                        int srcIdx = (int)(4 - chipRemain[lastChipIdx[0]] / 2.5);
                        System.out.println(lastChipIdx[0] + "\n" + srcIdx);
                    }
                } else if (chipRemain[lastChipIdx[0]] == 2.5){
                    chipRemain[lastChipIdx[0]] -= 2.5;
                    if (i == jobCnt) {
                        int srcIdx = (int)(4 - chipRemain[lastChipIdx[0]] / 2.5);
                        System.out.println(lastChipIdx[0] + "\n" + srcIdx);
                    }

                    lastChipIdx[0] = Math.max(lastChipIdx[0], lastChipIdx[1]) + 1;
                }
            } else {
                if (lastChipIdx[1] == -1) {
                    lastChipIdx[1] = findFirstIdx(chipRemain, jobCnt);
                    if (lastChipIdx[1] == -1) {
                        System.out.println("0\n0");
                        return;
                    }
                }

                if (chipRemain[lastChipIdx[1]] == 10) {
                    chipRemain[lastChipIdx[1]] -= 10;
                    if (i == jobCnt) {
                        if (!isValid(lastChipIdx, chipCnt)) {
                            System.out.println("0\n0");
                            return;
                        }
                        System.out.println(lastChipIdx[1] + "\n" + "1");
                    }
                    lastChipIdx[1] = Math.max(lastChipIdx[0], lastChipIdx[1]) + 1;
                } else {
                    lastChipIdx[1] = Math.max(lastChipIdx[0], lastChipIdx[1]) + 1;
                    if (i == jobCnt) {
                        if (!isValid(lastChipIdx, chipCnt)) {
                            System.out.println("0\n0");
                            return;
                        }
                        System.out.println(lastChipIdx[1] + "\n" + "1");
                    }
                }


            }

//            System.out.println("current job:" + job + ", idx = " + i + ", nextChipIdx[A,B]=" + lastChipIdx[0] + "," + lastChipIdx[1] );
//            for (double remain: chipRemain) {
//                System.out.print(remain + ", ");
//            }
//            System.out.println();
        }

    }

    private static int findFirstIdx(double[] chipRemain, int jobCnt) {
        for (int i = 1; i <= jobCnt; i++) {
            if (chipRemain[i] == 10.0) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isValid(int[] lastChipIdx, int chipCnt) {
        int maxChipIdx = Math.max(lastChipIdx[0], lastChipIdx[1]);
        if (maxChipIdx > chipCnt) return false;
        return true;
    }
}
