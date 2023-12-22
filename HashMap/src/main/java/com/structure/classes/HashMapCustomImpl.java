package com.structure.classes;

import com.structure.interfaces.HashMapCustom;

public class HashMapCustomImpl<K, V> implements HashMapCustom<K, V> {
    private static final int INITIAL_CAPACITY = 101;
    private Entry<K, V> hashTable[];

    private HashMapCustomImpl() {
        hashTable = new Entry[INITIAL_CAPACITY];
    }

    private HashMapCustomImpl(int capacity) {
        hashTable = new Entry[capacity];
    }

}
