package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.autobus.model.MyArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testDefaultConstructor() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testAddSingleElement() {
        assertTrue(list.add("A"));
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void testAddMultipleElements() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddExceedInitialCapacity() {
        for (int i = 0; i < 15; i++) {
            list.add("Element" + i);
        }

        assertEquals(15, list.size());
        assertEquals("Element0", list.get(0));
        assertEquals("Element14", list.get(14));
    }
    @Test
    void testGetValidIndex() {
        list.add("A");
        list.add("B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        list.add("A");

        assertNull(list.get(-1));
        assertNull(list.get(1));
    }

    @Test
    void testGetEmptyList() {
        assertNull(list.get(0));
    }

    @Test
    void testSetValidIndex() {
        list.add("A");
        list.add("B");

        String oldValue = list.set(1, "X");

        assertEquals("B", oldValue);
        assertEquals("X", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void testSetFirstElement() {
        list.add("A");
        list.add("B");

        String oldValue = list.set(0, "First");

        assertEquals("A", oldValue);
        assertEquals("First", list.get(0));
    }

    @Test
    void testSetInvalidIndex() {
        list.add("A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(5, "X");
        });
    }

    @Test
    void testRemoveValidIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        String removed = list.remove(1);

        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testRemoveFirstElement() {
        list.add("A");
        list.add("B");

        String removed = list.remove(0);

        assertEquals("A", removed);
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    void testRemoveLastElement() {
        list.add("A");
        list.add("B");

        String removed = list.remove(1);

        assertEquals("B", removed);
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    void testIsEmptyTrue() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        list.add("A");
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemove() {
        list.add("A");
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAllWithCollection() {
        List<String> collection = Arrays.asList("X", "Y", "Z");

        boolean result = list.addAll(collection);

        assertTrue(result);
        assertEquals(3, list.size());
        assertEquals("X", list.get(0));
        assertEquals("Z", list.get(2));
    }

    @Test
    void testAddAllEmptyCollection() {
        List<String> emptyCollection = Arrays.asList();

        boolean result = list.addAll(emptyCollection);

        assertTrue(result);
        assertTrue(list.isEmpty());
    }

    @Test
    void testStream_EmptyList() {
        long count = list.stream().count();
        assertEquals(0, count);
    }

    @Test
    void testStreamWithElements() {
        list.add("A");
        list.add("B");
        list.add("C");

        long count = list.stream().count();
        List<String> collected = list.stream().collect(Collectors.toList());

        assertEquals(3, count);
        assertEquals(Arrays.asList("A", "B", "C"), collected);
    }

    @Test
    void testStreamFilterOperations() {
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        List<String> result = list.stream()
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0));
    }
}