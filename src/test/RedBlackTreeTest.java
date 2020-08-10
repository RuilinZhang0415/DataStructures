package test;

import main.RedBlackTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedBlackTreeTest {
    @Test
    public void testInsertKey() {
        RedBlackTree t = new RedBlackTree();

        t.insert(55);
        t.insert(80);
        t.insert(90);
        t.insert(85);
        t.insert(66);
        t.insert(60);

        List<Integer> expected = Arrays.asList(80, 60, 55, 66, 90, 85);
        List<Integer> actual = t.preorderTraversal();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

        t.insert(87);
        expected = Arrays.asList(80, 60, 55, 66, 87, 85, 90);
        actual = t.preorderTraversal();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
