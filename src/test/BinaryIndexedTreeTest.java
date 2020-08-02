package test;

import main.BinaryIndexedTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryIndexedTreeTest {
    @Test
    public void testQuery() {
        int[] arr = {1, 2, 3, 4, 3, 2, 3, 2, 1};
        BinaryIndexedTree t = new BinaryIndexedTree(arr);

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += arr[j];
            }
            assertEquals(sum, t.query(i + 1));
        }
    }

    @Test
    public void testUpdate() {
        int[] arr = {1, 2, 3, 4, 3, 2, 3, 2, 1};
        BinaryIndexedTree t = new BinaryIndexedTree(arr);

        for (int i = 0; i < arr.length; i++) {
            t.update(i + 1, 5);
            arr[i] += 5;

            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += arr[j];
            }
            assertEquals(sum, t.query(i + 1));
        }
    }
}
