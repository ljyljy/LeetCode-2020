package BinarySearch;/////////////// 方法1 倍增
//https://www.jiuzhang.com/solution/search-in-a-big-sorted-array/#tag-highlight-lang-java

/**
 * 方法1 倍增:
 *
 * 首先特判一下首个元素. 然后设定 idx = 0 为查找的下标, jump = 1 为向后跳跃的长度.
 *
 * 每次循环将 idx 向后移动 jump 个元素, 并将 jump 翻倍. 而如果移动后的位置不小于 target, 则 jump 缩小至一半.
 *
 * 即我们在保证每次跳跃后的 idx 的位置都小于target的前提下, 倍增式地跳跃, 以此保证 O(logn) 的时间复杂度.
 *
 * 循环终止的条件就是 jump == 0, 就是说, 这时 idx + 1 的位置以及不小于 target 了 (此时idx位置的仍然是小于target)
 *
 * 也就是说, 到最后idx指向的元素是: 最大的小于target的元素. 返回答案前判断一下 idx + 1 是否 target 即可.
 *
 * 方法2 二分:
 *
 * 二分查找第一个不小于target的元素很简单. 但是需要确定二分区间的范围. 此时还是需要倍增地找到右边界.
 *
 * 初始右边界为1, 如果右边界的数小于 target, 就将其倍增, 直到右边界不小于target.
 *
 * 这时就可以二分查找了.
 *
 * 注意: 越界访问是没有关系的, 因为这个ArrayReader在越界访问时, 返回 INT_MAX, 一定不小于 target. 而即使是返回 -1 之类的数值, 我们也可以加一个判断搞定.
 */

/**
 * Definition of ArrayReader:
 *
 * public class ArrayReader {
 * public int get(int index) {
 *          // return the number on given index,
 *          // return 2147483647 if the index is invalid.
 *     }
 * };
 */
public class q9_447_Search_in_a_Big_Sorted_Array {
    public class ArrayReader{
        public int get(int idx){return 0;}
        public int read(int idx){return 0;}

    }
    public int searchBigSortedArray(ArrayReader reader, int target) {
        int idx = 1;
        while (reader.read(idx - 1) < target) {
            idx *= 2; // idx <<= 1;
        }
        int start = 0, end = idx - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (reader.get(start) < target) return start;
        if (reader.get(end) < target) return end;
        return -1;
    }
}
