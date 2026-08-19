class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats row-wise
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        // Every completely empty row can accommodate 2 families
        int ans = (n - map.size()) * 2;

        // Check rows having reserved seats
        for (int row : map.keySet()) {

            HashSet<Integer> seats = map.get(row);

            boolean left = true;   // 2,3,4,5
            boolean middle = true; // 4,5,6,7
            boolean right = true;  // 6,7,8,9

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            if (left && right) {
                // Can place 2 families
                ans += 2;
            } 
            else if (left || middle || right) {
                // Can place 1 family
                ans += 1;
            }
        }

        return ans;
    }
}