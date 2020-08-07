package test;

import main.BSTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BSTreeTest {
    @Test
    public void test1() {
        BSTree t = new BSTree();
        assertEquals(0, t.getHeight(t.root));
        assertEquals(0, t.getSize(t.root));
        t.root = t.insertKey(t.root, 10);
        assertEquals(1, t.getHeight(t.root));
        assertEquals(1, t.getSize(t.root));
        t.root = t.insertKey(t.root, 5);
        assertEquals(2, t.getHeight(t.root));
        assertEquals(2, t.getSize(t.root));
        t.root = t.insertKey(t.root, 20);
        assertEquals(2, t.getHeight(t.root));
        assertEquals(3, t.getSize(t.root));
        t.root = t.insertKey(t.root, 30);
        assertEquals(3, t.getHeight(t.root));
        assertEquals(4, t.getSize(t.root));

        assertEquals(30, t.maxKey(t.root));
        assertEquals(5, t.minKey(t.root));
    }

    @Test
    public void test2() {
        BSTree t = new BSTree();
        t.root = t.insertKey(t.root, 10);
        t.root = t.insertKey(t.root, 5);
        t.root = t.insertKey(t.root, 20);
        t.root = t.insertKey(t.root, 30);

        t.root = t.deleteKey(t.root, 10);
        assertEquals(2, t.getHeight(t.root));
        assertEquals(3, t.getSize(t.root));
        assertEquals(20, t.root.key);
    }

    @Test
    public void testVerticalTraversal() {
        BSTree t = new BSTree();
        t.root = t.insertKey(t.root, 9);
        t.root = t.insertKey(t.root, 3);
        t.root = t.insertKey(t.root, 20);
        t.root = t.insertKey(t.root, 15);
        t.root = t.insertKey(t.root, 27);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(3), Arrays.asList(9,15), Arrays.asList(20), Arrays.asList(27));

        List<List<Integer>> actual = t.verticalTraversal(t.root);
        for (int i = 0; i < expected.size(); i++) {
            int len = expected.get(i).size();
            for (int j = 0; j < len; j++) {
                assertEquals(expected.get(i).get(j), actual.get(i).get(j));
            }
        }
    }

    @Test
    public void testPreorderTraversal() {
        BSTree t = new BSTree();
        List<Integer> expected = Arrays.asList(10, 5, 1, 7, 40, 50);
        for (int i: expected) {
            t.root = t.insertKey(t.root, i);
        }
        List<Integer> actual = t.preorderTraversal(t.root);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testConstructTreeFromPreorderTraversal() {
        BSTree t = new BSTree();
        int[] pre = {10, 5, 1, 7, 40, 50};
        List<Integer> expected = Arrays.asList(10, 5, 1, 7, 40, 50);
        t.root = t.constructTreeFromPreorderTraversal(pre);
        List<Integer> actual = t.preorderTraversal(t.root);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSortedListToBST() {
        BSTree t = new BSTree();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> expected = Arrays.asList(3, 2, 1, 4);
        t.root = t.sortedListToBST(list);
        List<Integer> actual = t.preorderTraversal(t.root);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testMedian() {
        double tolerance = 0.000001;

        BSTree t = new BSTree();
        int[] arr = {1,2,3,4,5,6,7};
        for (int i: arr) t.root = t.insertKey(t.root, i);
        assertEquals(4, t.median(t.root), tolerance);

        t.root = t.insertKey(t.root, 8);
        assertEquals(4.5, t.median(t.root), tolerance);

        assertEquals(Integer.MAX_VALUE, t.median(null), tolerance);
    }
}
