class Solution {

    int[] parent;

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path Compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        parent = new int[n];

        // Initialize DSU
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Union adjacent nodes if their difference is within maxDiff
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                union(i, i - 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        // Answer queries
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = (find(u) == find(v));
        }

        return ans;
    }
}