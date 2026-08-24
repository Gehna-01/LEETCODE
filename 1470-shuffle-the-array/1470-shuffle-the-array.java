class Solution {
    public int[] shuffle(int[] nums, int n) {
        int [] ans=new int[nums.length];
        int i=0;
        int c=0;
        while(n<nums.length){
            ans[c++]=nums[i++];
            ans[c++]=nums[n++];

        }
        return ans;
    }
}