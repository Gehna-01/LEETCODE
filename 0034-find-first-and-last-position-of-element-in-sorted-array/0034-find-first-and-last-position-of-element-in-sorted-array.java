class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first=firstoccur(nums,target);
        int last=lastoccur(nums,target);
        return new int[]{first,last};
    }

        static int firstoccur(int[] nums, int target){
            int left=0;
            int ans=-1;
            int right=nums.length-1;
            while(left<=right){
               int mid=left+(right-left)/2;
                if(nums[mid]==target){
                    ans=mid;
                    right=mid-1;
                }
                else if(nums[mid]>target){
                    right=mid-1;
                }
                else{
                    left=mid+1;
                }
            }
            return ans;
        }

        static int lastoccur(int[]nums,int target){
            int left=0;
            int ans=-1;
            int right=nums.length-1;
            while(left<=right){
                int mid=left+(right-left)/2;
                if(nums[mid]==target){
                    ans=mid;
                    left=mid+1;
                }
                else if(nums[mid]>target){
                    right=mid-1;
                }
                else{
                    left=mid+1;
                }
            }
            return ans;
        }
    }


  