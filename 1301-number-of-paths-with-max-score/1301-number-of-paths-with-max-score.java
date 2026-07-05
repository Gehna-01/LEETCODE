class Solution {

    int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score)
            Arrays.fill(row, -1);

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                char ch = board.get(i).charAt(j);

                if (ch == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;

                if (i > 0)
                    best = Math.max(best, score[i - 1][j]);

                if (j > 0)
                    best = Math.max(best, score[i][j - 1]);

                if (i > 0 && j > 0)
                    best = Math.max(best, score[i - 1][j - 1]);

                if (best == -1)
                    continue;

                long cnt = 0;

                if (i > 0 && score[i - 1][j] == best)
                    cnt += ways[i - 1][j];

                if (j > 0 && score[i][j - 1] == best)
                    cnt += ways[i][j - 1];

                if (i > 0 && j > 0 && score[i - 1][j - 1] == best)
                    cnt += ways[i - 1][j - 1];

                ways[i][j] = (int) (cnt % MOD);

                if (ch == 'S')
                    score[i][j] = best;
                else
                    score[i][j] = best + (ch - '0');
            }
        }

        if (ways[n - 1][n - 1] == 0)
            return new int[] {0, 0};

        return new int[] {score[n - 1][n - 1], ways[n - 1][n - 1]};
    }
}
