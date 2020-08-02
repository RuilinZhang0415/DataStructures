package main;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    public static class AVLNode {
        public int key;
        public AVLNode left, right;

        public AVLNode(int key) {
            this.key = key;
        }
    }

    // Instance variables
    public AVLNode root;
    private final int maxInbalance;

    // Constructors
    public AVLTree() {
        root = null;
        maxInbalance = 1;
    }

    public AVLTree(int maxInbalance) {
        root = null;
        this.maxInbalance = maxInbalance;
    }

    public int getSize(AVLNode n) {
        return n == null ? 0 : 1 + getSize(n.left) + getSize(n.right);
    }

    public int getHeight(AVLNode n) {
        return n == null ? 0 : 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }

    public boolean containsKey(int key) {
        AVLNode curr = root;
        while (curr != null) {
            if (key < curr.key) curr = curr.left;
            else if (key > curr.key) curr = curr.right;
            else return true;
        }

        return false;
    }

    public void insertKey(int key) {
        root = insertAux(root, key);
    }

    private AVLNode insertAux(AVLNode n, int key) {
        if (n == null) return new AVLNode(key);
        else if (key < n.key) n.left = insertAux(n.left, key);
        else if (key > n.key) n.right = insertAux(n.right, key);
        else return n;

        int lh = getHeight(n.left);
        int rh = getHeight(n.right);

        if (lh - rh > maxInbalance) {
            if (getHeight(n.left.left) < getHeight(n.left.right)) {
                n.left = rotateLeft(n.left);
                return rotateRight(n);
            } else {
                return rotateRight(n);
            }
        } else if (rh - lh > maxInbalance) {
            if (getHeight(n.right.right) < getHeight(n.right.left)) {
                n.right = rotateRight(n.right);
                return rotateLeft(n);
            } else {
                return rotateLeft(n);
            }
        }

        return n;
    }

    public void deleteKey(int key) {
        root = deleteAux(root, key);
    }

    private AVLNode deleteAux(AVLNode n, int key) {
        if (n == null) { return null; }
        else if (key < n.key) { n.left = deleteAux(n.left, key); }
        else if (key > n.key) { n.right = deleteAux(n.right, key); }
        else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            n.key = minKey(n.right);
            n.right = deleteAux(n.right, n.key);
        }

        int lh = getHeight(n.left);
        int rh = getHeight(n.right);

        if (lh - rh > maxInbalance) {
            if (getHeight(n.left.left) < getHeight(n.left.right)) {
                n.left = rotateLeft(n.left);
                return rotateRight(n);
            } else {
                return rotateRight(n);
            }
        }
        else if (rh - lh > maxInbalance) {
            if (getHeight(n.right.right) < getHeight(n.right.left)) {
                n.right = rotateRight(n.right);
                return rotateLeft(n);
            } else {
                return rotateLeft(n);
            }
        }

        return n;
    }

    public int minKey(AVLNode n) {
        if (n == null) return Integer.MAX_VALUE;

        AVLNode curr = n;
        while (curr.left != null) {
            curr = curr.left;
        }

        return curr.key;
    }

    public int maxKey(AVLNode n) {
        if (n == null) return Integer.MIN_VALUE;

        AVLNode curr = n;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr.key;
    }

    public AVLNode rotateLeft(AVLNode n) {
        AVLNode temp = n.right;
        n.right = temp.left;
        temp.left = n;
        return temp;
    }

    public AVLNode rotateRight(AVLNode n) {
        AVLNode temp = n.left;
        n.left = temp.right;
        temp.right = n;
        return temp;
    }

    public List<Integer> preorderTraversal(AVLNode n) {
        ArrayList<Integer> l = new ArrayList<>();
        preorderTraversalAux(n, l);
        return l;
    }

    public void preorderTraversalAux(AVLNode n, ArrayList<Integer> l) {
        if (n == null) return;

        l.add(n.key);
        preorderTraversalAux(n.left, l);
        preorderTraversalAux(n.right, l);
    }

    public static void main(String[] args) {

    }
}
