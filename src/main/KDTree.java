package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KDTree {
    class KDNode {
        int dim, coords[];
        KDNode left, right;

        KDNode(int coords[]) {
            this.dim = coords.length;
            this.coords = Arrays.copyOf(coords, dim);
        }
    }

    // Instance fields
    private KDNode root;
    private int size;
    private final int k;

    // Constructors
    public KDTree() {
        root = null;
        k = 2;
    }

    public KDTree(int k) {
        root = null;
        this.k = k > 2 ? k : 2;
    }

    // public methods
    public int getSize() {
        return size;
    }

    public int getHeight() {
        ArrayDeque<KDNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            int c = queue.size();
            for (int i = 0; i < c; i++) {
                KDNode curr = queue.removeFirst();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return height;
    }

    public void insert(int[] coords) {
        if (coords.length == k) root = insertKey(root, coords, 0);
    }

    private KDNode insertKey(KDNode n, int[] coords, int dim) {
        if (n == null) {
            size++;
            return new KDNode(coords);
        }

        if (coords[dim] < n.coords[dim]) {
            n.left = insertKey(n.left, coords, (dim + 1) % k);
        } else if (coords[dim] > n.coords[dim]) {
            n.right = insertKey(n.right, coords, (dim + 1) % k);
        } else {
            for (int i = 0; i < k; i++) {
                if (coords[i] != n.coords[i]) {
                    n.right = insertKey(n.right, coords, (dim + 1) % k);
                    return n;
                }
            }
        }

        return n;
    }

    public boolean contains(int[] coords) {
        if (coords.length == k) return containsKey(root, coords, 0);
        return false;
    }

    private boolean containsKey(KDNode n, int[] coords, int dim) {
        if (n == null) return false;

        if (coords[dim] < n.coords[dim]) {
            return containsKey(n.left, coords, (dim + 1) % k);
        } else if (coords[dim] > n.coords[dim]) {
            return containsKey(n.right, coords, (dim + 1) % k);
        } else {
            for (int i = 0; i < k; i++) {
                if (coords[i] != n.coords[i]) {
                    return containsKey(n.right, coords, (dim + 1) % k);
                }
            }
        }

        return true;
    }

    public void delete(int[] coords) {
        if (coords.length == k) root = deleteKey(root, coords, 0);
    }

    private KDNode deleteKey(KDNode n, int[] coords, int dim) {
        if (n == null) return null;

        if (coords[dim] < n.coords[dim]) {
            n.left = deleteKey(n.left, coords, (dim + 1) % k);
        } else if (coords[dim] > n.coords[dim] || !Arrays.equals(coords, n.coords)) {
            n.right = deleteKey(n.right, coords, (dim + 1) % k);
        } else {
            if (n.right != null) { // Find the smallest of current dimension in the right sub-tree
                int[] min = findMin(n.right, dim, (dim + 1) % k);
                n.coords = Arrays.copyOf(min, k);
                n.right = deleteKey(n.right, min, (dim + 1) % k);
            } else if (n.left != null) { // Find the smallest of current dimension in the left sub-tree, and make left sub-tree right
                int[] min = findMin(n.left, dim, (dim + 1) % k);
                n.coords = Arrays.copyOf(min, k);
                n.right = deleteKey(n.left, min, (dim + 1) % k);
                n.left = null;
            } else {
                return n = null;
            }
        }

        return n;
    }

    public int[] findMin(KDNode n, int dim, int currDim) {
        if (n == null) return null;

        int[] min;
        if (dim == currDim) {
            min = findMin(n.left, dim, (currDim + 1) % k);
        } else {
            int[] leftMin = findMin(n.left, dim, (currDim + 1) % k);
            int[] rightMin = findMin(n.right, dim, (currDim + 1) % k);

            if (leftMin == null) min = rightMin;
            else if (rightMin == null) min = leftMin;
            else min = leftMin[dim] < rightMin[dim] ? leftMin : rightMin;
        }

        if (min == null) return n.coords;
        else return min[dim] <= n.coords[dim] ? min : n.coords;
    }

    public List<int[]> preorderTraversal() {
        ArrayList<int[]> l = new ArrayList<>();
        preorderTraversalAux(root, l);
        return l;
    }

    private void preorderTraversalAux(KDNode n, ArrayList<int[]> l) {
        if (n == null) return;

        l.add(n.coords);
        preorderTraversalAux(n.left, l);
        preorderTraversalAux(n.right, l);
    }

    public static void printCoords(int[] coords) {
        System.out.print("(");
        for (int i: coords) System.out.print(i + ", ");
        System.out.print(") ");
    }
}
