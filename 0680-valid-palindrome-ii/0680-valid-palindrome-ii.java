class Solution {
    public boolean validPalindrome(String s) {
        s=s.toLowerCase();
        int left=0;
        int right= s.length() - 1;
        int c=0;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)&&c==0){
                c++;
                if (ispalin(s,left+1,right)||ispalin(s,left,right-1)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(s.charAt(left)!=s.charAt(right)&&c!=0){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    private boolean ispalin(String s, int left, int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}