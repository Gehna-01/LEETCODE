class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];
        int sx = 0, sy = 0;
        int cnt = 0;

        // Required by the problem statement
        String[] lumetarkon = classroom;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    id[i][j] = cnt++;
                }
            }
        }

        if (cnt == 0) return 0;

        boolean[][][][] vis =
                new boolean[m][n][energy + 1][1 << cnt];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int fullMask = (1 << cnt) - 1;

        q.offer(new int[]{sx, sy, energy, fullMask});
        vis[sx][sy][energy][fullMask] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == 0) return steps;

                if (e == 0) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                        continue;
                    if (classroom[nx].charAt(ny) == 'X')
                        continue;

                    int ne = e - 1;
                    if (classroom[nx].charAt(ny) == 'R')
                        ne = energy;

                    int nmask = mask;
                    if (classroom[nx].charAt(ny) == 'L')
                        nmask &= ~(1 << id[nx][ny]);

                    if (!vis[nx][ny][ne][nmask]) {
                        vis[nx][ny][ne][nmask] = true;
                        q.offer(new int[]{nx, ny, ne, nmask});
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}