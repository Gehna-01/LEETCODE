class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
            prod = 1 % k;
        }
    }

    static class SegmentTree {
        int n;
        int k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];

            build(1, 0, n - 1, nums);
        }

        // Merge two segments
        Node merge(Node left, Node right) {
            Node res = new Node(k);

            // Product of the complete segment
            res.prod = (left.prod * right.prod) % k;

            // Prefixes completely inside the left segment
            for (int r = 0; r < k; r++) {
                res.cnt[r] += left.cnt[r];
            }

            // Prefixes that start in the left segment
            // and continue into the right segment
            for (int r = 0; r < k; r++) {
                int newRemainder = (left.prod * r) % k;
                res.cnt[newRemainder] += right.cnt[r];
            }

            return res;
        }

        // Build segment tree
        void build(int node, int l, int r, int[] nums) {

            if (l == r) {
                int value = nums[l] % k;

                tree[node] = new Node(k);
                tree[node].prod = value;
                tree[node].cnt[value] = 1;

                return;
            }

            int mid = (l + r) / 2;

            build(node * 2, l, mid, nums);
            build(node * 2 + 1, mid + 1, r, nums);

            tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
            );
        }

        // Update nums[index] = value
        void update(int node, int l, int r, int index, int value) {

            if (l == r) {
                value %= k;

                tree[node] = new Node(k);
                tree[node].prod = value;
                tree[node].cnt[value] = 1;

                return;
            }

            int mid = (l + r) / 2;

            if (index <= mid) {
                update(node * 2, l, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, r, index, value);
            }

            tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
            );
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        // Query range [ql, qr]
        Node query(int node, int l, int r, int ql, int qr) {

            // Completely inside query range
            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) / 2;

            // Completely in left child
            if (qr <= mid) {
                return query(
                    node * 2,
                    l,
                    mid,
                    ql,
                    qr
                );
            }

            // Completely in right child
            if (ql > mid) {
                return query(
                    node * 2 + 1,
                    mid + 1,
                    r,
                    ql,
                    qr
                );
            }

            // Query overlaps both children
            Node left = query(
                node * 2,
                l,
                mid,
                ql,
                qr
            );

            Node right = query(
                node * 2 + 1,
                mid + 1,
                r,
                ql,
                qr
            );

            return merge(left, right);
        }

        Node query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }
    }

    public int[] resultArray(
        int[] nums,
        int k,
        int[][] queries
    ) {

        int n = nums.length;

        // Build segment tree
        SegmentTree tree = new SegmentTree(nums, k);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            tree.update(index, value);

            // Query [start, n - 1]
            Node result = tree.query(start, n - 1);

            // Number of ways whose product % k == x
            answer[i] = result.cnt[x];
        }

        return answer;
    }
}