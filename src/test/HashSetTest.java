package test;

import main.HashSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashSetTest {
    @Test
    public void test1() {
        HashSet test = new HashSet();
        for (int i = 0; i < 7; i++) {
            assertFalse(test.contains(i));
            test.add(i);
            assertTrue(test.contains(i));
            assertEquals(i + 1, test.size());
        }
    }

    @Test
    public void test2() {
        HashSet test = new HashSet();
        for (int i = 1; i < 5; i++) {
            test.add(i * 11);
            assertTrue(test.contains(i * 11));
            assertEquals(i, test.size());
        }

        int index = 0;
        for (int i: test.data) {

            if (i == Integer.MIN_VALUE) {
                System.out.print(index + ":" + "E, ");
            } else {
                System.out.print(index + ":" + i + ", ");
            }
            index++;
        }
    }

    @Test
    public void test3() {
        HashSet test = new HashSet();
        for (int i = 1; i <= 100; i++) {
            test.add(i);
        }
        int size = 100;
        for (int i = 1; i <= 100; i += 3) {
            test.remove(i);
            assertFalse(test.contains(i));
            assertEquals(--size, test.size());
        }

        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 3 != 0) {
                assertTrue(test.contains(i));
            }
        }
    }
}
