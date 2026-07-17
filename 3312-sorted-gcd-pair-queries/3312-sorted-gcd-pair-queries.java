class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        // freq[i] = how many times i appears
        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        // gcdCount[g] = number of pairs having gcd exactly g
        long[] gcdCount = new long[max + 1];

        // Process from largest gcd to smallest
        for (int g = max; g >= 1; g--) {

            long count = 0;

            // Count numbers divisible by g
            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            // Total pairs divisible by g
            long pairs = count * (count - 1) / 2;

            // Remove pairs whose gcd is multiple of g
            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                pairs -= gcdCount[multiple];
            }

            gcdCount[g] = pairs;
        }

        // Prefix sums
        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + gcdCount[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1; // convert 0-based index to count

            int left = 1;
            int right = max;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (prefix[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = left;
        }

        return ans;
    }
}