package Sort.Quick_Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class q179_largest_number {
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
        Integer[] ns = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(ns, (a, b) -> ("" + b + a).compareTo("" + a + b));
        return Arrays.stream(ns).mapToInt(x -> x).sum() == 0 ?
                "0" : Arrays.stream(ns).map(Object::toString)
                        .collect(Collectors.joining(""));
    }

    // 法2：快排
    public String largestNumber_v2(int[] nums) {
        if (nums.length == 0) return "";
        int n = nums.length;
        String[] nums_str = new String[n];
        for (int i = 0; i < n; i++)
            nums_str[i] = "" + nums[i];
        quickSort(nums_str, 0, n-1);
        if (nums_str[0].equals("0")) return "0";//去除前导’0’（全0数组）
        StringBuilder sb = new StringBuilder();
        for (String num_s : nums_str)
            sb.append(num_s);
        return sb.toString();
    }

    private void quickSort(String[] nums, int L, int R) {
        if (L >= R) return;
        String pivot = nums[L + R >>1];
        int i = L - 1, j = R + 1;
        while (i < j) {
            do i++; while ((nums[i] + pivot).compareTo(pivot + nums[i]) > 0);
            do j--; while ((pivot + nums[j]).compareTo(nums[j] + pivot) > 0);
            if (i < j) swap(nums, i, j);
        }
        quickSort(nums, L, j);
        quickSort(nums, j+1, R);
    }

    private void swap(String[] nums, int i, int j) {
        String tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        String s1 = "ab c", s2="ab d";
        System.out.println(s1.compareTo(s2)); // -1

    }
}
