package com.structure.classes;

import com.structure.interfaces.HashMapCustom;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

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
    public void testPut() {
        assertEquals(INITIAL_COUNT, integerStringHashMapCustom.size());
    }

    @Test
    public void testPutWithSameObject() {
        integerStringHashMapCustom.put(3, "rty");
        Random random = new Random();
        final int COUNT = 80;
        for (int i = 0; i < COUNT; i++) {
            integerStringHashMapCustom.put(random.nextInt(), "qwer");
        }
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
        assertEquals(4, collisions.size());
    }



    @Test
    public void testGet() {
    }

    @Test
    public void testContainsKey() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testClear() {
    }
}