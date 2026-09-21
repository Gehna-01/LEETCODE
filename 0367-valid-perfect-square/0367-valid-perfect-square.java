class Solution {
    public boolean isPerfectSquare(int num) {
       
       if(num==1){
        return true;
       }
       int low=1;
       int high=num/2;

       while(low<=high){
        int mid=low+(high-low)/2;
        long x=(long)mid*mid;
        if(x==num){
            return true;
        }
        else if(x>num){
            high=mid-1;
        }
        else{
            low=mid+1;
        }
       } 
       return false;
    }
}