package main;

public class BinaryIndexedTree {
    public int BIT[], len;

    public BinaryIndexedTree(int[] arr) {
        len = arr.length + 1;
        BIT = new int[len];
        for (int i = 1; i < arr.length + 1; i++) update(i, arr[i - 1]);
    }

    public void update(int i, int val) {
        for (; i < len; i += i&-i) BIT[i] += val;
    }

    public int query(int i) {
        int sum = 0;
        for (; i > 0; i -= i&-i) sum += BIT[i];
        return sum;
    }
}
