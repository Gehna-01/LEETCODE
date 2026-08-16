class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int stone : stones) {
            count[stone % 3]++;
        }

        if (count[0] % 2 == 0) {
            // Even number of 0-remainder stones
            return count[1] > 0 && count[2] > 0;
        } else {
            // Odd number of 0-remainder stones
            return Math.abs(count[1] - count[2]) > 2;
        }
    }
}