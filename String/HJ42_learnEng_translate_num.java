package String;

import java.util.Scanner;

// 如：1,652,510:  one million six hundred and fifty two thousand five hundred and ten
//     123,456,789: X billion X million X thousand X
// 每3位单独处理，先处理后三位，中间三位处理完+thousand，前三位处理完+million，最后拼一块，处理特殊情况
public class HJ42_learnEng_translate_num {
    static String[] NUMS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}; // [0-9]
    static String[] NUMS_teen = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};// [10-19]

    static String[] NUMS_ty = {"zero", "ten", "twenty", "thirty", "forty", "fifty", // [10,20,...90]
            "sixty", "seventy", "eighty", "ninety"};

    static String[] POWER = {"", "hundred", "thousand", "million", "billion"}; //

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                long num = sc.nextLong();
                if (num < 0) {
                    System.out.println("error");
                    continue;
                } else if (num == 0) {
                    System.out.println("zero");
                } else { // 其他情况
                    String numStr = String.valueOf(num);
                    int len = numStr.length();
                    if (len > 9) {
                        System.out.println("error");
                        continue;
                    }
                    StringBuilder res = new StringBuilder();
                    int part_3 = (int)(num % 1000), part_2 = (int)((num / 1000) % 1000), part_1 = (int)((num / 1000 / 1000) % 1000);
                    String part_3_eng = getPartEng(part_3);
                    res.append(part_3_eng);
                    if (part_2 > 0) {
                        String part_2_eng = getPartEng(part_2);
                        res.insert(0, part_2_eng + " thousand ");
                        if (part_1 > 0) {
                            String part_1_eng = getPartEng(part_1);
                            res.insert(0, part_1_eng + " million ");
                        }
                    }
                    System.out.println(res.toString());
                }
            } catch (Exception e) {
                System.out.println("error");
                continue;
            }
        }
    }

    private static String getPartEng(int num) { // 3位数的num
        if (num == 0) return "";
        StringBuilder res = new StringBuilder();
        int bit_3 = (int)num % 10, bit_23 = (int)(num % 100), bit_1 = (int)(num / 100), bit_2 = (int)((num / 10) % 10);
        if (bit_1 != 0) { // 123
            res.append(NUMS[bit_1] + " hundred ");
        }
        if (bit_23 != 0) { // 1[xy]
            if (bit_1 != 0) res.append("and ");
            if (bit_2 == 0) { // 1[03]
                res.append(NUMS[bit_3]);
            } else if (bit_2 == 1){  // 1[13]
                res.append(NUMS_teen[bit_23 - 10]);
            } else {
                if (bit_3 != 0) // 1[23]
                    res.append(NUMS_ty[bit_2] + " " + NUMS[bit_3]);
                else  // 1[20]
                    res.append(NUMS_ty[bit_2]);
            }
        }
        return res.toString().trim();
    }



    /**
     * 987(million)654(thousand)321
     * 对于每部分的三位数：3 hundred and 21
     *
     * eg：num=987654321 写个循环
     * 第一步:num%1000得到末尾3位数321，得到str
     * 第二步，num=num/1000，即987654，继续取余得到654，加单位和上一步得到的str
     * 重复第二步直到num为0。
     *
     * 对于每部分的三个数 函数封装
     * 三位数：整百，百位 hundred and 后两位(调用下面的函数)；
     * 两位数：1-9; 10-19; 10的倍数; 其他情况(十位ty+个位)
     *
     *
     */
}
