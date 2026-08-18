class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;
        int answer = -1;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = 0; j <= n - k; j++) {

                boolean found = false;

                for (int x = j; x < j + k; x++) {
                    if (nums[x] == nums[i]) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    count++;
                }
            }

            if (count == 1) {
                answer = Math.max(answer, nums[i]);
            }
        }

        return answer;
    }
}