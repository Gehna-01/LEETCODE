import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        Set<Integer> pairXor = new HashSet<>();
        BitSet tripletXor = new BitSet();

        // Store all unique XORs of pairs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        // Combine each pair XOR with every element
        for (int x : pairXor) {
            for (int num : nums) {
                tripletXor.set(x ^ num);
            }
        }

        return tripletXor.cardinality();
    }
}