class Solution {

    int MOD = 1_000_000_007;
    int[][][] dp;
    int[] nums;
    int n;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;

        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        dp = new int[n + 1][max + 1][max + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= max; j++) {
                java.util.Arrays.fill(dp[i][j], -1);
            }
        }

        return dfs(0, 0, 0);
    }

    private int dfs(int index, int gcd1, int gcd2) {

        if (index == n) {
            return (gcd1 != 0 && gcd1 == gcd2) ? 1 : 0;
        }

        if (dp[index][gcd1][gcd2] != -1) {
            return dp[index][gcd1][gcd2];
        }

        long ans = 0;

        // Put current element in seq1
        ans += dfs(
                index + 1,
                gcd(gcd1, nums[index]),
                gcd2
        );

        // Put current element in seq2
        ans += dfs(
                index + 1,
                gcd1,
                gcd(gcd2, nums[index])
        );

        // Skip current element
        ans += dfs(index + 1, gcd1, gcd2);

        ans %= MOD;

        dp[index][gcd1][gcd2] = (int) ans;

        return dp[index][gcd1][gcd2];
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}