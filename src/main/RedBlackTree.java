package main;

import java.util.ArrayList;
import java.util.List;

// A left-leaning Red-Black Binary Search Tree
// The deletion operation not available.
public class RedBlackTree {
    public class TreeNode {
        private int key;
        private TreeNode left, right;
        private boolean isRed, removed;

        public TreeNode(int k) {
            key = k;
        }

        public TreeNode(int k, boolean red) {
            key = k;
            isRed = red;
        }
    }

    public TreeNode root;

    public RedBlackTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertAux(root, key);
        root.isRed = false;
    }

    private TreeNode insertAux(TreeNode n, int key) {
        if (n == null) return new TreeNode(key, true);

        if (key < n.key) {
            n.left = insertAux(n.left, key);
        } else if (key > n.key) {
            n.right = insertAux(n.right, key);
        } else {
            if (n.removed) n.removed = false;

            return n;
        }

        if (n.left != null && n.left.isRed && n.right != null && n.right.isRed) n = flipColors(n);

        if (n.right != null && n.right.isRed) n = rotateLeft(n);

        if (n.left != null && n.left.isRed && n.left.left != null && n.left.left.isRed) n = rotateRightAndFlipColors(n);

        return n;
    }

    private TreeNode rotateLeft(TreeNode n) { // Fix a right-leaning red node
        TreeNode temp = n.right;
        n.right = temp.left;
        temp.left = n;
        temp.isRed = n.isRed;
        n.isRed = true;

        return temp;
    }

    private TreeNode flipColors(TreeNode n) { // Fix a node connected to two red nodes
        n.left.isRed = n.right.isRed = false;
        n.isRed = true;

        return n;
    }

    private TreeNode rotateRightAndFlipColors(TreeNode n) { // Fix a node with a red left child whose left child is also red
        TreeNode temp = n.left;
        n.left = temp.right;
        temp.right = n;

        return flipColors(temp);
    }

    public boolean containsKey(TreeNode n, int key) {
        if (n == null) return false;

        return (n.key == key && !n.removed) || containsKey(n.left, key) || containsKey(n.right, key);
    }

    public List<Integer> preorderTraversal() {
        List<Integer> list = new ArrayList<>();
        preorderTraversalAux(root, list);

        return list;
    }

    public void preorderTraversalAux(TreeNode n, List<Integer> list) {
        if (n == null) return;

        if (!n.removed) list.add(n.key);
        preorderTraversalAux(n.left, list);
        preorderTraversalAux(n.right, list);
    }

    public static void main(String[] args) {

    }
}
