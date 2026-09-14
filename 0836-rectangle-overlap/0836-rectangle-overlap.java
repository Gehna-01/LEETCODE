class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        // rec = [x1, y1, x2, y2]
        
        // Check if there is NO overlap
        if (rec1[2] <= rec2[0] ||   // rec1 is completely left
            rec2[2] <= rec1[0] ||   // rec2 is completely left
            rec1[3] <= rec2[1] ||   // rec1 is completely below
            rec2[3] <= rec1[1]) {   // rec2 is completely below
            
            return false;
        }
        
        return true;
    }
}