package main;

import java.util.Arrays;

public class HashSet {
    private int data[], capacity, size;
    private final double ratio;

    public HashSet() {
        data = new int[11];
        Arrays.fill(data, Integer.MIN_VALUE);
        capacity = 11;
        size = 0;
        ratio = 0.7;
    }

    public HashSet(double ratio) {
        data = new int[11];
        Arrays.fill(data, Integer.MIN_VALUE);
        capacity = 11;
        size = 0;
        this.ratio = ratio;
    }

    // Private methods
    private int hash(int key) { // Simple uniform hash function
        return key % capacity;
    }

    private void expand() { // New capacity will be the largest prime smaller than 2 times curr capacity
        int newCapacity = 2 * capacity;
        while (!isPrime(newCapacity)) {
            newCapacity--;
        }

        int temp[] = data;
        data = new int[newCapacity];
        Arrays.fill(data, Integer.MIN_VALUE);
        capacity = newCapacity;
        size = 0;
        for (int i: temp) {
            if (i != Integer.MIN_VALUE) {
                add(i);
            }
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) if (num % i == 0) return false;
        return true;
    }

    // Public methods
    public void add(int key) { // Insertion with linear probing
        int index = hash(key);
        while (data[index] != Integer.MIN_VALUE && data[index] != key) {
            index++;

            if (index == capacity) index = 0;
        }

        if (data[index] == Integer.MIN_VALUE) {
            data[index] = key;
            size++;

            double currRatio = size * 1.0 / capacity; // Resize
            if (currRatio > ratio) expand();
        }
    }

    public boolean contains(int key) {
        int index = hash(key);
        while (data[index] != Integer.MIN_VALUE && data[index] != key) {
            index++;

            if (index == capacity) index = 0;
        }

        if (data[index] == key) {
            return true;
        }

        return false;
    }

    public void remove(int key) { // Remove with linear probing
        int index = hash(key);
        while (data[index] != Integer.MIN_VALUE && data[index] != key) {
            index++;

            if (index == capacity) index = 0;
        }

        if (data[index] == key) {
            data[index] = Integer.MIN_VALUE;
            size--;

            // Re-insert all keys in this chain
            index += 1;
            while (data[index] != Integer.MIN_VALUE) {
                int temp = data[index];
                data[index] = Integer.MIN_VALUE;
                size--;
                add(temp);

                index++;

                if (index == capacity) index = 0;
            }
        }

    }

    public int size() {
        return size;
    }
}
