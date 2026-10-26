class Solution {

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start DFS whenever we find an unvisited land cell
                if (grid[i][j] != 0 && !visited[i][j]) {

                    long sum = dfs(grid, visited, i, j);

                    // Check whether total island value is divisible by k
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, boolean[][] visited, int i, int j) {

        // Boundary / invalid / water / already visited
        if (i < 0 || i >= grid.length ||
            j < 0 || j >= grid[0].length ||
            grid[i][j] == 0 ||
            visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        long sum = grid[i][j];

        sum += dfs(grid, visited, i + 1, j);
        sum += dfs(grid, visited, i - 1, j);
        sum += dfs(grid, visited, i, j + 1);
        sum += dfs(grid, visited, i, j - 1);

        return sum;
    }
}