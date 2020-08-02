package test;

import main.MinHeap;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {
    @Test
    public void testSiftDown() {
        int arr[] = {6, 2, 3, 4, 5, 7, 8};
        MinHeap.siftDown(arr, 0, arr.length);
        assertArrayEquals(new int[] {2, 4, 3, 6, 5, 7, 8}, arr);
    }

    @Test
    public void testHeapify() {
        int arr[] = {4, 10, 3, 5, 1};
        MinHeap.heapify(arr, arr.length);
        assertArrayEquals(new int[] {1, 4, 3, 5, 10}, arr);
    }

    @Test
    public void testExtractMin() {
        int arr[] = {1, 4, 3, 5, 10};
        MinHeap obj = new MinHeap(arr);
        for (int i: new int[] {1, 3, 4, 5, 10, Integer.MAX_VALUE}) assertEquals(i, obj.extractMin());
    }

    @Test
    public void testDecreaseKey() {
        int arr[] = {1, 4, 3, 5, 10};
        MinHeap obj = new MinHeap(arr);
        obj.decreaseKey(4, 2);
        assertArrayEquals(new int[] {1, 2, 3, 5, 4}, obj.heap);
    }

    @Test
    public void testDeleteKey() {
        int arr[] = {1, 4, 3, 5, 10};
        MinHeap obj = new MinHeap(arr);

        obj.deleteKey(4);
        assertEquals(4, obj.size);
        int expect[] = {1, 4, 3, 5};
        for (int i = 0; i < obj.size; i++)
            assertEquals(expect[i], obj.heap[i]);
    }

    @Test
    public void testInsertKey() {
        MinHeap obj = new MinHeap();
        obj.insertKey(10);
        obj.insertKey(5);
        obj.insertKey(4);
        obj.insertKey(3);
        obj.insertKey(1);
        assertEquals(5, obj.size);
        assertArrayEquals(new int[] {1, 3, 5, 10, 4, 0, 0, 0, 0, 0}, obj.heap);
    }
}
