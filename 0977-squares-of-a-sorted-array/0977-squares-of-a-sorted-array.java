class Solution {
    public int[] sortedSquares(int[] nums) {
        int left=0;
        int right=nums.length -1;
        int arr[]=new int[nums.length];
        int index=nums.length-1;
        while(left<=right){
            int lsq=nums[left]*nums[left];
            int rsq=nums[right]*nums[right];
            if(lsq>rsq){
                arr[index]=lsq;
                left++;
            }
            else{
                arr[index]=rsq;
                right--;
            }
            index--;
        }
        return arr;
    }
}