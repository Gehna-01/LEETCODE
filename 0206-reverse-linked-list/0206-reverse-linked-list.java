class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // Store next node
            curr.next = prev;          // Reverse link
            prev = curr;               // Move prev
            curr = next;               // Move current
        }

        return prev;
    }
}