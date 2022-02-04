package Other.math;

public class q12_integer_to_roman {
    private int[] val = new int[]{
            1000,900,500,400,
            100,90,50,40,
            10,9,5,4,1
    };
    private String[] str = new String[]{
            "M","CM","D","CD",
            "C","XC","L","XL",
            "X","IX","V","IV","I"};

    public String intToRoman(int num) {
        int idx = 0;
        StringBuilder res = new StringBuilder();
        while (idx < val.length && num > 0) {
            while (num >= val[idx]) { // ?²»ÊÇif£¡Èç: 3="III"
                res.append(str[idx]);
                num -= val[idx];
            }
            idx++;
        }
        return res.toString();
    }
}
