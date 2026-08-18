class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = nums[0];

        int currMax = nums[0];
        int maxSum = nums[0];

        int currMin = nums[0];
        int minSum = nums[0];

        for (int i=1;i<nums.length;i++) {

            totalSum += nums[i];

            // Maximum Subarray (Kadane)
            currMax = Math.max(nums[i], currMax + nums[i]);
            maxSum = Math.max(maxSum, currMax);

            // Minimum Subarray (Kadane)
            currMin = Math.min(nums[i], currMin + nums[i]);
            minSum = Math.min(minSum, currMin);
        }

        // If all elements are negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Maximum of normal and circular subarray
        return Math.max(maxSum, totalSum - minSum);
    }
}