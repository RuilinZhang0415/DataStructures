package test;

import main.AVLTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test
    public void testRotate() {
        AVLTree t = new AVLTree();
        t.root = new AVLTree.AVLNode(10);
        t.root.left = new AVLTree.AVLNode(5);
        t.root.left.left = new AVLTree.AVLNode(1);
        t.root.left.right = new AVLTree.AVLNode(7);
        t.root.right = new AVLTree.AVLNode(40);
        t.root.right.right = new AVLTree.AVLNode(50);

        List<Integer> expected = Arrays.asList(5, 1, 10, 7, 40, 50);
        t.root = t.rotateRight(t.root);
        List<Integer> actual = t.preorderTraversal(t.root);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

        expected = Arrays.asList(10, 5, 1, 7, 40, 50);
        t.root = t.rotateLeft(t.root);
        actual = t.preorderTraversal(t.root);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testInsertKey1() {
        AVLTree t = new AVLTree();
        t.insertKey(10);
        t.insertKey(5);
        t.insertKey(1);

        List<Integer> actual = t.preorderTraversal(t.root);
        List<Integer> expected = Arrays.asList(5, 1, 10);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

        t.insertKey(7);
        t.insertKey(40);
        t.insertKey(50);

        actual = t.preorderTraversal(t.root);
        expected = Arrays.asList(10, 5, 1, 7, 40, 50);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testInsertKey2() {
        AVLTree t = new AVLTree();
        t.insertKey(10);
        t.insertKey(1);
        t.insertKey(7);

        List<Integer> actual = t.preorderTraversal(t.root);
        List<Integer> expected = Arrays.asList(7, 1, 10);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testDeleteKey() {
        AVLTree t = new AVLTree();
        List<Integer> keys = Arrays.asList(10, 5, 1, 7, 40, 50);
        for (int k: keys) t.insertKey(k);

        t.deleteKey(50);
        t.deleteKey(40);
        List<Integer> actual = t.preorderTraversal(t.root);
        List<Integer> expected = Arrays.asList(5, 1, 10, 7);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

        t.deleteKey(1);
        actual = t.preorderTraversal(t.root);
        expected = Arrays.asList(7, 5, 10);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
