class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long[] freq = new long[100001];
        long total = (long) k1 + k2;
        long sum = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            sum += diff;
        }

        if (sum <= total) {
            return 0;
        }

        for (int d = 100000; d > 0 && total > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            long move = Math.min(freq[d], total);
            freq[d] -= move;
            freq[d - 1] += move;
            total -= move;
        }

        long result = 0;

        for (int d = 1; d <= 100000; d++) {
            result += freq[d] * d * d;
        }

        return result;
    }
}