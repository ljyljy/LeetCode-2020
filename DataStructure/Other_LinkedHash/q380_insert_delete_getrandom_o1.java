package DataStructure.Other_LinkedHash;

import java.util.*;

public class q380_insert_delete_getrandom_o1 {
    class RandomizedSet {
        List<Integer> nums;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, nums.size()); // <val, idx>
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            // ֱ���滻��lastNum�ƶ���������idx(newIdx)��
            Integer lastNum = nums.get(nums.size() - 1);
            Integer newIdx = map.get(val); // newIdx: val��Ӧ��Idx��
            int lastIdx = nums.size()-1;
            nums.set(newIdx, lastNum);
            map.put(lastNum, newIdx);
            // �����Ԫ��ɾ�������Ѿ����ǵ�newIdx����
            nums.remove(lastIdx);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            int rand = random.nextInt(nums.size());
            return nums.get(rand);
        }
    }
}
