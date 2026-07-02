class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum=0;
        for(int i=0;i<k;i++){
            sum+=nums[i];
        }
        double maxsum=sum;
        double avg=maxsum/k;
        for(int i=k;i<nums.length;i++){
            sum=sum-nums[i-k]+nums[i];
            if(sum>maxsum){
                maxsum=sum;
                avg=maxsum/k;
            }

        }
        return avg;
    }
}