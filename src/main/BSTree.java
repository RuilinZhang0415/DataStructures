package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BSTree {
    public class TreeNode {
        public int key;
        public TreeNode left, right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    // Instance Vairables
    public TreeNode root;

    // Constructors
    public BSTree() {
        root = null;
    }

    // Public Instance Methods
    public int getSize(TreeNode n) {
        if (n == null) return 0;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(n);
        int size = 0;
        while (!queue.isEmpty()) {
            size++;
            TreeNode curr = queue.removeFirst();
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        return size;
    }

//    public int getSize(TreeNode n) { // Inorder traversal, in place
//        int size = 0;
//        TreeNode curr = n, pre = null;
//        while (curr != null) {
//            if (curr.left == null) { // Curr min found, count it and advance
//                curr = curr.right;
//                size++;
//            } else {
//                // Find the inorder predecessor of curr
//                pre = curr.left;
//                while (pre.right != null && pre.right != curr) pre = pre.right;
//
//                if (pre.right == null) { // Left subtree haven't been visited
//                    pre.right = curr; // Link curr to pre, so that we can move back
//                    curr = curr.left; // Go left and find curr min
//                } else { // Left subtree have been visited, thus curr node is the curr min
//                    pre.right = null; // Remove link
//                    curr = curr.right; // Count it and advance
//                    size++;
//                }
//
//            }
//        }
//
//        return size;
//    }

    public int getHeight(TreeNode n) {
        if (n == null) return 0;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(n);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            int c = queue.size();
            for (int i = 0; i < c; i++) {
                TreeNode curr = queue.removeFirst();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return height;
    }

    public int minKey(TreeNode n) {
        if (n == null) return Integer.MAX_VALUE;

        TreeNode curr = n;
        while (curr.left != null) {
            curr = curr.left;
        }

        return curr.key;
    }

    public int maxKey(TreeNode n) {
        if (n == null) return Integer.MIN_VALUE;

        TreeNode curr = n;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr.key;
    }

    public boolean containsKey(TreeNode n, int key) {
        TreeNode curr = n;
        while (curr != null) {
            if (key < curr.key) curr = curr.left;
            else if (key > curr.key) curr = curr.right;
            else return true;
        }

        return false;
    }

    public TreeNode insertKey(TreeNode n, int key) {
        TreeNode curr = n, prev = null;
        while (curr != null) {
            if (key < curr.key) {
                prev = curr;
                curr = curr.left;
            } else if (key > curr.key) {
                prev = curr;
                curr = curr.right;
            } else {
                return n;
            }
        }
        if (prev == null) {
            return new TreeNode(key);
        } else {
            if (key < prev.key) prev.left = new TreeNode(key);
            else prev.right = new TreeNode(key);
        }

        return n;
    }

    public TreeNode deleteKey(TreeNode n, int key) {
        if (n == null) {
            return null;
        } else if (key < n.key) {
            n.left = deleteKey(n.left, key);
        } else if (key > n.key) {
            n.right = deleteKey(n.right, key);
        } else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            n.key = minKey(n.right);
            n.right = deleteKey(n.right, n.key);
        }

        return n;
    }

    public List<Integer> preorderTraversal(TreeNode n) {
        ArrayList<Integer> l = new ArrayList<>();
        preorderTraversalAux(n, l);
        return l;
    }

    public void preorderTraversalAux(TreeNode n, ArrayList<Integer> l) {
        if (n == null) return;

        l.add(n.key);
        preorderTraversalAux(n.left, l);
        preorderTraversalAux(n.right, l);
    }

    // Given a preorder traversal, construct the binary search tree
    public TreeNode constructTreeFromPreorderTraversal(int[] pre) {
        int index[] = new int[] {0};
        return constructTreeFromPreorderTraversalAux(pre, Integer.MIN_VALUE, Integer.MAX_VALUE, index);
    }

    private TreeNode constructTreeFromPreorderTraversalAux(int[] pre, int low, int high, int[] currIndex) {
        if (currIndex[0] >= pre.length) return null;
        if (pre[currIndex[0]] < low || pre[currIndex[0]] > high) return null;

        TreeNode currNode = new TreeNode(pre[currIndex[0]]);
        currIndex[0]++;
        currNode.left = constructTreeFromPreorderTraversalAux(pre, low, currNode.key, currIndex);
        currNode.right = constructTreeFromPreorderTraversalAux(pre, currNode.key, high, currIndex);

        return currNode;
    }

    // Convert a sorted list to a binary search tree
    public TreeNode sortedListToBST(List<Integer> list) {
        return sortedListToBSTAux(list, new int[] {0}, list.size());
    }

    private TreeNode sortedListToBSTAux(List<Integer> list, int[] index, int n) {
        if (n <= 0) return null;

        TreeNode left = sortedListToBSTAux(list, index, n / 2);
        TreeNode parent = new TreeNode(list.get(index[0]));
        index[0]++;
        TreeNode right = sortedListToBSTAux(list, index, n - n / 2 - 1);

        parent.left = left;
        parent.right = right;

        return parent;
    }

    // Find the median under the given node, in place
    // Similar to the in-place vertion of the getSize() method
    public double median(TreeNode n) {
        int size = getSize(n), count = 0, median = (size + 2) / 2;
        boolean single = size % 2 == 0 ? false : true;

        TreeNode curr = n, predecessor = null, prev = null;

        while (curr != null) {
            if (curr.left == null) {
                count++;

                if (count == median) {
                    if (single) return curr.key;
                    else return (curr.key + prev.key) / 2.0;
                }

                prev = curr;
                curr = curr.right;
            } else {
                predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = curr;
                    prev = curr;
                    curr = curr.left;
                } else {
                    count++;

                    if (count == median) {
                        if (single) return curr.key;
                        else return (curr.key + prev.key) / 2.0;
                    }

                    predecessor.right = null;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
