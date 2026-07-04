class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int onecount=0;
        int maxcount=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                onecount++;
                maxcount=Math.max(maxcount,onecount);
            }
            else{
                onecount=0;
            }
            
        }
        return maxcount;

    }
}