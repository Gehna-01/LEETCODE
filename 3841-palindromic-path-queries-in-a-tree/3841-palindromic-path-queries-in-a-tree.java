import java.util.*;

class Solution {

    List<Integer>[] graph;
    int[] parent, depth, size, heavy, head, pos;
    int currentPos;
    char[] chars;
    SegmentTree seg;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        chars = s.toCharArray();

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        dfs(0, -1);

        currentPos = 0;
        decompose(0, 0);

        int[] base = new int[n];

        for (int i = 0; i < n; i++) {
            base[pos[i]] = 1 << (chars[i] - 'a');
        }

        seg = new SegmentTree(base);

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {

            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {

                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                chars[node] = newChar;

                int mask = 1 << (newChar - 'a');

                seg.update(pos[node], mask);

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                // 0 or 1 bits set => palindrome possible
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }


    // ------------------------------------------------
    // DFS
    // ------------------------------------------------

    private void dfs(int u, int p) {

        parent[u] = p;
        size[u] = 1;

        int maxSubtree = 0;

        for (int v : graph[u]) {

            if (v == p)
                continue;

            depth[v] = depth[u] + 1;

            dfs(v, u);

            size[u] += size[v];

            if (size[v] > maxSubtree) {

                maxSubtree = size[v];

                heavy[u] = v;
            }
        }
    }


    // ------------------------------------------------
    // Heavy Light Decomposition
    // ------------------------------------------------

    private void decompose(int u, int h) {

        head[u] = h;

        pos[u] = currentPos++;

        if (heavy[u] != -1) {

            decompose(heavy[u], h);
        }

        for (int v : graph[u]) {

            if (v == parent[u] || v == heavy[u])
                continue;

            decompose(v, v);
        }
    }


    // ------------------------------------------------
    // XOR of complete path u -> v
    // ------------------------------------------------

    private int queryPath(int u, int v) {

        int result = 0;

        while (head[u] != head[v]) {

            if (depth[head[u]] < depth[head[v]]) {

                int temp = u;
                u = v;
                v = temp;
            }

            result ^= seg.query(
                pos[head[u]],
                pos[u]
            );

            u = parent[head[u]];
        }

        // Same heavy chain
        if (depth[u] > depth[v]) {

            int temp = u;
            u = v;
            v = temp;
        }

        result ^= seg.query(pos[u], pos[v]);

        return result;
    }


    // =================================================
    // Segment Tree
    // =================================================

    static class SegmentTree {

        int n;

        int[] tree;

        SegmentTree(int[] arr) {

            n = arr.length;

            tree = new int[4 * n];

            build(1, 0, n - 1, arr);
        }


        private void build(
                int node,
                int left,
                int right,
                int[] arr) {

            if (left == right) {

                tree[node] = arr[left];

                return;
            }

            int mid = left + (right - left) / 2;

            build(
                node * 2,
                left,
                mid,
                arr
            );

            build(
                node * 2 + 1,
                mid + 1,
                right,
                arr
            );

            tree[node] =
                tree[node * 2]
                ^
                tree[node * 2 + 1];
        }


        // ------------------------------------------------
        // Point Update
        // ------------------------------------------------

        void update(int index, int value) {

            update(
                1,
                0,
                n - 1,
                index,
                value
            );
        }


        private void update(
                int node,
                int left,
                int right,
                int index,
                int value) {

            if (left == right) {

                tree[node] = value;

                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {

                update(
                    node * 2,
                    left,
                    mid,
                    index,
                    value
                );

            } else {

                update(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    index,
                    value
                );
            }

            tree[node] =
                tree[node * 2]
                ^
                tree[node * 2 + 1];
        }


        // ------------------------------------------------
        // Range XOR Query
        // ------------------------------------------------

        int query(int qLeft, int qRight) {

            return query(
                1,
                0,
                n - 1,
                qLeft,
                qRight
            );
        }


        private int query(
                int node,
                int left,
                int right,
                int qLeft,
                int qRight) {

            if (qLeft <= left && right <= qRight) {

                return tree[node];
            }

            int mid = left + (right - left) / 2;

            int result = 0;

            if (qLeft <= mid) {

                result ^= query(
                    node * 2,
                    left,
                    mid,
                    qLeft,
                    qRight
                );
            }

            if (qRight > mid) {

                result ^= query(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    qLeft,
                    qRight
                );
            }

            return result;
        }
    }
}