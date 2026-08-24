class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Step 1: Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Step 2: DP from right to left
        int ans = stones[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            ans = Math.max(ans, stones[i] - ans);
        }

        return ans;
    }
}