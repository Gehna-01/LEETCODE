class Solution {

    class Node {
        int left;
        int right;

        int prefix; // longest same-character prefix
        int suffix; // longest same-character suffix
        int max;    // longest same-character substring

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update the string
            arr[index] = ch;

            // Update segment tree
            update(1, 0, n - 1, index);

            // Root contains the answer
            answer[i] = tree[1].max;
        }

        return answer;
    }

    // Build segment tree
    void build(int node, int l, int r) {

        tree[node] = new Node(l, r);

        // Leaf node
        if (l == r) {
            tree[node].prefix = 1;
            tree[node].suffix = 1;
            tree[node].max = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    // Update one character
    void update(int node, int l, int r, int index) {

        if (l == r) {
            tree[node].prefix = 1;
            tree[node].suffix = 1;
            tree[node].max = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node);
    }

    // Merge left and right children
    void merge(int node) {

        Node left = tree[node * 2];
        Node right = tree[node * 2 + 1];
        Node current = tree[node];

        int leftLength = left.right - left.left + 1;
        int rightLength = right.right - right.left + 1;

        // Initially take the best from either side
        current.max = Math.max(left.max, right.max);

        current.prefix = left.prefix;
        current.suffix = right.suffix;

        // If boundary characters are same,
        // we can join the two parts.
        if (arr[left.right] == arr[right.left]) {

            // Entire left part is same character
            if (left.prefix == leftLength) {
                current.prefix = leftLength + right.prefix;
            }

            // Entire right part is same character
            if (right.suffix == rightLength) {
                current.suffix = rightLength + left.suffix;
            }

            // Join left suffix + right prefix
            current.max = Math.max(
                    current.max,
                    left.suffix + right.prefix
            );
        }
    }
}