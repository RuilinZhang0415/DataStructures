package test;

import main.KDTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class KDTreeTest {
    @Test
    public void testInsert() {
        KDTree t = new KDTree();
        t.insert(new int[] {10, 20}); assertEquals(1, t.getSize());
        t.insert(new int[] {5, 10}); assertEquals(2, t.getSize());
        t.insert(new int[] {11, 5}); assertEquals(3, t.getSize());
        t.insert(new int[] {15, 2}); assertEquals(4, t.getSize());
        t.insert(new int[] {20, 1}); assertEquals(5, t.getSize());
        t.insert(new int[] {5, 8}); assertEquals(6, t.getSize());
        t.insert(new int[] {5, 8}); assertEquals(6, t.getSize());

        List<int[]> actual = t.preorderTraversal();
        List<int[]> expected = Arrays.asList(new int[] {10, 20}, new int[] {5, 10}, new int[] {5, 8}, new int[] {11, 5},
                new int[] {15, 2}, new int[] {20, 1});
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
            assertTrue(t.contains(expected.get(i)));
        }
    }

    @Test
    public void testDelete() {
        KDTree t = new KDTree();
        t.insert(new int[]{10, 20});
        t.insert(new int[]{5, 10});
        t.insert(new int[]{11, 5});
        t.insert(new int[]{15, 2});
        t.insert(new int[]{20, 1});
        t.insert(new int[]{5, 8});
        t.insert(new int[]{5, 8});

        t.delete(new int[]{10, 20});
        List<int[]> actual = t.preorderTraversal();
        List<int[]> expected = Arrays.asList(new int[]{11, 5}, new int[]{5, 10}, new int[]{5, 8}, new int[]{20, 1},
                new int[]{15, 2});
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void test() {
        KDTree t = new KDTree();
        t.insert(new int[] {8, 10});
        t.insert(new int[] {12, 7});
        t.insert(new int[] {8, 6});
        t.insert(new int[] {11, 6});
        t.insert(new int[] {10, 2});

        t.delete(new int[] {12, 7});
        List<int[]> actual = t.preorderTraversal();
        List<int[]> expected = Arrays.asList(new int[]{8, 10}, new int[]{10, 2}, new int[]{8, 6}, new int[]{11, 6});
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }
}
