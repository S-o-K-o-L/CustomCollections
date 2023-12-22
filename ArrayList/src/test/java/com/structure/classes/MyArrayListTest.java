package com.structure.classes;

import com.structure.interfaces.MyList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyArrayListTest {
    MyArrayList<Integer> integerMyArrayList = new MyArrayList<>(5);
    final private int INITIAL_COUNT = 3;
    final private int FIRST_ELEMENT = 1;
    final private int SECOND_ELEMENT = 2;
    final private int THIRD_ELEMENT = 3;

    @Before
    public void init() {
        integerMyArrayList.add(FIRST_ELEMENT);
        integerMyArrayList.add(SECOND_ELEMENT);
        integerMyArrayList.add(THIRD_ELEMENT);
    }

    @Test
    public void testResize() {
        final int COUNT_ELEMENT = 1000000;
        Random random = new Random();
        for (int i = 0; i < COUNT_ELEMENT; i++) {
            integerMyArrayList.add(random.nextInt());
        }
        assertEquals(COUNT_ELEMENT + INITIAL_COUNT, integerMyArrayList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithIllegalArgument() {
        new MyArrayList<>(-5);
    }

    @Test
    public void testSize() {
        assertEquals(INITIAL_COUNT, integerMyArrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(integerMyArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(integerMyArrayList.contains(FIRST_ELEMENT));
    }

    @Test
    public void testContainsAll() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(FIRST_ELEMENT);
        integers.add(SECOND_ELEMENT);
        assertTrue(integerMyArrayList.containsAll(integers));
    }

    @Test
    public void testForEach() {
        ArrayList<Integer> integers = new ArrayList<>();
        integerMyArrayList.forEach(integers::add);
        assertArrayEquals(integers.toArray(), integerMyArrayList.toArray());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAdd() {
        integerMyArrayList.add(FIRST_ELEMENT);
        assertEquals(INITIAL_COUNT + 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT, FIRST_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAddWithIndexToInsert() {
        integerMyArrayList.add(1, FIRST_ELEMENT);
        assertEquals(INITIAL_COUNT + 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAddFirst() {
        integerMyArrayList.addFirst(FIRST_ELEMENT);
        assertEquals(INITIAL_COUNT + 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAddLast() {
        integerMyArrayList.addLast(FIRST_ELEMENT);
        assertEquals(INITIAL_COUNT + 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT, FIRST_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAddAll() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(FIRST_ELEMENT);
        integers.add(SECOND_ELEMENT);
        integerMyArrayList.addAll(integers);
        assertEquals(INITIAL_COUNT + 2, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT, FIRST_ELEMENT, SECOND_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testAddAllWithIndexToInsert() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(FIRST_ELEMENT);
        integers.add(SECOND_ELEMENT);
        integerMyArrayList.addAll(0, integers);
        assertEquals(INITIAL_COUNT + 2, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testRemoveWithIndex() {
        integerMyArrayList.remove(0);
        assertEquals(INITIAL_COUNT - 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithInvalidIndex() {
        integerMyArrayList.remove(-5);
    }

    @Test
    public void testRemoveWithObject() {
        integerMyArrayList.remove(Integer.valueOf(2));
        assertEquals(INITIAL_COUNT - 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testRemoveWithNoneExistsObject() {
        assertFalse(integerMyArrayList.remove(Integer.valueOf(0)));
        assertEquals(INITIAL_COUNT, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testRemoveFirst() {
        integerMyArrayList.removeFirst();
        assertEquals(INITIAL_COUNT - 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstWhenListIsEmpty() {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.removeFirst();
    }

    @Test
    public void testRemoveLast() {
        integerMyArrayList.removeLast();
        assertEquals(INITIAL_COUNT - 1, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastWhenListIsEmpty() {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.removeLast();
    }

    @Test
    public void testRemoveIf() {
        integerMyArrayList.removeIf(elem -> elem % 2 == 0);
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testRemoveAll() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(FIRST_ELEMENT);
        integers.add(SECOND_ELEMENT);
        integerMyArrayList.removeAll(integers);
        assertArrayEquals(new Integer[]{THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testGet() {
        assertEquals(SECOND_ELEMENT, (int) integerMyArrayList.get(1));
    }

    @Test
    public void testGetFirst() {
        assertEquals(FIRST_ELEMENT, (int) integerMyArrayList.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(THIRD_ELEMENT, (int) integerMyArrayList.getLast());
    }

    @Test
    public void testSet() {
        integerMyArrayList.set(0, THIRD_ELEMENT);
        assertEquals(INITIAL_COUNT, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{THIRD_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testIndexOf() {
        assertEquals(2, integerMyArrayList.indexOf(3));
    }

    @Test
    public void testLastIndexOf() {
        integerMyArrayList.add(THIRD_ELEMENT);
        assertEquals(3, integerMyArrayList.lastIndexOf(3));
    }

    @Test
    public void testSubList() {
        MyList<Integer> reversedList = integerMyArrayList.subList(0, 1);
        assertArrayEquals(new Integer[]{FIRST_ELEMENT},
                reversedList.toArray());
    }

    @Test
    public void testStream() {
        assertEquals(6, (int) integerMyArrayList.stream().reduce(0, Integer::sum));
    }

    @Test
    public void testReversed() {
        MyList<Integer> reversedList = integerMyArrayList.reversed();
        assertArrayEquals(new Integer[]{THIRD_ELEMENT, SECOND_ELEMENT, FIRST_ELEMENT},
                reversedList.toArray());
    }

    @Test
    public void testSort() {
        integerMyArrayList.add(FIRST_ELEMENT);
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT, FIRST_ELEMENT},
                integerMyArrayList.toArray());
        integerMyArrayList.sort(Integer::compareTo);
        assertArrayEquals(new Integer[]{FIRST_ELEMENT, FIRST_ELEMENT, SECOND_ELEMENT, THIRD_ELEMENT},
                integerMyArrayList.toArray());
    }

    @Test
    public void testClear() {
        integerMyArrayList.clear();
        assertEquals(0, integerMyArrayList.size());
        assertArrayEquals(new Integer[]{}, integerMyArrayList.toArray());
    }
}