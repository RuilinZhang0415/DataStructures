package main;

public class DisjointSet {
    public int[] parent;
    public int[] size;

    public DisjointSet(int t) {
        parent = new int[t + 1];
        size = new int[t + 1];
        for (int i = 0; i < t + 1; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Recursive Path compression: makes every node between the query node and the root point to the root.
//    public int find(int x) {
//        if (parent[x] != x) parent[x] = find(parent[x]);
//        return parent[x];
//    }

    // Iterative Path compression
//    public int find(int x) {
//        int root = x;
//        while (parent[root] != root) root = parent[root];
//
//        while (parent[x] != x) {
//            int next = parent[x];
//            parent[x] = root;
//            x = next;
//        }
//
//        return root;
//    }

    // Path splitting:
//    public int find(int x) {
//        while (parent[x] != x) {
//            int curr = x;
//            x = parent[x];
//            parent[curr] = parent[parent[curr]];
//        }
//
//        return x;
//    }

    // Path halving:
    public int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[y] = x;
        size[x] += size[y];
    }
}
