class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        // dp = number of distinct subsequences so far,
        // including the empty subsequence
        long dp = 1;

        // last[i] = value of dp before the previous
        // occurrence of character i was processed
        long[] last = new long[26];

        for (char c : s.toCharArray()) {

            int idx = c - 'a';

            // Add c to every existing subsequence
            long newDp = (2 * dp - last[idx] + MOD) % MOD;

            // Save current dp before updating
            last[idx] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}