package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Transition_Array_Collections {
    // Java中List, Integer[], int[]的相互转换
    public static void main(String[] args) {
        int[] data = {4, 5, 3, 6, 2, 5, 1};

        // int[] 转 List<Integer>

        ArrayList<Integer> list0 = Arrays.stream(data)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        List<Integer> list1 = Arrays.stream(data)
                .boxed()
                .collect(Collectors.toList());
        // Arrays.stream(arr) 可以替换成IntStream.of(arr)。
        // 1.使用Arrays.stream将int[]转换成IntStream。
        // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
        // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。

        // int[] 转 Integer[]
        Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);
        // 前两步同上，此时是Stream<Integer>。
        // 然后使用Stream的toArray，传入IntFunction<A[]> generator。
        // 这样就可以返回Integer数组。
        // 不然默认是Object[]。

        // List<Integer> 转 Integer[]
        Integer[] integers2 = list1.toArray(new Integer[0]);
        //  调用toArray。传入参数T[] a。这种用法是目前推荐的。
        // List<String>转String[]也同理。

        // List<Integer> 转 int[]
        int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
        int[] arr0 = list0.parallelStream().mapToInt(Integer::valueOf).toArray();
        // 想要转换成int[]类型，就得先转成IntStream。
        // 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
        // 而IntStream中默认toArray()转成int[]。


        // Integer[] 转 int[]
        int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
        // 思路同上。先将Integer[]转成Stream<Integer>，再转成IntStream。

        // Integer[] 转 List<Integer>
        // 【ljy?：包装类的数组与集合可以互转(Arrays.asList(包装类Arr)) // 包装类List.toArray()】
        List<Integer> list2 = Arrays.asList(integers1);
        // 最简单的方式。String[]转List<String>也同理。

        // 同理
        String[] strings1 = {"a", "b", "c"};
        // String[] 转 List<String>
        List<String> list3 = Arrays.asList(strings1);
        // List<String> 转 String[]
        String[] strings2 = list3.toArray(new String[0]);


//        对于基本类型的数组如int[], double[], char[] ,Arrays类只提供了默认的升序排列，
//        没有降序，需要传入自定义比较器，使用Arrays.sort(num,c)，传入一个实现了Comparator接口的类的对象c。
        // 必须是Integer[], 基本的int[]没有降序，也不能使用比较器！?
        Arrays.sort(integers1,new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b-a;
            }
        });
    }
}

