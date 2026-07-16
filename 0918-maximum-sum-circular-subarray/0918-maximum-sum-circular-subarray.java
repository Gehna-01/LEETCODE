class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currMax = 0;
        int maxSum = Integer.MIN_VALUE;

        int currMin = 0;
        int minSum = Integer.MAX_VALUE;

        for (int num : nums) {

            totalSum += num;

            // Maximum Subarray (Kadane)
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            // Minimum Subarray (Kadane)
            currMin = Math.min(num, currMin + num);
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