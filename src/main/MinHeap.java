package main;

public class MinHeap {
    // Instance variables
    public int size = 0, heap[];

    // Constructors
    public MinHeap() {
        heap = new int[10];
    }

    public MinHeap(int capacity) {
        this.size = capacity;
        heap = new int[capacity];
    }

    public MinHeap(int arr[]) {
        size = arr.length;
        heap = new int[arr.length];
        for (int i = 0; i < arr.length; i++) heap[i] = arr[i];
        heapify(heap, size);
    }

    public int getMin() {
        return heap[0];
    }


    // Public Instance methods

    public boolean decreaseKey(int index, int newVal) {
        if (index >= size) return false;

        heap[index] = newVal;

        int parent = (index - 1) / 2;
        while (index > 0 && heap[parent] > newVal) {
            swap(heap, parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
        return true;
    }

    public boolean deleteKey(int index) {
        if (decreaseKey(index, Integer.MIN_VALUE)) {
            extractMin();
            return true;
        }
        return false;
    }

    public int extractMin() {
        if (size == 0) return Integer.MAX_VALUE;

        int re = heap[0];
        swap(heap, 0, size - 1);
        size--;
        siftDown(heap, 0, size);

        return re;
    }

    public void insertKey(int val) {
        if (size == heap.length) expand();

        heap[size++] = val;

        int curr = size - 1, parent = (curr - 1) / 2;
        while (curr > 0 && heap[parent] > val) {
            swap(heap, parent, curr);
            curr = parent;
            parent = (curr - 1) / 2;
        }
    }

    private void expand() {
        int[] newHeap = new int[heap.length + 10];
        for (int i = 0; i < size; i++) newHeap[i] = heap[i];
        heap = newHeap;
    }

    // Public Static Methods
    public static void heapify(int arr[], int size) {
        if (arr == null || arr.length < 2) return;

        // Start from the last non-leaf node
        int curr = (size - 1) / 2;
        while (curr >= 0) {
            siftDown(arr, curr, size);
            curr--;
        }
    }

    public static void siftDown(int arr[], int parent, int size) {
        if (size <= 1) return;

        while (parent <= (size - 2) / 2) {
            int left = parent * 2 + 1, right = left + 1, min = left;

            if (right < size && arr[right] < arr[left]) min = right;

            if (arr[min] < arr[parent]) {
                swap(arr, parent, min);
                parent = min;
            } else {
                break;
            }
        }
    }

    private static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
