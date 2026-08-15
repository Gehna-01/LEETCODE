class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;

        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        if (xor != 0) {
            return n;
        }

        // XOR is 0, so remove one element.
        // To keep XOR non-zero, we need an element != 0.
        for (int num : nums) {
            if (num != 0) {
                return n - 1;
            }
        }

        return 0;
    }
}