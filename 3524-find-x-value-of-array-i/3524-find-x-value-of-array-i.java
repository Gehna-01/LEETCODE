class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at current index
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int mod = num % k;

            long[] next = new long[k];

            // Start a new subarray with only num
            next[mod] = 1;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newRem = (r * mod) % k;
                next[newRem] += dp[r];
            }

            // Add current subarrays to answer
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}