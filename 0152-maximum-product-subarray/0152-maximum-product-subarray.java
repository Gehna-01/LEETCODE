class Solution {
    public int maxProduct(int[] nums) {
        int max= nums[0];
        int min=nums[0];
        int maxpro=nums[0];
        for(int i=1;i<nums.length;i++){
            int oldmax=max;
            int oldmin=min;
            max=Math.max(nums[i],Math.max(nums[i]*oldmax,nums[i]*oldmin));
            min=Math.min(nums[i],Math.min(nums[i]*oldmax,nums[i]*oldmin));
            maxpro=Math.max(max,maxpro);
        }
        return maxpro;
    }
}