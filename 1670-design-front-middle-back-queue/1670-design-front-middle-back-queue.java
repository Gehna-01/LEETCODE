import java.util.*;

class FrontMiddleBackQueue {

    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    private void balance() {
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        } else if (right.size() > left.size() + 1) {
            left.addLast(right.removeFirst());
        }
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        left.addLast(val);
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (left.isEmpty() && right.isEmpty())
            return -1;

        int ans;
        if (!left.isEmpty())
            ans = left.removeFirst();
        else
            ans = right.removeFirst();

        balance();
        return ans;
    }

    public int popMiddle() {
        if (left.isEmpty() && right.isEmpty())
            return -1;

        int ans;
        if (left.size() == right.size())
            ans = left.removeLast();
        else
            ans = right.removeFirst();

        balance();
        return ans;
    }

    public int popBack() {
        if (right.isEmpty())
            return -1;

        int ans = right.removeLast();
        balance();
        return ans;
    }
}