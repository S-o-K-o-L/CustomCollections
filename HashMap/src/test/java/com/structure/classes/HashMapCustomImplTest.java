package com.structure.classes;

import com.structure.interfaces.HashMapCustom;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashMapCustomImplTest {
    HashMapCustom<Integer, String> integerStringHashMapCustom = new HashMapCustomImpl<>();
    private final int INITIAL_COUNT = 3;

    @Before
    public void init() {
        integerStringHashMapCustom.put(1, "qwe");
        integerStringHashMapCustom.put(2, "asd");
        integerStringHashMapCustom.put(3, "zxc");
    }

    @Test
    public void testSize() {
        assertEquals(INITIAL_COUNT, integerStringHashMapCustom.size());
    }

    @Test
    public void testPutWithSameObject() {
        integerStringHashMapCustom.put(3, "rty");
        assertEquals(INITIAL_COUNT, integerStringHashMapCustom.size());
    }

    @Test
    public void testPutWithCollisions() {
        HashMapCustom<Integer, String> collisions = new HashMapCustomImpl<>(1);
        Random random = new Random();
        final int COUNT = 10000;
        for (int i = 0; i < COUNT; i++) {
            collisions.put(random.nextInt(), "qwer");
        }
        assertEquals(COUNT, collisions.size());
    }

    @Test
    public void testGet() {
        assertEquals("asd", integerStringHashMapCustom.get(2));
    }

    @Test
    public void testContainsKey() {
        assertTrue(integerStringHashMapCustom.containsKey(2));
        assertFalse(integerStringHashMapCustom.containsKey(0));
    }

    @Test
    public void testRemove() {
        assertTrue(integerStringHashMapCustom.remove(1));
        assertFalse(integerStringHashMapCustom.remove(0));
        assertEquals(INITIAL_COUNT - 1, integerStringHashMapCustom.size());
    }

    @Test
    public void testRemoveWithManyExecutionTimes() {
        Random random = new Random();
        final int COUNT = 1000000;
        int collisionsCount = 0;
        for (int i = 0; i < COUNT; i++) {
            int randomInt = random.nextInt();
            if (integerStringHashMapCustom.containsKey(randomInt)) {
                integerStringHashMapCustom.remove(randomInt);
                collisionsCount++;
            }
            integerStringHashMapCustom.put(randomInt, "qwer");
        }
        assertEquals(INITIAL_COUNT + COUNT - collisionsCount ,
                integerStringHashMapCustom.size());
    }

    @Test
    public void testClear() {
        integerStringHashMapCustom.clear();
        assertEquals(0, integerStringHashMapCustom.size());
    }

    @Test
    public void testExpandTable() {
        Random random = new Random();
        final int COUNT = 1000000;
        int collisionsCount = 0;
        for (int i = 0; i < COUNT; i++) {
            int randomInt = random.nextInt();
            if (integerStringHashMapCustom.containsKey(randomInt)) {
                collisionsCount++;
            }
            integerStringHashMapCustom.put(randomInt, "qwer");
        }
        assertEquals(INITIAL_COUNT + COUNT - collisionsCount,
                integerStringHashMapCustom.size());
    }
}