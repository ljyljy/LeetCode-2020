package Sort.Quick_Sort;

import java.util.*;
import java.util.stream.Collectors;

public class q179_largest_number {
    // 类比q179, qo_45
    // 法1：新1
    public String largestNumber(int[] nums) {
        int n = nums.length;
        List<String> numStr = new ArrayList<>();
        for (int num: nums) {
            numStr.add("" + num);
        }

        Collections.sort(numStr, (o1, o2)->((o2+o1).compareTo(o1+o2)));
        StringBuilder sb = new StringBuilder(String.join("", numStr));
        if (sb.charAt(0) == '0') return "0";
//        或： while (sb.length() > 1 && sb.charAt(0) == sb.charAt(1) && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }

    // 新2
    public String largestNumber2(int[] nums) {
        int n = nums.length;
        String[] numStr = new String[n];
        for (int i = 0; i < n; i++) {
            numStr[i] = "" + nums[i];
        }
        Arrays.sort(numStr, (s1, s2)->(s2+s1).compareTo(s1+s2)); // 降序
        StringBuilder sb = new StringBuilder(String.join("", numStr));
        // // 去除前导‘0’ - 法1
        // while (sb.charAt(0) == '0') {
        //     sb.deleteCharAt(0);
        //     if (sb.length() == 1) break; // 保证[0,0,0,0]->至少保留一个"0"
        // }
        // // 去除前导‘0’ - 法2
        if (numStr[0].equals("0")) return "0";//去除前导’0’（全0数组）
        return sb.toString();
    }

    // 法1：字符串 比较器Comparator
    public String largestNumber_v1(int[] nums) {
        if (nums.length == 0) return "";
        int n = nums.length;
        String[] nums_str = new String[n];
        for (int i = 0; i < n; i++)
            nums_str[i] = "" + nums[i]; // String.valueOf(int)
        sortStrArr(nums_str);

        StringBuilder sb = new StringBuilder();
        for (String num_s : nums_str)
            sb.append(num_s);
        // 去除前导'0'，如[0, 0, 0]  最大数为“0”,而非“000”
        for (int i = 0; sb.charAt(i) == '0' && i < sb.length()-1;)
            sb.deleteCharAt(i); // 写法2：i++
        return sb.toString(); // 写法2：sb.substring(i); （substring更慢）
    }

    private void sortStrArr(String[] nums_str) {

        // 写法1：lambda 表达式 -- 最大数(大顶堆 - 降序排列，后减前)
        Arrays.sort(nums_str, (s1, s2)-> ((s2+s1).compareTo(s1+s2)));
        // 写法1-2
//        Arrays.sort(nums_str, (s1, s2) -> {
//            String s12 = s1 + s2, s21 = s2 + s1;
//            return s21.compareTo(s12); // (""+s2+s1).compareTo(...)
//        }); //      ↑ 降序排列，后减前
        /*
        // 写法2：Comparator 字符串比较器
        Arrays.sort(nums_str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String s12 = s1 + s2, s21 = s2 + s1;
                return s21.compareTo(s12);
            }
        });
         */
    }

    // 法1-写法2： 精简
    public String largestNumber_short(int[] nums) {
        // int[]无法排序，但Integer[]可以！
        Integer[] ns = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(ns, (a, b) -> ("" + b + a).compareTo("" + a + b));
        return Arrays.stream(ns).mapToInt(x -> x).sum() == 0 ? "0" :
                Arrays.stream(ns).map(Object::toString)
                        .collect(Collectors.joining(""));
    }


    // 法2：快排-v1
    public String largestNumber1(int[] nums) {
        int n = nums.length;
        String[] numStr = new String[n];
        for (int i = 0; i < n; i++) {
            numStr[i] = "" + nums[i];
        }
        quickSort(numStr, 0, n-1);
        if ("0".equals(numStr[0])) return "0";//去除前导’0’（全0数组）
        return String.join("", numStr);
    }

    private void quickSort(String[] numStr, int start, int end) {
        if (start >= end) return;
        int mid = parition(numStr, start, end);
        quickSort(numStr, start, mid-1);
        quickSort(numStr, mid+1, end);
    }

    private int parition(String[] str, int start, int end) {
        if (start >= end) return start;
        String pivot = str[start]; // "4",567 123
        int i = start + 1, j = end;
        while (i <= j) { // 需要降序！❤
            while (i <= j && (str[i] + pivot).compareTo(pivot + str[i]) > 0) i++; //(567+"4" > "4"+567)
            while (i <= j && (str[j] + pivot).compareTo(pivot + str[j]) < 0) j--; //("4"+123 > 123+"4")
            if (i <= j) {
                swap(str, i, j);
                i++; j--;
            }
        }
        swap(str, j, start);
        return j;
    }

    private void swap(String[] str, int i, int j) {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

//    // 法2：快排-v2
//    public String largestNumber_v2(int[] nums) {
//        if (nums.length == 0) return "";
//        int n = nums.length;
//        String[] nums_str = new String[n];
//        for (int i = 0; i < n; i++)
//            nums_str[i] = "" + nums[i];
//        quickSort(nums_str, 0, n-1);
//        if (nums_str[0].equals("0")) return "0";//去除前导’0’（全0数组）
//        StringBuilder sb = new StringBuilder();
//        for (String num_s : nums_str)
//            sb.append(num_s);
//        return sb.toString();
//    }
//
//    private void quickSort(String[] nums, int L, int R) {
//        if (L >= R) return;
//        String pivot = nums[L + R >>1];
//        int i = L - 1, j = R + 1;
//        while (i < j) {
//            do i++; while ((nums[i] + pivot).compareTo(pivot + nums[i]) > 0);
//            do j--; while ((pivot + nums[j]).compareTo(nums[j] + pivot) > 0);
//            if (i < j) swap(nums, i, j);
//        }
//        quickSort(nums, L, j);
//        quickSort(nums, j+1, R);
//    }
//
//    private void swap(String[] nums, int i, int j) {
//        String tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }

    public static void main(String[] args) {
        String s1 = "ab c", s2="ab d";
        System.out.println(s1.compareTo(s2)); // -1

    }
}
