package main;

import java.util.*;

public class BSTree {
    public class TreeNode {
        public int key;
        public TreeNode left, right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    // Instance Vairables
    private TreeNode root;
    private int size;

    // Constructors
    public BSTree() {
        root = null;
    }

    // Public Instance Methods
    public int getSize() {
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

    public int getHeight() {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
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

    public boolean contains(int key) {
        TreeNode curr = root;
        while (curr != null) {
            if (key < curr.key) curr = curr.left;
            else if (key > curr.key) curr = curr.right;
            else return true;
        }

        return false;
    }

    public void insert(int key) {
        root = insertKey(root, key);
    }

    private TreeNode insertKey(TreeNode n, int key) {
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

        size++;
        if (prev == null) {
            return new TreeNode(key);
        } else {
            if (key < prev.key) prev.left = new TreeNode(key);
            else prev.right = new TreeNode(key);
        }

        return n;
    }

    public void delete(int key) {
        root = deleteKey(root, key);
    }

    private TreeNode deleteKey(TreeNode n, int key) {
        if (n == null) {
            return null;
        } else if (key < n.key) {
            n.left = deleteKey(n.left, key);
        } else if (key > n.key) {
            n.right = deleteKey(n.right, key);
        } else {
            if (n.left == null) {
                size--;
                return n.right;
            }
            if (n.right == null) {
                size--;
                return n.left;
            }

            n.key = minKey(n.right);
            n.right = deleteKey(n.right, n.key);
        }

        return n;
    }

    public class Tuple {
        TreeNode node;
        int level;

        public Tuple(TreeNode n, int i) {
            node = n;
            level = i;
        }
    }

    public List<List<Integer>> verticalTraversal() {
        return verticalTraversalAux(root);
    }

    private List<List<Integer>> verticalTraversalAux(TreeNode n) {
        if (n == null) return new ArrayList<>();

        List<List<Integer>> lists = new ArrayList<>();
        int range[] = new int[] {1, 0};
        Deque<Tuple> queue = new ArrayDeque<>();
        queue.addFirst(new Tuple(n, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            Tuple curr = queue.removeLast();

            if (curr.level < range[0]) {
                range[0] = curr.level;
                List<Integer> toAdd = new ArrayList<>();
                toAdd.add(curr.node.key);
                lists.add(0, toAdd);
            } else if (curr.level > range[1]) {
                range[1] = curr.level;
                List<Integer> toAdd = new ArrayList<>();
                toAdd.add(curr.node.key);
                lists.add(range[1] - range[0], toAdd);
            } else {
                lists.get(curr.level - range[0]).add(curr.node.key);
            }

            if (curr.node.left != null) queue.addFirst(new Tuple(curr.node.left, curr.level - 1));
            if (curr.node.right != null) queue.addFirst(new Tuple(curr.node.right, curr.level + 1));
        }


        return lists;
    }

//    public List<List<Integer>> verticalTraversal(TreeNode n) {
//        if (n == null) return new ArrayList<>();
//
//        List<List<Integer>> lists = new ArrayList<>();
//        List<Integer> curr = new ArrayList<>();
//        curr.add(n.key);
//        lists.add(curr);
//        int[] range = new int[] {0, 0};
//        verticalTraversalAux(n.left, lists, range, -1);
//        verticalTraversalAux(n.right, lists, range, 1);
//
//        return lists;
//    }
//
//    private void verticalTraversalAux(TreeNode n, List<List<Integer>> lists, int[] range, int currLayer) {
//        if (n == null) return;
//
//        if (currLayer < range[0]) {
//            range[0] = currLayer;
//            List<Integer> toAdd = new ArrayList<>();
//            toAdd.add(n.key);
//            lists.add(0, toAdd);
//        } else if (currLayer > range[1]) {
//            range[1] = currLayer;
//            List<Integer> toAdd = new ArrayList<>();
//            toAdd.add(n.key);
//            lists.add(range[1] - range[0], toAdd);
//        } else {
//            lists.get(currLayer - range[0]).add(n.key);
//        }
//
//        verticalTraversalAux(n.left, lists, range, currLayer - 1);
//        verticalTraversalAux(n.right, lists, range, currLayer + 1);
//    }

    public List<Integer> preorderTraversal() {
        ArrayList<Integer> l = new ArrayList<>();
        preorderTraversalAux(root, l);
        return l;
    }

    private void preorderTraversalAux(TreeNode n, ArrayList<Integer> l) {
        if (n == null) return;

        l.add(n.key);
        preorderTraversalAux(n.left, l);
        preorderTraversalAux(n.right, l);
    }

    // Given a preorder traversal, construct the binary search tree
    public BSTree constructTreeFromPreorderTraversal(int[] pre) {
        BSTree t = new BSTree();
        int index[] = new int[] {0};
        t.root = constructTreeFromPreorderTraversalAux(pre, Integer.MIN_VALUE, Integer.MAX_VALUE, index);

        return t;
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
    public BSTree sortedListToBST(List<Integer> list) {
        BSTree t = new BSTree();
        t.root = sortedListToBSTAux(list, new int[] {0}, list.size());

        return t;
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
    // Similar to the in-place version of the getSize() method
    public double median() {
        int size = getSize(), count = 0, median = (size + 2) / 2;
        boolean single = size % 2 == 0 ? false : true;

        TreeNode curr = root, predecessor = null, prev = null;

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

    // Compute the longest distance (num of nodes - 1) between any two nodes in this tree.
    public int diameter() {
        return diameterAux(root)[1] == 0 ? 0 : diameterAux(root)[1] - 1;
    }

    private int[] diameterAux(TreeNode n) {
        if (n == null) return new int[2];

        int[] left = diameterAux(n.left);
        int[] right = diameterAux(n.right);

        int[] retVal = new int[2];
        // Height at this node
        retVal[0] = Math.max(left[0], right[0]) + 1;
        // Maximum diameter for this node and its children
        retVal[1] = Math.max(Math.max(left[1], right[1]), left[0] + right[0] + 1);

        return retVal;
    }
}
