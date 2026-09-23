class Solution {
    public int minOperations(int[] nums, int x) {

        int total = 0;

        // Find total sum
        for (int num : nums) {
            total += num;
        }

        // Sum of the subarray we want to KEEP
        int target = total - x;

        int n = nums.length;

        // If target < 0, impossible
        if (target < 0) {
            return -1;
        }

        // If target == 0, remove everything
        if (target == 0) {
            return n;
        }

        int left = 0;
        int currentSum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {

            currentSum += nums[right];

            // Shrink window if sum becomes too large
            while (currentSum > target) {
                currentSum -= nums[left];
                left++;
            }

            // Found a valid subarray
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLen == -1) {
            return -1;
        }

        // Remove everything outside the longest subarray
        return n - maxLen;
    }
}