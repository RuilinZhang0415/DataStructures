package test;

import main.BSTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BSTreeTest {
    @Test
    public void test1() {
        BSTree t = new BSTree();
        assertEquals(0, t.getHeight());
        assertEquals(0, t.getSize());
        t.insert(10);
        assertEquals(1, t.getHeight());
        assertEquals(1, t.getSize());
        t.insert(5);
        assertEquals(2, t.getHeight());
        assertEquals(2, t.getSize());
        t.insert(20);
        assertEquals(2, t.getHeight());
        assertEquals(3, t.getSize());
        t.insert(30);
        assertEquals(3, t.getHeight());
        assertEquals(4, t.getSize());

//        assertEquals(30, t.maxKey(t.root));
//        assertEquals(5, t.minKey(t.root));
    }

    @Test
    public void test2() {
        BSTree t = new BSTree();
        t.insert(10);
        t.insert(5);
        t.insert(20);
        t.insert(30);

        t.delete(10);
        assertEquals(2, t.getHeight());
        assertEquals(3, t.getSize());
    }

    @Test
    public void testVerticalTraversal() {
        BSTree t = new BSTree();
        t.insert(9);
        t.insert(3);
        t.insert(20);
        t.insert(15);
        t.insert(27);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(3), Arrays.asList(9,15), Arrays.asList(20), Arrays.asList(27));

        List<List<Integer>> actual = t.verticalTraversal();

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
            t.insert(i);
        }
        List<Integer> actual = t.preorderTraversal();
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testConstructTreeFromPreorderTraversal() {
        BSTree t = new BSTree();
        int[] pre = {10, 5, 1, 7, 40, 50};
        List<Integer> expected = Arrays.asList(10, 5, 1, 7, 40, 50);
        t = t.constructTreeFromPreorderTraversal(pre);
        List<Integer> actual = t.preorderTraversal();
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSortedListToBST() {
        BSTree t = new BSTree();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> expected = Arrays.asList(3, 2, 1, 4);
        t = t.sortedListToBST(list);
        List<Integer> actual = t.preorderTraversal();
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testMedian() {
        double tolerance = 0.000001;

        BSTree t = new BSTree();
        int[] arr = {1,2,3,4,5,6,7};
        for (int i: arr) t.insert(i);
        assertEquals(4, t.median(), tolerance);

        t.insert(8);
        assertEquals(4.5, t.median(), tolerance);

        t = new BSTree();
        assertEquals(Integer.MAX_VALUE, t.median(), tolerance);
    }
}
