package test;

import main.DisjointSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisjointSetTest {
    @Test
    public void test() {
        int size = 5;
        DisjointSet test = new DisjointSet(size);

        test.union(4, 5);
        assertEquals(test.find(4), test.find(5));

        test.union(0, 1);
        assertEquals(test.find(0), test.find(1));

        test.union(1, 5);
        assertEquals(test.find(0), test.find(4));

        test.union(2, 3);
        assertEquals(test.find(2), test.find(3));

        assertFalse(test.find(1) == test.find(2));
    }
}
