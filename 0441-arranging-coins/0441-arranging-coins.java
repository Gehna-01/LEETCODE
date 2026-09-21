class Solution {
    public int arrangeCoins(int n) {
        int low=0;
        int high=n;
        int ans=0;
        while(high>=low){
            int mid=low+(high-low)/2;
            long x= (long) mid*(mid+1)/2;
            if(x<=n){
                ans=mid;
                low=mid+1;
            }
            else{
                high=mid-1;
            }
        }
        return ans;
    }
}