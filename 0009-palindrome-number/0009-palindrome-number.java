class Solution {
    public boolean isPalindrome(int x) {
        int n=x;
        int sum=0;
        while(n>0){
            int d=n%10;
            sum=sum*10+d;
            n/=10;
        }
        if(sum==x){
            return true;
        }
        else{
            return false;
        }
    }
}