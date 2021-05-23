package DataStructure.Trie;

import java.util.Arrays;
import java.util.Comparator;

public class q1707_max_xor_with_an_element_from_array {
    class TrieNode {
        static final int L = 30; // 10e9=(10^3)^3的长度
        TrieNode[] child = new TrieNode[2]; // ∵二进制表示 ∴只有0/1
    }

    class Trie {
        TrieNode root = new TrieNode();
        int L = TrieNode.L;
        private void insert(int num) {
            TrieNode p = root;// 从根遍历
            // 逆序: 从高位依次插入Trie
            for (int i = L-1; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (p.child[bit] == null)
                    p.child[bit] = new TrieNode();
                p = p.child[bit];
            }
        }

        // 在Trie树中，获取与num异或的最大值
        private int getMaxXor(int num) {
            int ans = 0;
            TrieNode p = root;
            for (int i = L-1; i >= 0; i--) {
                int bit1 = (num >> i) & 1, bit2 = bit1 ^ 1; // 或1-bit1
                if (p.child[bit2] != null) {
                    ans |= (1 << i); // 异或结果为1
                    p = p.child[bit2];
                } else {
                    ans |= (0 << i);
                    p = p.child[bit1];
                }
            }
            return ans;
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int nQ = queries.length;
        int[][] newQueries = new int[nQ][3]; // {xi, mi, idx}
        for (int i = 0; i < nQ; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }

        Arrays.sort(newQueries, new Comparator<int[]>() {
            // 理解：1) query1/2: newQueries[i] vs [j];
            //      2) query1[1]= newQueries[i][1] – 比较mi
            @Override
            public int compare(int[] q1, int[] q2) {
                return q1[1] - q2[1]; // 按mi升序排列
            }
        });

        int[] res = new int[nQ];
        Trie trie = new Trie();
        int idx = 0, n = nums.length;
        for (int[] query : newQueries) {
            int x = query[0], m = query[1], qid = query[2];
            while (idx < n && nums[idx] <= m) {
                trie.insert(nums[idx++]);
            }
            if (idx == 0) { // 字典树为空（不满足while要求）
                res[qid] = -1;
            } else {
                res[qid] = trie.getMaxXor(x);
            }
        }
        return res;
    }

}
