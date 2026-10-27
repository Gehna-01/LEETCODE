class Solution {
    public String[] createGrid(int n, int m) {
        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < m; j++) {

                // First row
                if (i == 0) {
                    row.append('.');
                }
                // Last column
                else if (j == m - 1) {
                    row.append('.');
                }
                // Block other cells
                else {
                    row.append('#');
                }
            }

            grid[i] = row.toString();
        }

        return grid;
    }
}