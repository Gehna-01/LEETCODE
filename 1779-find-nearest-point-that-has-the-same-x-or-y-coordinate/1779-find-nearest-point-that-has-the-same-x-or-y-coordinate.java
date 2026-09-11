class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {

        int ans = -1;
        int mindis = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {

            int a = points[i][0];
            int b = points[i][1];

            if (x == a || y == b) {

                int dis = Math.abs(x - a) + Math.abs(y - b);

                if (dis < mindis) {
                    mindis = dis;
                    ans = i;
                }
            }
        }

        return ans;
    }
}