class Solution {
    int m, n;
    char[][] grid;
    boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Total path length must be even
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // Valid parentheses path must start with '('
        // and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        visited = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {

        // Already visited this state
        if (visited[i][j][balance]) {
            return false;
        }

        visited[i][j][balance] = true;

        // Update balance
        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Balance can never become negative
        if (balance < 0) {
            return false;
        }

        // Not enough cells left to close all '('
        int remaining = (m - 1 - i) + (n - 1 - j);

        if (balance > remaining) {
            return false;
        }

        // Reached destination
        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        // Move down
        if (i + 1 < m && dfs(i + 1, j, balance)) {
            return true;
        }

        // Move right
        if (j + 1 < n && dfs(i, j + 1, balance)) {
            return true;
        }

        return false;
    }
}