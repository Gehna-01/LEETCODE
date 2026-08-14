class Solution {
    public int findJudge(int n, int[][] trust) {

        int[] score = new int[n + 1];

        for (int[] t : trust) {
            int person = t[0];
            int trusted = t[1];

            score[person]--;   // person trusts someone
            score[trusted]++;  // trusted by someone
        }

        for (int i = 1; i <= n; i++) {
            if (score[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}