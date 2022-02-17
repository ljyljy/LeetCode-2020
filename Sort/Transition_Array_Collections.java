package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Transition_Array_Collections {
    // Java��List, Integer[], int[]���໥ת��
    public static void main(String[] args) {
        int[] data = {4, 5, 3, 6, 2, 5, 1};

        // int[] ת List<Integer>
        List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
        // Arrays.stream(arr) �����滻��IntStream.of(arr)��
        // 1.ʹ��Arrays.stream��int[]ת����IntStream��
        // 2.ʹ��IntStream�е�boxed()װ�䡣��IntStreamת����Stream<Integer>��
        // 3.ʹ��Stream��collect()����Stream<T>ת����List<T>���������List<Integer>��

        // int[] ת Integer[]
        Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);
        // ǰ����ͬ�ϣ���ʱ��Stream<Integer>��
        // Ȼ��ʹ��Stream��toArray������IntFunction<A[]> generator��
        // �����Ϳ��Է���Integer���顣
        // ��ȻĬ����Object[]��

        // List<Integer> ת Integer[]
        Integer[] integers2 = list1.toArray(new Integer[0]);
        //  ����toArray���������T[] a�������÷���Ŀǰ�Ƽ��ġ�
        // List<String>תString[]Ҳͬ��

        // List<Integer> ת int[]
        int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
        // ��Ҫת����int[]���ͣ��͵���ת��IntStream��
        // �����ͨ��mapToInt()��Stream<Integer>����Integer::valueOf��ת��IntStream
        // ��IntStream��Ĭ��toArray()ת��int[]��


        // Integer[] ת int[]
        int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
        // ˼·ͬ�ϡ��Ƚ�Integer[]ת��Stream<Integer>����ת��IntStream��

        // Integer[] ת List<Integer>
        // ��ljy?����װ��������뼯�Ͽ��Ի�ת(Arrays.asList(��װ��Arr)) // ��װ��List.toArray()��
        List<Integer> list2 = Arrays.asList(integers1);
        // ��򵥵ķ�ʽ��String[]תList<String>Ҳͬ��

        // ͬ��
        String[] strings1 = {"a", "b", "c"};
        // String[] ת List<String>
        List<String> list3 = Arrays.asList(strings1);
        // List<String> ת String[]
        String[] strings2 = list3.toArray(new String[0]);


//        ���ڻ������͵�������int[], double[], char[] ,Arrays��ֻ�ṩ��Ĭ�ϵ��������У�
//        û�н�����Ҫ�����Զ���Ƚ�����ʹ��Arrays.sort(num,c)������һ��ʵ����Comparator�ӿڵ���Ķ���c��
        // ������Integer[], ������int[]û�н���Ҳ����ʹ�ñȽ�����?
        Arrays.sort(integers1,new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b-a;
            }
        });
    }
}

